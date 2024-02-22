package com.ionic.plugin.core

import com.ionic.plugin.core.actions.*
import com.ionic.plugin.core.events.EventBase
import com.ionic.plugin.core.events.IEventSender
import com.ionic.plugin.core.logger.*
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
    IEventSender<TDelegate, TMappers> {
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

    fun call(action: TActionKey, call: CallContext) = wrapActionSafely(call) {
        print("plugin action: $action")
        val baseAction = createAction(action, call)
        setCurrentActionAndRunSafely(baseAction, call)
    }

    fun callAction(baseAction: BaseAction<TDelegate, TMappers>, call: CallContext) = wrapActionSafely(call) {
        setCurrentActionAndRunSafely(baseAction, call)
    }

    private fun wrapActionSafely(call: CallContext, block: () -> Unit): Boolean {
        try {
            block()
        } catch (error: Throwable) {
            mappers.reportError(error, call, true)
        }
        return true
    }

    private val callback = object : PluginCallbackInternal<TDelegate, BaseAction<TDelegate, TMappers>, TMappers> {
        override fun finishActionSafely(action: BaseAction<TDelegate, TMappers>) {
            synchronized(_actionsLockObject) {
                actions?.actionFinished(action)
            }
        }

        override fun sendLog(
            action: String?,
            tag: String?,
            level: LogLevel,
            message: String,
            params: Array<out LogParam>
        ) {
            val event = LogEvent<TDelegate, TMappers>(action, tag, level, message, params)
            if (!testLog(event)) return
            sendEvent(event)
        }

        override fun sendEvent(event: EventBase<TDelegate, TMappers>) {
            this@Plugin.sendEvent(event)
        }

        override fun setLogLevels(logLevels: Array<LogLevel>?) {
            this@Plugin.setLogLevels(logLevels)
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

    override fun logger(tag: String?): ILogger = Logger(null, tag, callback)

    override fun sendEvent(event: EventBase<TDelegate, TMappers>) {
        event.initialize(callback, delegate)
        wrapperDelegate.sendEvent(event.name, event.getData())
    }

    private var logLevels: Array<LogLevel>? = null

    fun setLogLevels(logLevels: Array<LogLevel>?) {
        this.logLevels = logLevels
    }

    private fun testLog(data: LogEvent<TDelegate, TMappers>): Boolean {
        val logLevels = this.logLevels ?: return true
        return logLevels.contains(data.level)
    }
}
