package com.ionic.plugin.core

import kotlin.js.JsExport

@JsExport
open class PluginException(message: String?, cause: Throwable? = null) : Throwable(message, cause) {
}
