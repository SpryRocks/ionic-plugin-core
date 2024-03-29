package com.ionic.plugin.core.actions

import com.ionic.plugin.core.events.IEventSender
import com.ionic.plugin.core.logger.ILoggerRaw
import com.ionic.plugin.core.logger.LogLevel

interface PluginCallbackInternal<
        TDelegate : Delegate<TMappers>,
        TAction : BaseAction<TDelegate, TMappers>,
        TMappers : Mappers,
        > :
    ILoggerRaw,
    IEventSender<TDelegate, TMappers> {
    fun finishActionSafely(action: TAction)
    fun setLogLevels(logLevels: Array<LogLevel>?)
}
