package com.ionic.plugin.core

import com.cordova.core.actions.LogUtils.debug
import com.ionic.plugin.core.actions.*
import kotlin.js.JsExport

@JsExport
abstract class Plugin<TActionKey, TDelegate : Delegate>
protected constructor() : Callback<TDelegate, BaseAction<TDelegate>> {
    private val _actionsLockObject = Any()

    protected abstract val delegate: TDelegate;

    abstract fun createAction(action: TActionKey, call: CallContext): BaseAction<TDelegate>

    open fun load() {}

    fun call(action: TActionKey, call: CallContext): Boolean {
        debug("plugin action: $action")
        try {
            val baseAction = createAction(action, call)
            setCurrentActionAndRunSafely(baseAction, call)
        } catch (e: PluginException) {
            call.result(delegate.errorMapper.map(e))
        }
        return true
    }

    @Throws(PluginException::class)
    private fun setCurrentActionAndRunSafely(action: BaseAction<TDelegate>, call: CallContext) {
        setupAction(action, call)
//        synchronized(_actionsLockObject) {
        beforeActionRun(action)
//        }
        action.run()
    }

    override fun finishActionSafely(action: BaseAction<TDelegate>) {
//        synchronized(_actionsLockObject) {
        actionFinished(action)
//        }
    }

    //    @Throws(PluginException::class)
    protected open fun beforeActionRun(action: BaseAction<TDelegate>) {}
    protected open fun actionFinished(action: BaseAction<TDelegate>) {}

    private fun setupAction(action: BaseAction<TDelegate>, call: CallContext) {
        action._callback = this
        action._delegate = delegate
        action._call = call
    }
}