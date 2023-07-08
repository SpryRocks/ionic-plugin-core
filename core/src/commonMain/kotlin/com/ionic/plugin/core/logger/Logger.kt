package com.ionic.plugin.core.logger

class Logger(
    private val action: String?,
    private val tag: String?,
    private val pluginLogger: IPluginLogger,
) : ILogger {
    override fun warning(message: String, vararg params: LogParam) {
        pluginLogger.sendLog(action, tag, LogLevel.Warning, message, params)
    }

    override fun debug(message: String, vararg params: LogParam) {
        pluginLogger.sendLog(action, tag, LogLevel.Debug, message, params)
    }

    override fun info(message: String, vararg params: LogParam) {
        pluginLogger.sendLog(action, tag, LogLevel.Info, message, params)
    }

    override fun error(message: String, vararg params: LogParam) {
        pluginLogger.sendLog(action, tag, LogLevel.Error, message, params)
    }

    override fun tag(tag: String): ILogger {
        return Logger(action, tag, pluginLogger)
    }
}
