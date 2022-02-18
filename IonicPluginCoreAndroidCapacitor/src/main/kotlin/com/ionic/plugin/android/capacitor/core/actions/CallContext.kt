package com.ionic.plugin.android.capacitor.core.actions

import com.getcapacitor.JSObject
import com.getcapacitor.PluginCall
import com.ionic.plugin.android.core.actions.CallContext
import com.ionic.plugin.core.actions.CallContext.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

class CallContext(private val call: PluginCall, wrapperDelegate: WrapperDelegate) : CallContext(wrapperDelegate) {
    override fun getString(key: String): String? {
        return call.getString(key)
    }

    override fun getObject(key: String): JsonObject? {
        return Json.decodeFromString(call.getObject(key).toString())
    }

    override fun result(result: Result) {
        if(result.status == Result.Status.OK){
            val data = JSObject(result.data.toString())
            call.resolve(data)
        } else {
            call.reject("Error")
        }
    }
}
