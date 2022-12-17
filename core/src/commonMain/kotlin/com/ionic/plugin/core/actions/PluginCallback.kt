package com.ionic.plugin.core.actions

interface PluginCallback<
        TDelegate : Delegate<TMappers>,
        TAction : BaseAction<TDelegate, TMappers>,
        TMappers : Mappers,
        > {
    fun reportSuccess(data: Any?, call: CallContext, finish: Boolean)
    fun reportError(error: Throwable?, call: CallContext, finish: Boolean)
    fun finishActionSafely(action: TAction)
}
