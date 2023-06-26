package com.ionic.plugin.android.core

import com.ionic.plugin.android.core.utils.IActivityProvider
import com.spryrocks.kson.JsonObject

interface WrapperDelegate : com.ionic.plugin.core.WrapperDelegate, IActivityProvider {
    fun sendEvent(name: String, data: JsonObject)
}
