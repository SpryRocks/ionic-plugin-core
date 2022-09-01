package com.spryrocks.kson

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class MutableJsonArray
internal constructor(
    override val list: MutableList<JsonElement?>,
) : JsonArray(list), IMutableJsonArray {
    constructor() : this(mutableListOf())

    //region put
    override fun put(value: String, index: Int?) = putValueInternal(value, index)

    override fun put(value: Number, index: Int?) {
        when (value) {
            is Int -> put(value, index)
            is Float -> put(value, index)
            is Long -> put(value, index)
            else -> throw NotImplementedError("unknown type")
        }
    }

    override fun put(value: Int, index: Int?) = putValueInternal(value, index)

    override fun put(value: Float, index: Int?) = putValueInternal(value, index)

    override fun put(value: Long, index: Int?) = putValueInternal(value, index)

    override fun put(value: Boolean, index: Int?) = putValueInternal(value, index)

    override fun put(value: JsonObject, index: Int?) = putValueInternal(value.map, index)

    override fun put(value: JsonArray, index: Int?) = putValueInternal(value.list, index)

    override fun putNull(index: Int?) = putValueInternal<String?>(null, index)
    //endregion

    //region helpers
    private inline fun <reified T> putValueInternal(value: T, index: Int?) = putInternal(Json.encodeToJsonElement(value), index)

    private fun putInternal(value: JsonElement, index: Int?) = let {
        list += value
        // todo: insert ordinal
    }
    //endregion
}
