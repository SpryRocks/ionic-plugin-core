package com.ionic.plugin.core.actions

import com.spryrocks.kson.JsonArray
import com.spryrocks.kson.JsonObject

actual interface CallContext {
    actual fun getString(key: String): String?
    actual fun getInt(key: String): Int?
    actual fun getJsonObject(key: String): JsonObject?
    actual fun getBoolean(key: String): Boolean?
    actual fun getDouble(key: String): Double?
    actual fun getJsonArray(key: String): JsonArray?

    actual fun result(result: CallContextResult, finish: Boolean)
}
