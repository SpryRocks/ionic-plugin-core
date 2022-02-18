package com.ionic.plugin.core

import com.cordova.core.actions.LogUtils.debug
import com.ionic.plugin.core.actions.BaseAction
import com.ionic.plugin.core.actions.Delegate
import com.ionic.plugin.core.actions.Mappers
import com.ionic.plugin.core.actions.CallContext

abstract class Plugin<TActionKey, TDelegate : Delegate>
protected constructor(): BaseAction.Callback<TDelegate, BaseAction<TDelegate>> {
    private val _actionsLockObject = Any()

    protected abstract val delegate: TDelegate;

    abstract val factory: Factory<TActionKey, TDelegate, BaseAction<TDelegate>>

    fun call(action: TActionKey, call: CallContext): Boolean {
        debug("plugin action: $action")
        try {
            val baseAction = factory.createAction(action, call)
            setCurrentActionAndRunSafely(baseAction)
        } catch (e: PluginException) {
            call.result(delegate.errorMapper.map(e))
        }
        return true
    }

    @Throws(PluginException::class)
    private fun setCurrentActionAndRunSafely(action: BaseAction<TDelegate>) {
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

    private fun setupAction(action: BaseAction<TDelegate>) {
        action._callback = this
        action._delegate = delegate
    }
}