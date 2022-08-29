package com.ionic.plugin.core.actions

import com.spryrocks.kson.IJsonObjectProperties

@JsExport
actual interface CallContext: IJsonObjectProperties {
    actual fun result(result: CallContextResult, finish: Boolean)
}
