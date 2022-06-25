package com.ionic.plugin.core.actions

import com.spryrocks.kson.JsonObject
import kotlin.js.JsExport

expect interface CallContext {
    fun getString(key: String): String?
    fun getObject(key: String): JsonObject?

    fun result(result: CallContextResult)
}

@JsExport
class CallContextResult(val ok: Boolean, val data: Any? = null)
