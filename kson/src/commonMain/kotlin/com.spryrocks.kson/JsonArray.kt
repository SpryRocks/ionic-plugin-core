package com.spryrocks.kson

import com.spryrocks.kson.utils.JsonArrayIterator
import com.spryrocks.kson.utils.convertFromKJsonElement
import com.spryrocks.kson.utils.encodeToJsonArray
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

open class JsonArray
internal constructor(
    internal open val list: List<kotlinx.serialization.json.JsonElement?>,
): JsonElement(), IJsonArray {
    companion object {
        fun fromJson(json: String) = fromList(Json.decodeFromString(json))

        fun fromList(list: List<kotlinx.serialization.json.JsonElement>) = JsonArray(list)

        inline fun <reified T> fromObject(data: T) = fromList(encodeToJsonArray(data))
    }

    //region opt
    override fun opt(index: Int) = list[index]?.let { convertFromKJsonElement(it) }

    override fun optString(index: Int) = opt(index) as String?

    override fun optNumber(index: Int) = opt(index) as Number?

    override fun optInt(index: Int) = opt(index) as Int?

    override fun optFloat(index: Int) = opt(index) as Float?

    override fun optLong(index: Int) = opt(index) as Long?

    override fun optBoolean(index: Int) = opt(index) as Boolean?

    override fun optJsonObject(index: Int) = opt(index) as JsonObject?

    override fun optJsonArray(index: Int) = opt(index) as JsonArray
    //endregion

    //region get
    override fun get(index: Int) = require(index, ::opt)

    override fun getString(index: Int) = require(index, ::optString)

    override fun getNumber(index: Int) = require(index, ::optNumber)

    override fun getInt(index: Int) = require(index, ::optInt)

    override fun getFloat(index: Int) = require(index, ::optFloat)

    override fun getLong(index: Int) = require(index, ::optLong)

    override fun getBoolean(index: Int) = require(index, ::optBoolean)

    override fun getJsonObject(index: Int) = require(index, ::optJsonObject)

    override fun getJsonArray(index: Int) = require(index, ::optJsonArray)
    //endregion

    override val size get() = list.size

    override fun mutate() = MutableJsonArray(list.toMutableList())

    override fun toString() = Json.encodeToString(list)

    override fun iterator() = JsonArrayIterator(this)

    //region helpers
    private fun <T> require(index: Int, block: (index: Int) -> T?) =
        block(index) ?: throw Exception("value with index '${index}' is null")
    //endregion
}
