package com.spryrocks.kson

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class MutableJsonObject
internal constructor(
    override val map: MutableMap<String, JsonElement>,
) : JsonObject(map), IMutableJsonObject {
    constructor() : this(mutableMapOf())

    //region put
    override fun put(name: String, value: String) = putValueInternal(name, value)

    override fun put(name: String, value: Number) {
        when (value) {
            is Int -> put(name, value)
            is Float -> put(name, value)
            is Long -> put(name, value)
            else -> throw NotImplementedError("unknown type")
        }
    }

    override fun put(name: String, value: Int) = putValueInternal(name, value)

    override fun put(name: String, value: Float) = putValueInternal(name, value)

    override fun put(name: String, value: Long) = putValueInternal(name, value)

    override fun put(name: String, value: Boolean) = putValueInternal(name, value)

    override fun put(name: String, value: JsonObject) = putValueInternal(name, value.map)

    override fun put(name: String, value: JsonArray) = putValueInternal(name, value.list)

    override fun putNull(name: String) = putValueInternal<String?>(name, null)
    //endregion

    //region utils
    private inline fun <reified T> putValueInternal(name: String, value: T) =
        putInternal(name, Json.encodeToJsonElement(value))

    private fun putInternal(name: String, value: JsonElement): Unit = let { map[name] = value }
    //endregion
}
