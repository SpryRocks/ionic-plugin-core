package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

object Mappers {
    @Throws(PluginException::class)
    fun mapObjectToJson(src: Any): JsonElement {
        return Json.encodeToJsonElement(src);
    }

    interface IErrorMapper {
        fun map(error: PluginException): CallContext.Result
    }

    class DefaultErrorMapper : IErrorMapper {
        override fun map(error: PluginException): CallContext.Result {
            return CallContext.Result(CallContext.Result.Status.ERROR, error.message.orEmpty())
        }
    }
}
