package com.spryrocks.kson

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement

inline fun <reified T> encodeToJsonObject(data: T): JsonObject {
    return Json.encodeToJsonElement(data) as? JsonObject
        ?: throw RuntimeException("Provided data is not JsonObject")
}
