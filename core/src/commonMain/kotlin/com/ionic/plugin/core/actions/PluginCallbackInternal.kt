package com.ionic.plugin.core.actions

import com.ionic.plugin.core.events.IEventSender
import com.ionic.plugin.core.logger.ILoggerRaw

interface PluginCallbackInternal<
        TDelegate : Delegate<TMappers>,
        TAction : BaseAction<TDelegate, TMappers>,
        TMappers : Mappers,
        > :
    ILoggerRaw,
    IEventSender<TDelegate, TMappers> {
    fun reportSuccess(data: Any?, call: CallContext, finish: Boolean)
    fun reportError(error: Throwable?, call: CallContext, finish: Boolean)
    fun finishActionSafely(action: TAction)
}
