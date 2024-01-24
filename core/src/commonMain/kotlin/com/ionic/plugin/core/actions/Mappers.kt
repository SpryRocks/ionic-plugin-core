package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginExceptionOld
import com.ionic.plugin.core.PluginExceptionBase
import com.ionic.plugin.core.PluginException
import com.ionic.plugin.core.logger.LogParam
import com.spryrocks.kson.*
import kotlin.js.JsExport
import kotlin.reflect.KClass

@JsExport
abstract class Mappers {
    open fun reportSuccess(data: Any?, callContext: CallContext, finish: Boolean) {
        callContext.success(data, finish)
    }

    fun reportError(error: Throwable?, callContext: CallContext, finish: Boolean) {
        errorMapper.reportError(error, callContext, finish)
    }

    abstract val errorMapper: ErrorMapper
    open val logMapper: LogMapper = LogMapper()
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
            if (error is PluginExceptionOld) {
                error.code?.let { put("code", it) }
            }
            if (error is PluginException) {
                error.code?.let { put("code", it) }
            }
        }
    }

    open fun reportError(error: Throwable?, callContext: CallContext, finish: Boolean) {
        callContext.error(error, finish)
    }
}

typealias LogMapperObjectFormatter<T> = (obj: T) -> JsonObject

open class LogMapper {
    private val formatters = mutableMapOf<KClass<*>, LogMapperObjectFormatter<*>>()

    protected fun <T: Any> register(objectClass: KClass<T>, formatter: LogMapperObjectFormatter<T>) {
        formatters[objectClass] = formatter
    }

    private fun mapLogObject(obj: Any): JsonObject? {
        val formatter = formatters[obj::class]
        return if (formatter != null) {
            (formatter as LogMapperObjectFormatter<Any>)(obj)
        } else {
            null
        }
    }

    internal fun formatParams(params: Array<out LogParam>): JsonObject {
        return mutableJsonObject().apply {
            params.forEach {
                val value = formatValue(it.second)
                if (value != null) {
                    put(it.first, value)
                } else {
                    putNull(it.first)
                }
            }
        }
    }

    private fun formatValue(value: Any?): JsonValue? {
        if (value == null) return null
        if (value is Array<*>) {
            return formatArray(value)
        }
        if (value is String || value is Number || value is Boolean) {
            return value
        }
        return formatObject(value)
    }

    private fun formatArray(array: Array<*>): JsonArray {
        return mutableJsonArray().apply {
            array.forEach {
                val value = formatValue(it)
                if (value != null) {
                    put(value)
                } else {
                    putNull()
                }
            }
        }
    }

    private fun formatObject(obj: Any): JsonValue {
        val formatted = mapLogObject(obj)
        if (formatted != null) return formatted
        return obj.toString()
    }
}
