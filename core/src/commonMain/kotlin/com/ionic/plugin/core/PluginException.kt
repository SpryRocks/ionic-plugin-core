package com.ionic.plugin.core

import kotlin.js.JsExport

@JsExport
open class PluginException(message: String? = null, val code: Int? = null, cause: Throwable? = null) : RuntimeException(message, cause) {
}
