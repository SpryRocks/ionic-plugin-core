package com.ionic.plugin.core

import com.spryrocks.kson.JsonObject

interface WrapperDelegate {
    fun sendEvent(name: String, data: JsonObject)
}
