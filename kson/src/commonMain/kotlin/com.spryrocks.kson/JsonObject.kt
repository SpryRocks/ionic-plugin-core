package com.spryrocks.kson

import com.spryrocks.kson.utils.convertFromKJsonElement
import com.spryrocks.kson.utils.encodeToJsonObject
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.spryrocks.kson.utils.require

open class JsonObject
internal constructor(
    internal open val map: Map<String, kotlinx.serialization.json.JsonElement>,
) : JsonElement(), IJsonObject {
    companion object {
        fun fromJson(json: String) = fromMap(Json.decodeFromString(json))

        fun fromMap(map: Map<String, kotlinx.serialization.json.JsonElement>) = JsonObject(map)

        inline fun <reified T> fromObject(data: T) = fromMap(encodeToJsonObject(data))
    }

    //region opt
    override fun opt(name: String) = map[name]?.let { convertFromKJsonElement(it) }

    override fun optString(name: String) = opt(name) as String?

    override fun optNumber(name: String) = opt(name) as Number?

    override fun optInt(name: String) = opt(name) as Int?

    override fun optFloat(name: String) = opt(name) as Float?

    override fun optLong(name: String) = opt(name) as Long?

    override fun optBoolean(name: String) = opt(name) as Boolean?

    override fun optJsonObject(name: String) = opt(name) as JsonObject?

    override fun optJsonArray(name: String) = opt(name) as JsonArray?
    //endregion

    //region get
    override fun get(name: String) = require(name, ::opt)

    override fun getString(name: String) = require(name, ::optString)

    override fun getNumber(name: String) = require(name, ::optNumber)

    override fun getInt(name: String) = require(name, ::optInt)

    override fun getFloat(name: String) = require(name, ::optFloat)

    override fun getLong(name: String) = require(name, ::optLong)

    override fun getBoolean(name: String) = require(name, ::optBoolean)

    override fun getJsonObject(name: String) = require(name, ::optJsonObject)

    override fun getJsonArray(name: String) = require(name, ::optJsonArray)
    //endregion

    override val names: Set<String> get() = map.keys

    override fun mutate() = MutableJsonObject(map.toMutableMap())

    override fun has(key: String) = map.containsKey(key)

    override fun toString() = Json.encodeToString(map)
}
