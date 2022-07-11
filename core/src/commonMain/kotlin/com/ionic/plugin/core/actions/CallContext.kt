package com.ionic.plugin.core.actions

import com.spryrocks.kson.JsonArray
import com.spryrocks.kson.JsonObject
import kotlin.js.JsExport

expect interface CallContext {
    fun getString(key: String): String
    fun getInt(key: String): Int
    fun getJsonObject(key: String): JsonObject
    fun getBoolean(key: String): Boolean
    fun getDouble(key: String): Double
    fun getJsonArray(key: String): JsonArray

    fun optString(key: String): String?
    fun optInt(key: String): Int?
    fun optJsonObject(key: String): JsonObject?
    fun optBoolean(key: String): Boolean?
    fun optDouble(key: String): Double?
    fun optJsonArray(key: String): JsonArray?

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
