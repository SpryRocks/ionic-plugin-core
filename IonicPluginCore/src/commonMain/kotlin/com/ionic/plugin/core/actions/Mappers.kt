package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement

open class Mappers {
    inline fun <reified T>mapToJson(src: T) : JsonElement {
        return Json.encodeToJsonElement(src);
    }

    inline fun <reified T>mapObjectToJson(src: T) : JsonObject {
        return mapToJson(src) as JsonObject;
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
