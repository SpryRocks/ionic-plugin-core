package com.ionic.plugin.core.actions

import com.ionic.plugin.core.logger.IPluginLogger
import com.spryrocks.kson.JsonObject

interface PluginCallbackInternal<
        TDelegate : Delegate<TMappers>,
        TAction : BaseAction<TDelegate, TMappers>,
        TMappers : Mappers,
        >: IPluginLogger {
    fun reportSuccess(data: Any?, call: CallContext, finish: Boolean)
    fun reportError(error: Throwable?, call: CallContext, finish: Boolean)
    fun finishActionSafely(action: TAction)
    fun sendEvent(name: String, data: JsonObject)
}
