package com.spryrocks.kson

import com.spryrocks.kson.utils.convertFromKJsonElement
import com.spryrocks.kson.utils.encodeToJsonArray
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

open class JsonArray
internal constructor(internal open val list: List<kotlinx.serialization.json.JsonElement?>): JsonElement() {
    companion object {
        fun fromJson(json: String) = fromList(Json.decodeFromString(json))

        fun fromList(list: List<kotlinx.serialization.json.JsonElement>) = JsonArray(list)

        inline fun <reified T> fromObject(data: T) = fromList(encodeToJsonArray(data))
    }

    override fun toString() = Json.encodeToString(list)

    fun mutate() = MutableJsonArray(list.toMutableList())

    val length get() = list.size

    fun getJsonObject(index: Int) = require(index, ::optJsonObject)

    fun optJsonObject(index: Int) = opt(index) as JsonObject?

    fun get(index: Int) = require(index, ::opt)

    fun opt(index: Int): Any? = list[index]?.let { convertFromKJsonElement(it) }

    private fun <T> require(index: Int, block: (index: Int) -> T?) =
        block(index) ?: throw Exception("value with index '${index}' is null")
}
