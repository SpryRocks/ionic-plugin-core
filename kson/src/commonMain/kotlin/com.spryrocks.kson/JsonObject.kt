package com.spryrocks.kson

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

open class JsonObject protected constructor(internal open val map: Map<String, kotlinx.serialization.json.JsonElement>): JsonElement() {
    companion object {
        fun fromJson(json: String): JsonObject {
            val map = Json.decodeFromString<Map<String, kotlinx.serialization.json.JsonElement>>(json)
            return JsonObject(map)
        }
    }

    override fun toString(): String {
        return Json.encodeToString(map)
    }
}
