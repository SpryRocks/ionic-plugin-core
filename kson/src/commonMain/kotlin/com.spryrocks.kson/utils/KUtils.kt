package com.spryrocks.kson.utils

import kotlinx.serialization.json.*

inline fun <reified T> encodeToJsonObject(data: T): JsonObject {
    return Json.encodeToJsonElement(data) as? JsonObject
        ?: throw RuntimeException("Provided data is not JsonObject")
}

inline fun <reified T> encodeToJsonArray(data: T): JsonArray {
    return Json.encodeToJsonElement(data) as? JsonArray
        ?: throw RuntimeException("Provided data is not JsonArray")
}
