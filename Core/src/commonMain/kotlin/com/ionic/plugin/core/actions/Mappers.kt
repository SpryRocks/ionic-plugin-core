package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import com.ionic.plugin.core.base.PluginResult
import kotlinx.serialization.json.JsonObject

object Mappers {
    @Throws(PluginException::class)
    fun mapObjectToJson(src: Any): JsonObject {
//        val gson = Gson()
//        val jsonString: String = gson.toJson(src)
//        return try {
//            JSONObject(jsonString)
//        } catch (e: JSONException) {
//            throw PluginException("Cannot convert object to json", e)
//        }

        throw Exception("Not implemented")
    }

    interface IErrorMapper {
        fun map(error: PluginException): PluginResult
    }

    class DefaultErrorMapper : IErrorMapper {
        override fun map(e: PluginException): PluginResult {
            return PluginResult(PluginResult.Status.ERROR, e.message.orEmpty())
        }
    }
}
