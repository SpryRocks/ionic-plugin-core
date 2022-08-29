package com.ionic.plugin.core.actions

import com.spryrocks.kson.IJsonObjectProperties

actual interface CallContext: IJsonObjectProperties {
    actual fun result(result: CallContextResult, finish: Boolean)
}
