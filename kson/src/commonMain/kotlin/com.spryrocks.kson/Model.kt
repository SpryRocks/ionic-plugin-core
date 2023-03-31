package com.spryrocks.kson

import kotlin.js.JsExport

typealias JsonValue = Any

interface IJsonElement

@JsExport
interface IJsonObjectProperties {
    fun opt(name: String): JsonValue?
    fun optString(name: String): String?
    fun optNumber(name: String): Number?
    fun optInt(name: String): Int?
    fun optFloat(name: String): Float?
    fun optLong(name: String): Long?
    fun optBoolean(name: String): Boolean?
    fun optJsonObject(name: String): JsonObject?
    fun optJsonArray(name: String): JsonArray?

    fun get(name: String): JsonValue
    fun getString(name: String): String
    fun getNumber(name: String): Number
    fun getInt(name: String): Int
    fun getFloat(name: String): Float
    fun getLong(name: String): Long
    fun getBoolean(name: String): Boolean
    fun getJsonObject(name: String): JsonObject
    fun getJsonArray(name: String): JsonArray
}

interface IJsonObject : IJsonElement, IJsonObjectProperties {
    val names: Set<String>

    fun mutate(): MutableJsonObject

    fun has(key: String): Boolean

    override fun toString(): String
}

interface IJsonArrayProperties {
    val size: Int

    fun opt(index: Int): JsonValue?
    fun optString(index: Int): String?
    fun optNumber(index: Int): Number?
    fun optInt(index: Int): Int?
    fun optFloat(index: Int): Float?
    fun optLong(index: Int): Long?
    fun optBoolean(index: Int): Boolean?
    fun optJsonObject(index: Int): JsonObject?
    fun optJsonArray(index: Int): JsonArray?

    fun get(index: Int): JsonValue
    fun getString(index: Int): String
    fun getNumber(index: Int): Number
    fun getInt(index: Int): Int
    fun getFloat(index: Int): Float
    fun getLong(index: Int): Long
    fun getBoolean(index: Int): Boolean
    fun getJsonObject(index: Int): JsonObject
    fun getJsonArray(index: Int): JsonArray
}

interface IJsonArray : IJsonElement, IJsonArrayProperties, Iterable<JsonValue> {
    fun mutate(): IMutableJsonArray

    override fun toString(): String
}

interface IMutableJsonObject : IJsonObject {
    fun put(name: String, value: String)
    fun put(name: String, value: Number)
    fun put(name: String, value: Int)
    fun put(name: String, value: Float)
    fun put(name: String, value: Long)
    fun put(name: String, value: Boolean)
    fun put(name: String, value: JsonObject)
    fun put(name: String, value: JsonArray)
    fun putNull(name: String)
    fun put(name: String, value: JsonValue)
}

interface IMutableJsonArray : IJsonArray {
    fun put(value: String, index: Int? = null)
    fun put(value: Number, index: Int? = null)
    fun put(value: Int, index: Int? = null)
    fun put(value: Float, index: Int? = null)
    fun put(value: Long, index: Int? = null)
    fun put(value: Boolean, index: Int? = null)
    fun put(value: JsonObject, index: Int? = null)
    fun put(value: JsonArray, index: Int? = null)
    fun putNull(index: Int? = null)
    fun put(value: JsonValue, index: Int? = null)
}
