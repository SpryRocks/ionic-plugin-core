package com.ionic.plugin.core.logger

import com.ionic.plugin.core.actions.Delegate
import com.ionic.plugin.core.actions.Mappers
import com.ionic.plugin.core.events.EventBase
import com.spryrocks.kson.JsonObject
import com.spryrocks.kson.mutableJsonObject

class LogEvent<TDelegate : Delegate<TMappers>, TMappers : Mappers>(
    private val action: String?,
    private val tag: String?,
    private val level: LogLevel,
    private val message: String,
    private val params: Array<out LogParam>,
) : EventBase<TDelegate, TMappers>() {
    override val name = "log"

    override fun getData(): JsonObject {
        val paramsJsonArray = mutableJsonObject().apply {
            params.forEach { put(it.first, it.second.toString()) }
        }
        return mutableJsonObject().apply {
            put("level", level.value)
            action?.let { put("action", it) }
            tag?.let { put("tag", it) }
            put("message", message)
            put("params", paramsJsonArray)
        }
    }
}
