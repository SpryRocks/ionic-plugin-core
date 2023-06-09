package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import com.ionic.plugin.core.PluginExceptionBase
import com.ionic.plugin.core.PluginExceptionNew
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
    open fun map(error: Throwable): PluginExceptionBase {
        if (error is PluginExceptionBase) return error
        return PluginException(error.message, cause = error)
    }

    open fun mapToJson(error: Throwable): JsonObject? {
        return MutableJsonObject().apply {
            error.message?.let { put("message", it) }
            if (error is PluginException) {
                error.code?.let { put("code", it) }
            }
            if (error is PluginExceptionNew) {
                error.code?.let { put("code", it) }
            }
        }
    }

    open fun reportError(error: Throwable?, callContext: CallContext, finish: Boolean) {
        callContext.error(error, finish)
    }
}
