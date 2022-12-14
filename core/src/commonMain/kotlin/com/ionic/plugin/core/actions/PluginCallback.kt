package com.ionic.plugin.core.actions

interface PluginCallback<TDelegate : Delegate, TAction : BaseAction<TDelegate>> {
    fun reportSuccess(data: Any?, call: CallContext, finish: Boolean)
    fun reportError(error: Throwable?, call: CallContext, finish: Boolean)
    fun finishActionSafely(action: TAction)
}
