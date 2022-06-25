package com.spryrocks.kson

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class MutableJsonObject private constructor(override val map: MutableMap<String, JsonElement>): JsonObject(map) {
    constructor(): this(mutableMapOf())

    fun put(key: String, value: Boolean) {
        putValueInternal(key, value)
    }

    fun put(key: String, value: JsonObject) {
        putValueInternal(key, value.map)
    }

    private inline fun <reified T> putValueInternal(key: String, value: T) {
        putInternal(key, Json.encodeToJsonElement(value))
    }

    private fun putInternal(key: String, value: JsonElement) {
        map[key] = value
    }
}
