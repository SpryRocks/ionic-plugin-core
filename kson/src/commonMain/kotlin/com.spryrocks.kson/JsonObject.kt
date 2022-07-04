package com.spryrocks.kson

import com.spryrocks.kson.utils.convertFromKJsonElement
import com.spryrocks.kson.utils.encodeToJsonObject
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

    fun getJsonArray(name: String) = JsonArray(map[name] as kotlinx.serialization.json.JsonArray)

    fun names() = map.keys

    fun get(name: String) = require(name, ::opt)

    fun opt(name: String): Any? = map[name]?.let { convertFromKJsonElement(it) }

    fun getInt(name: String) = require(name, ::optInt)

    fun optInt(name: String) = opt(name) as Int?

    fun getString(name: String) = require(name, ::optString)

    fun optString(name: String) = opt(name) as String?

    fun getNumber(name: String) = require(name, ::optNumber)

    fun optNumber(name: String) = opt(name) as Number?

    fun getBoolean(name: String) = require(name, ::optBoolean)

    fun optBoolean(name: String) = opt(name) as Boolean?

    private fun <T> require(name: String, block: (name: String) -> T?) =
        block(name) ?: throw Exception("value with name '${name}' is null")
}
