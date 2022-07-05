package com.spryrocks.kson

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class MutableJsonArray internal constructor(override val list: MutableList<JsonElement>) : JsonArray(list) {
    constructor() : this(mutableListOf())

    fun put(value: Int) = putValueInternal(value)

    fun put(value: JsonObject) = putValueInternal(value.map)

    private inline fun <reified T> putValueInternal(value: T) = putInternal(Json.encodeToJsonElement(value))

    private fun putInternal(value: JsonElement) = apply { list += value }
}
