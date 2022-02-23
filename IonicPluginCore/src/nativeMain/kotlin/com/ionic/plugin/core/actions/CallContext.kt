package com.ionic.plugin.core.actions

actual interface CallContext {
    actual fun getString(key: String): String?
    actual fun getObject(key: String): String?
    actual fun result(result: CallContextResult)

}