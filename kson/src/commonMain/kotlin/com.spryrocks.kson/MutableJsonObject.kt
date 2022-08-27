package com.spryrocks.kson

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class MutableJsonObject internal constructor(override val map: MutableMap<String, JsonElement>) : JsonObject(map) {
    constructor() : this(mutableMapOf())

    fun put(key: String, value: String) = putValueInternal(key, value)

    fun put(key: String, value: Number)  {
        when(value) {
            is Int -> put(key, value)
            is Float -> put(key, value)
            is Long -> put(key, value)
            else -> throw NotImplementedError("unknown type")
        }
    }

    fun put(key: String, value: Int) = putValueInternal(key, value)

    fun put(key: String, value: Float) = putValueInternal(key, value)

    fun put(key: String, value: Long) = putValueInternal(key, value)

    fun put(key: String, value: Boolean) = putValueInternal(key, value)

    fun put(key: String, value: JsonObject) = putValueInternal(key, value.map)

    fun put(key: String, value: JsonArray) = putValueInternal(key, value.list)

    private inline fun <reified T> putValueInternal(key: String, value: T) =
        putInternal(key, Json.encodeToJsonElement(value))

    private fun putInternal(key: String, value: JsonElement) = apply { map[key] = value }
}
