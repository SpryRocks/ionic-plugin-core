package com.ionic.plugin.core.actions

import kotlin.js.JsExport

expect interface CallContext {
    fun getString(key: String): String?
    fun getObject(key: String): String?

    fun result(result: CallContextResult)
}

@JsExport
class CallContextResult(val ok: Boolean, val data: Any? = null)
