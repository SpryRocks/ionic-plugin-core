package com.ionic.plugin.core

import com.ionic.plugin.core.actions.*
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext
import kotlin.js.JsExport

@JsExport
abstract class Plugin<TActionKey, TDelegate : Delegate>
protected constructor() : Callback<TDelegate, BaseAction<TDelegate>>, CoroutineScope {
    private val _actionsLockObject = SynchronizedObject()

    protected abstract val delegate: TDelegate;

    abstract fun createAction(action: TActionKey, call: CallContext): BaseAction<TDelegate>

    open fun load() {}

    open fun unload() {}

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job

    fun call(action: TActionKey, call: CallContext): Boolean {
        print("plugin action: $action")
        try {
            val baseAction = createAction(action, call)
            setCurrentActionAndRunSafely(baseAction, call)
        } catch (error: Throwable) {
            call.result(
                CallContextResult.error(errorMapper.map(error)),
                true
            )
        }
        return true
    }

    @Throws(PluginException::class)
    private fun setCurrentActionAndRunSafely(action: BaseAction<TDelegate>, call: CallContext) {
        setupAction(action, call)
        synchronized(_actionsLockObject) {
            beforeActionRun(action)
        }
        action.run()
    }

    override fun finishActionSafely(action: BaseAction<TDelegate>) {
        synchronized(_actionsLockObject) {
            actionFinished(action)
        }
    }

    protected open fun beforeActionRun(action: BaseAction<TDelegate>) {}
    protected open fun actionFinished(action: BaseAction<TDelegate>) {}

    private fun setupAction(action: BaseAction<TDelegate>, call: CallContext) {
        action.initialize(call, this, delegate)
    }

    private val defaultErrorMapper: IErrorMapper = DefaultErrorMapper()
    private var _errorMapper: IErrorMapper? = null
    override val errorMapper: IErrorMapper get() = _errorMapper ?: defaultErrorMapper
}
