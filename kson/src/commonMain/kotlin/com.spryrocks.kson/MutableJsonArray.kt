package com.spryrocks.kson

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonNull

class MutableJsonArray
internal constructor(
    override val list: MutableList<JsonElement>,
) : JsonArray(list), IMutableJsonArray {
    constructor() : this(mutableListOf())

    //region put
    override fun put(value: String, index: Int?) = putInternal(JsonPrimitive(value), index)

    override fun put(value: Number, index: Int?) {
        when (value) {
            is Int -> put(value, index)
            is Float -> put(value, index)
            is Long -> put(value, index)
            else -> throw NotImplementedError("unknown type")
        }
    }

    override fun put(value: Int, index: Int?) = putInternal(JsonPrimitive(value), index)

    override fun put(value: Float, index: Int?) = putInternal(JsonPrimitive(value), index)

    override fun put(value: Long, index: Int?) = putInternal(JsonPrimitive(value), index)

    override fun put(value: Boolean, index: Int?) = putInternal(JsonPrimitive(value), index)

    override fun put(value: JsonObject, index: Int?) =
        putInternal(kotlinx.serialization.json.JsonObject(value.map), index)

    override fun put(value: JsonArray, index: Int?) =
        putInternal(kotlinx.serialization.json.JsonArray(value.list), index)

    override fun putNull(index: Int?) = putInternal(JsonNull, index)

    override fun put(value: JsonValue, index: Int?) = when (value) {
        is String -> put(value, index)
        is Number -> put(value, index)
        is Boolean -> put(value, index)
        is JsonObject -> put(value, index)
        is JsonArray -> put(value, index)
        else -> throw NotImplementedError("unknown type")
    }
    //endregion

    //region helpers
    private fun putInternal(value: JsonElement, index: Int?) = let {
        list += value
        // todo: insert ordinal
    }
    //endregion
}
