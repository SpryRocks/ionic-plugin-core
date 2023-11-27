package com.spryrocks.kson

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonNull

class MutableJsonObject
internal constructor(
    override val map: MutableMap<String, JsonElement>,
) : JsonObject(map), IMutableJsonObject {
    constructor() : this(mutableMapOf())

    //region put
    override fun put(name: String, value: String) = putInternal(name, JsonPrimitive(value))

    override fun put(name: String, value: Number) = when (value) {
        is Int -> put(name, value)
        is Float -> put(name, value)
        is Long -> put(name, value)
        is Double -> put(name, value)
        else -> throw NotImplementedError("Unknown type: ${value::class.simpleName}")
    }

    override fun put(name: String, value: Int) = putInternal(name, JsonPrimitive(value))

    override fun put(name: String, value: Float) = putInternal(name, JsonPrimitive(value))

    override fun put(name: String, value: Long) = putInternal(name, JsonPrimitive(value))

    override fun put(name: String, value: Double) = putInternal(name, JsonPrimitive(value))

    override fun put(name: String, value: Boolean) = putInternal(name, JsonPrimitive(value))

    override fun put(name: String, value: JsonObject) = putInternal(name, kotlinx.serialization.json.JsonObject(value.map))

    override fun put(name: String, value: JsonArray) = putInternal(name, kotlinx.serialization.json.JsonArray(value.list))

    override fun putNull(name: String) = putInternal(name, JsonNull)

    override fun put(name: String, value: JsonValue) = when (value) {
        is String -> put(name, value)
        is Number -> put(name, value)
        is Boolean -> put(name, value)
        is JsonObject -> put(name, value)
        is JsonArray -> put(name, value)
        else -> throw NotImplementedError("Unknown type: ${value::class.simpleName}")
    }
    //endregion

    //region utils
    private fun putInternal(name: String, value: JsonElement): Unit = let { map[name] = value }
    //endregion
}
