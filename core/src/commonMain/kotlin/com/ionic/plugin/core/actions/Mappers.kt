package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import com.spryrocks.kson.JsonObject
import com.spryrocks.kson.MutableJsonObject
import kotlin.js.JsExport

@JsExport
open class Mappers

@JsExport
interface IErrorMapper {
    fun map(error: Throwable): PluginException

    fun mapToJson(error: Throwable): JsonObject?
}

@JsExport
class DefaultErrorMapper : IErrorMapper {
    override fun map(error: Throwable): PluginException {
        if (error is PluginException) return error
        return PluginException(error.message, cause = error)
    }

    override fun mapToJson(error: Throwable): JsonObject? {
        if (error !is PluginException) return null

        val code = error.code

        val jsonObject = MutableJsonObject()
        code?.let { jsonObject.put("code", it) }
        return jsonObject
    }
}
