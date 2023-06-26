package com.ionic.plugin.core.actions

import com.ionic.plugin.core.logger.IPluginLogger

interface PluginCallback<
        TDelegate : Delegate<TMappers>,
        TAction : BaseAction<TDelegate, TMappers>,
        TMappers : Mappers,
        >: IPluginLogger {
    fun reportSuccess(data: Any?, call: CallContext, finish: Boolean)
    fun reportError(error: Throwable?, call: CallContext, finish: Boolean)
    fun finishActionSafely(action: TAction)
}
