package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import kotlin.js.JsExport

@JsExport
open class Mappers

@JsExport
interface IErrorMapper {
    fun map(error: Throwable): PluginException
}

@JsExport
class DefaultErrorMapper : IErrorMapper {
    override fun map(error: Throwable): PluginException {
        if (error is PluginException) return error
        return PluginException(error.message, cause = error)
    }
}
