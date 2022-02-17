package com.ionic.plugin.core.base

class PluginResult(status: Status, data: Any) {
    enum class Status {
        OK,
        ERROR,
    }
}
