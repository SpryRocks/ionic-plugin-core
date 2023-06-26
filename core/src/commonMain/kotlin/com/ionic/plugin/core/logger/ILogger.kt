package com.ionic.plugin.core.logger

interface WithLogger {
    fun logger(tag: String? = null): ILogger
}

typealias LogParam = Pair<String, Any>

enum class LogType(val value: String) {
    Warning("Warning"),
    Debug("Warning"),
    Info("Info"),
    Error("Error"),
}

interface ILogger {
    fun warning(message: String, vararg params: LogParam)
    fun debug(message: String, vararg params: LogParam)
    fun info(message: String, vararg params: LogParam)
    fun error(message: String, vararg params: LogParam)
    fun tag(tag: String): ILogger
}

interface IPluginLogger {
    fun sendLog(action: String?, tag: String?, type: LogType, message: String, params: Array<out LogParam>)
}
