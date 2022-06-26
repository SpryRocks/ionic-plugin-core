package com.ionic.plugin.core.actions

import com.spryrocks.kson.JsonObject

@JsExport
actual external interface CallContext {
    actual fun getString(key: String): String?
    actual fun getInt(key: String): Int?
    actual fun getObject(key: String): JsonObject?
    actual fun getBoolean(key: String): Boolean?
    actual fun getDouble(key: String): Double?

    actual fun result(result: CallContextResult, finish: Boolean)
}
