package com.ionic.plugin.core.actions

import kotlinx.serialization.json.JsonObject

interface CallContext {
    fun getString(key: String): String?
    fun getObject(key: String): JsonObject?

    fun result(result: Result)

    class Result(val status: Status, val data: Any? = null) {
        enum class Status {
            OK,
            ERROR,
        }
    }
}
