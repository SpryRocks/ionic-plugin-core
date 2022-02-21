package com.ionic.plugin.android.capacitor.core.actions

import com.ionic.plugin.core.actions.CallContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

open class CallContextWrapper(private val delegate: CallContextWrapperDelegate) : CallContext {
    override fun getString(key: String): String? {
        return delegate.getString(key)
    }

    override fun getObject(key: String): JsonObject? {
        val object_ = delegate.getObject(key) ?: return null
        return Json.decodeFromString(object_)
    }

    override fun result(result: CallContext.Result) {
        delegate.result(CallContextWrapperDelegate.Result(result.status, result.data.toString()))
    }

    interface CallContextWrapperDelegate {
        fun getString(key: String): String?
        fun getObject(key: String): String?

        fun result(result: Result)

        data class Result(val status: CallContext.Result.Status, val json: String? = null)
    }
}
