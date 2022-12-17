package com.ionic.plugin.core

import com.ionic.plugin.core.actions.*
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.js.JsExport

@JsExport
abstract class Plugin<TActionKey, TDelegate : Delegate<TMappers>, TMappers: Mappers>
protected constructor() : PluginCallback<TDelegate, BaseAction<TDelegate, TMappers>, TMappers>, CoroutineScope {
    private val _actionsLockObject = SynchronizedObject()

    protected abstract val delegate: TDelegate

    abstract val mappers: Mappers

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
            reportError(error, call, true)
        }
        return true
    }

    override fun reportSuccess(data: Any?, call: CallContext, finish: Boolean) {
        mappers.reportSuccess(data, call, finish)
    }

    override fun reportError(error: Throwable?, call: CallContext, finish: Boolean) {
        mappers.reportError(error, call, finish)
    }

    @Throws(PluginException::class)
    private fun setCurrentActionAndRunSafely(action: BaseAction<TDelegate, TMappers>, call: CallContext) {
        setupAction(action, call)
        synchronized(_actionsLockObject) {
            beforeActionRun(action)
        }
        action.run()
    }

    override fun finishActionSafely(action: BaseAction<TDelegate, TMappers>) {
        synchronized(_actionsLockObject) {
            actionFinished(action)
        }
    }

    protected open fun beforeActionRun(action: BaseAction<TDelegate, TMappers>) {}
    protected open fun actionFinished(action: BaseAction<TDelegate, TMappers>) {}

    private fun setupAction(action: BaseAction<TDelegate, TMappers>, call: CallContext) {
        action.initialize(call, this, delegate)
    }
}
