package com.ionic.plugin.core.logger

class Logger(
    private val action: String?,
    private val tag: String?,
    private val pluginLogger: ILoggerRaw,
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
        errorInternal(message, null, params)
    }

    override fun error(error: Throwable, vararg params: LogParam) {
        errorInternal(null, error, params)
    }

    override fun error(message: String, error: Throwable, vararg params: LogParam) {
        errorInternal(message, error, params)
    }

    private fun errorInternal(message: String?, error: Throwable?, params: Array<out LogParam>) {
        pluginLogger.sendLog(action, tag, LogLevel.Error, message ?: error?.message ?: "Unknown error", params)
    }

    override fun trace(message: String, vararg params: LogParam) {
        pluginLogger.sendLog(action, tag, LogLevel.Trace, message, params)
    }

    override fun tag(tag: String): ILogger {
        return Logger(action, tag, pluginLogger)
    }
}
