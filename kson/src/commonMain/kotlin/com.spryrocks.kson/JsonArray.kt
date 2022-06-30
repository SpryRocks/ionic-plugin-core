package com.spryrocks.kson

import com.spryrocks.kson.utils.encodeToJsonArray
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

open class JsonArray
internal constructor(internal open val list: List<kotlinx.serialization.json.JsonElement>): JsonElement() {
    companion object {
        fun fromJson(json: String) = fromList(Json.decodeFromString(json))

        fun fromList(list: List<kotlinx.serialization.json.JsonElement>) = JsonArray(list)

        inline fun <reified T> fromObject(data: T) = fromList(encodeToJsonArray(data))
    }

    override fun toString() = Json.encodeToString(list)

    fun mutate() = MutableJsonArray(list.toMutableList())

    fun length() = list.size

    fun getJsonObject(index: Int) = JsonObject(list[index] as kotlinx.serialization.json.JsonObject)
}
