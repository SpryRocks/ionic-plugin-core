package com.ionic.plugin.core.actions

import com.spryrocks.kson.JsonObject
import kotlin.js.JsExport

expect interface CallContext {
    fun getString(key: String): String?
    fun getInt(key: String): Int?
    fun getObject(key: String): JsonObject?
    fun getBoolean(key: String): Boolean?
    fun getDouble(key: String): Double?

    fun result(result: CallContextResult, finish: Boolean)
}

@JsExport
abstract class CallContextResult protected constructor() {
    companion object {
        fun success(data: JsonObject? = null) = Success(data)
        fun error(error: Throwable? = null) = Error(error)
    }

    class Success internal constructor(val data: JsonObject? = null) : CallContextResult()
    class Error internal constructor(val error: Throwable? = null) : CallContextResult()
}
