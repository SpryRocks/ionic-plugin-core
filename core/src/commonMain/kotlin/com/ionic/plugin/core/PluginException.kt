package com.ionic.plugin.core

import kotlin.js.JsExport

open class PluginExceptionBase(
    message: String? = null,
    cause: Throwable? = null,
) : RuntimeException(message, cause)

@JsExport
open class PluginException(
    message: String? = null,
    val code: Int? = null,
    cause: Throwable? = null,
) : PluginExceptionBase()

@JsExport
open class PluginExceptionNew(
    message: String? = null,
    val code: String? = null,
    cause: Throwable? = null,
) : PluginExceptionBase()
