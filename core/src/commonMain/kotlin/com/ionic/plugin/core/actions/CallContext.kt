package com.ionic.plugin.core.actions

import com.spryrocks.kson.*
import kotlin.js.JsExport
import com.spryrocks.kson.utils.require

@JsExport
abstract class CallContext: IJsonObjectProperties {
    abstract fun optDouble(name: String): Double?

    abstract fun result(result: CallContextResult, finish: Boolean)

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

@JsExport
abstract class CallContextResult protected constructor() {
    companion object {
        fun success(data: JsonObject? = null) = Success(data)
        fun error(error: Throwable? = null) = Error(error)
    }

    class Success internal constructor(val data: JsonObject? = null) : CallContextResult()
    class Error internal constructor(val error: Throwable? = null) : CallContextResult()
}
