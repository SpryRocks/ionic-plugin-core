package com.ionic.plugin.core

import kotlin.js.JsExport

open class PluginExceptionBase(
    message: String?,
    cause: Throwable?,
) : RuntimeException(message, cause)

@JsExport
open class PluginException(
    message: String? = null,
    val code: Int? = null,
    cause: Throwable? = null,
) : PluginExceptionBase(message, cause)

@JsExport
open class PluginExceptionNew(
    message: String? = null,
    val code: String? = null,
    cause: Throwable? = null,
) : PluginExceptionBase(message, cause)
