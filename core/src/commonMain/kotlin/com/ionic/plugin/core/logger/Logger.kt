package com.ionic.plugin.core.logger

class Logger(
    private val action: String?,
    private val tag: String?,
    private val pluginLogger: IPluginLogger,
) : ILogger {
    override fun warning(message: String, vararg params: LogParam) {
        pluginLogger.sendLog(action, tag, LogType.Warning, message, params)
    }

    override fun debug(message: String, vararg params: LogParam) {
        pluginLogger.sendLog(action, tag, LogType.Debug, message, params)
    }

    override fun info(message: String, vararg params: LogParam) {
        pluginLogger.sendLog(action, tag, LogType.Info, message, params)
    }

    override fun error(message: String, vararg params: LogParam) {
        pluginLogger.sendLog(action, tag, LogType.Error, message, params)
    }

    override fun tag(tag: String): ILogger {
        return Logger(action, tag, pluginLogger)
    }
}
