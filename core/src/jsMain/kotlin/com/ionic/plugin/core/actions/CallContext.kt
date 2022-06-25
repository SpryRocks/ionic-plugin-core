package com.ionic.plugin.core.actions

import com.spryrocks.kson.JsonObject

@JsExport
actual external interface CallContext {
    actual fun getString(key: String): String?
    actual fun getObject(key: String): JsonObject?
    actual fun result(result: CallContextResult)
}
