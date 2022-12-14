package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import com.spryrocks.kson.JsonObject
import com.spryrocks.kson.MutableJsonObject
import kotlin.js.JsExport

@JsExport
abstract class Mappers {
    open fun reportSuccess(data: Any?, callContext: CallContext, finish: Boolean) {
        callContext.success(data, finish)
    }

    fun reportError(error: Throwable?, callContext: CallContext, finish: Boolean) {
        errorMapper.reportError(error, callContext, finish)
    }

    abstract val errorMapper: ErrorMapper
}

@JsExport
open class ErrorMapper {
    open fun map(error: Throwable): PluginException {
        if (error is PluginException) return error
        return PluginException(error.message, cause = error)
    }

    open fun mapToJson(error: Throwable): JsonObject? {
        if (error !is PluginException) return null

        val code = error.code

        val jsonObject = MutableJsonObject()
        code?.let { jsonObject.put("code", it) }
        return jsonObject
    }

    open fun reportError(error: Throwable?, callContext: CallContext, finish: Boolean) {
        callContext.error(error, finish)
    }
}
