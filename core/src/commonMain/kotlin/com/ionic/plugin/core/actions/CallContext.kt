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

    abstract class AsObject: IJsonObjectProperties {
        abstract fun optDouble(name: String): Double?

        // require
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
