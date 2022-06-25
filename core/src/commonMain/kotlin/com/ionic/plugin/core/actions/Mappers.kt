package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlin.js.JsExport

@JsExport
open class Mappers {
}

@JsExport
abstract class IErrorMapper {
    abstract fun map(error: PluginException): CallContextResult
}

@JsExport
class DefaultErrorMapper : IErrorMapper() {
    override fun map(error: PluginException): CallContextResult {
        return CallContextResult(false, error.message.orEmpty())
    }
}
