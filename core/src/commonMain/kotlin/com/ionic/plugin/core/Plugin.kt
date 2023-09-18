package com.ionic.plugin.core

import com.ionic.plugin.core.actions.*
import com.ionic.plugin.core.logger.*
import com.spryrocks.kson.JsonObject
import com.spryrocks.kson.mutableJsonObject
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.js.JsExport

@JsExport
abstract class Plugin<TActionKey, TDelegate : Delegate<TMappers>, TMappers : Mappers>
protected constructor() :
    CoroutineScope,
    WithLogger,
    IPluginLogger {
    private val _actionsLockObject = SynchronizedObject()

    protected abstract val delegate: TDelegate

    abstract val mappers: Mappers

    private var actions: IActions<TDelegate, TMappers>? = null

    fun setActions(actions: IActions<TDelegate, TMappers>) {
        this.actions = actions
    }

    abstract fun createAction(action: TActionKey, call: CallContext): BaseAction<TDelegate, TMappers>

    open fun load() {}

    open fun unload() {}

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job

    private var _wrapperDelegate: WrapperDelegate? = null
    protected open val wrapperDelegate: WrapperDelegate
        get() = _wrapperDelegate!!

    fun _initializePluginInternal(wrapperDelegate: WrapperDelegate) {
        this._wrapperDelegate = wrapperDelegate
    }

    fun call(action: TActionKey, call: CallContext): Boolean {
        print("plugin action: $action")
        try {
            val baseAction = createAction(action, call)
            setCurrentActionAndRunSafely(baseAction, call)
        } catch (error: Throwable) {
            callback.reportError(error, call, true)
        }
        return true
    }

    private val callback = object : PluginCallbackInternal<TDelegate, BaseAction<TDelegate, TMappers>, TMappers> {
        override fun reportSuccess(data: Any?, call: CallContext, finish: Boolean) {
            mappers.reportSuccess(data, call, finish)
        }

        override fun reportError(error: Throwable?, call: CallContext, finish: Boolean) {
            mappers.reportError(error, call, finish)
        }

        override fun finishActionSafely(action: BaseAction<TDelegate, TMappers>) {
            synchronized(_actionsLockObject) {
                actions?.actionFinished(action)
            }
        }

        override fun sendEvent(name: String, data: JsonObject) {
            this@Plugin.sendEvent(name, data)
        }

        override fun sendLog(action: String?, tag: String?, level: LogLevel, message: String, params: Array<out LogParam>) {
            this@Plugin.sendLog(action, tag, level, message, params)
        }
    }

    @Throws(PluginException::class)
    private fun setCurrentActionAndRunSafely(action: BaseAction<TDelegate, TMappers>, call: CallContext) {
        setupAction(action, call)
        synchronized(_actionsLockObject) {
            actions?.beforeActionRun(action)
        }
        action.run()
    }

    private fun setupAction(action: BaseAction<TDelegate, TMappers>, call: CallContext) {
        action.initialize(call, callback, delegate)
    }

    override fun logger(tag: String?): ILogger = Logger(null, tag, this)

    fun sendEvent(name: String, data: JsonObject) {
        wrapperDelegate.sendEvent(name, data)
    }

    override fun sendLog(action: String?, tag: String?, level: LogLevel, message: String, params: Array<out LogParam>) {
        val paramsJsonArray = mutableJsonObject().apply {
            params.forEach { put(it.first, it.second.toString()) }
        }
        val data = mutableJsonObject().apply {
            put("level", level.value)
            action?.let { put("action", it) }
            tag?.let { put("tag", it) }
            put("message", message)
            put("params", paramsJsonArray)
        }
        sendEvent("log", data)
    }
}
