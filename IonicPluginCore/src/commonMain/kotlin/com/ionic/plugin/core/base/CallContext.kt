package com.ionic.plugin.core.base

interface CallContext {
    fun result(result: Result)

    class Result(status: Status, data: Any? = null) {
        enum class Status {
            OK,
            ERROR,
        }
    }
}
