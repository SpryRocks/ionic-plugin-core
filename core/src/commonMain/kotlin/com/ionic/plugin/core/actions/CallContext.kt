package com.ionic.plugin.core.actions

import com.spryrocks.kson.*
import kotlin.js.JsExport
import com.spryrocks.kson.utils.require

@JsExport
abstract class CallContext {
    abstract fun asObject(): AsObject

    abstract fun asArray(): AsArray

    // result
    abstract fun success(data: Any?, finish: Boolean)

    abstract fun error(error: Throwable?, finish: Boolean)

    data class ValueWrapper<T>(val value: T?)

    abstract class AsObject: IJsonObjectProperties {
        fun getDouble(name: String) = require(name, ::optDouble)
        override fun get(name: String) = require(name, ::opt)
        override fun getString(name: String) = require(name, ::optString)
        override fun getNumber(name: String) = require(name, ::optNumber)
        override fun getInt(name: String) = require(name, ::optInt)
        override fun getFloat(name: String) = require(name, ::optFloat)
        override fun getLong(name: String) = require(name, ::optLong)
        override fun getBoolean(name: String) = require(name, ::optBoolean)
        override fun getJsonObject(name: String) = require(name, ::optJsonObject)
        override fun getJsonArray(name: String) = require(name, ::optJsonArray)

        fun optDouble(name: String) = optDouble_(name).value
        override fun opt(name: String) = opt_(name).value
        override fun optString(name: String) = optString_(name).value
        override fun optNumber(name: String) = optNumber_(name).value
        override fun optInt(name: String) = optInt_(name).value
        override fun optFloat(name: String) = optFloat_(name).value
        override fun optLong(name: String) = optLong_(name).value
        override fun optBoolean(name: String) = optBoolean_(name).value
        override fun optJsonObject(name: String) = optJsonObject_(name).value
        override fun optJsonArray(name: String) = optJsonArray_(name).value

        @Throws(Throwable::class)
        abstract fun optDouble_(name: String): ValueWrapper<Double>
        @Throws(Throwable::class)
        abstract fun opt_(name: String): ValueWrapper<JsonValue>
        @Throws(Throwable::class)
        abstract fun optString_(name: String): ValueWrapper<String>
        @Throws(Throwable::class)
        abstract fun optNumber_(name: String): ValueWrapper<Number>
        @Throws(Throwable::class)
        abstract fun optInt_(name: String): ValueWrapper<Int>
        @Throws(Throwable::class)
        abstract fun optFloat_(name: String): ValueWrapper<Float>
        @Throws(Throwable::class)
        abstract fun optLong_(name: String): ValueWrapper<Long>
        @Throws(Throwable::class)
        abstract fun optBoolean_(name: String): ValueWrapper<Boolean>
        @Throws(Throwable::class)
        abstract fun optJsonObject_(name: String): ValueWrapper<JsonObject>
        @Throws(Throwable::class)
        abstract fun optJsonArray_(name: String): ValueWrapper<JsonArray>
    }

    abstract class AsArray: IJsonArrayProperties {
        abstract fun optDouble(index: Int): Double?

        // require
        fun getDouble(index: Int) = require(index, ::optDouble)
        override fun get(index: Int) = require(index, ::opt)
        override fun getString(index: Int) = require(index, ::optString)
        override fun getNumber(index: Int) = require(index, ::optNumber)
        override fun getInt(index: Int) = require(index, ::optInt)
        override fun getFloat(index: Int) = require(index, ::optFloat)
        override fun getLong(index: Int) = require(index, ::optLong)
        override fun getBoolean(index: Int) = require(index, ::optBoolean)
        override fun getJsonObject(index: Int) = require(index, ::optJsonObject)
        override fun getJsonArray(index: Int) = require(index, ::optJsonArray)
    }
}
