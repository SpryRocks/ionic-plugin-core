package com.ionic.plugin.core.logger

import com.ionic.plugin.core.PluginException

interface WithLogger {
    fun logger(tag: String? = null): ILogger
}

typealias LogParam = Pair<String, Any?>

enum class LogLevel(val value: String) {
    Warning("Warning"),
    Debug("Debug"),
    Info("Info"),
    Error("Error"),
    Trace("Trace"),

    ;

    companion object {
        fun fromValue(value: String) =
            LogLevel.entries
                .find { it.value === value }
                ?: throw PluginException("Log level '$value' not exists")
    }
}

interface ILogger {
    fun warning(message: String, vararg params: LogParam)
    fun debug(message: String, vararg params: LogParam)
    fun info(message: String, vararg params: LogParam)
    fun error(message: String, vararg params: LogParam)
    fun error(error: Throwable, vararg params: LogParam)
    fun error(message: String, error: Throwable, vararg params: LogParam)
    fun trace(message: String, vararg params: LogParam)
    fun tag(tag: String): ILogger
}

interface ILoggerRaw {
    fun sendLog(action: String?, tag: String?, level: LogLevel, message: String, params: Array<out LogParam>)
}
