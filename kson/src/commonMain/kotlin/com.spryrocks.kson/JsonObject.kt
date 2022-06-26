package com.spryrocks.kson

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

open class JsonObject
internal constructor(internal open val map: Map<String, kotlinx.serialization.json.JsonElement>) : JsonElement() {
    companion object {
        fun fromJson(json: String) = fromMap(Json.decodeFromString(json))

        fun fromMap(map: Map<String, kotlinx.serialization.json.JsonElement>) = JsonObject(map)

        inline fun <reified T> fromObject(data: T) = fromMap(encodeToJsonObject(data))
    }

    override fun toString() = Json.encodeToString(map)

    fun mutate() = MutableJsonObject(map.toMutableMap())
}
