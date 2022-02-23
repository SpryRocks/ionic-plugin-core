package com.ionic.plugin.android.capacitor.core.actions

import com.getcapacitor.JSObject
import com.getcapacitor.PluginCall
import com.ionic.plugin.android.core.actions.CallContext
import com.ionic.plugin.core.actions.CallContextResult
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

class CallContext(private val call: PluginCall, wrapperDelegate: WrapperDelegate) : CallContext(wrapperDelegate) {
    override fun getString(key: String): String? {
        return call.getString(key)
    }

    override fun getObject(key: String): String? {
        return call.getObject(key)?.toString()
    }

    override fun result(result: CallContextResult) {
        if (result.ok) {
            val data = getResultJsonObject(result)
            call.resolve(data)
        } else {
            call.reject("Error")
        }
    }

    private fun getResultJsonObject(result: CallContextResult): JSObject? {
        if (result.data == null) return null
        return JSObject(result.data.toString())
    }
}
