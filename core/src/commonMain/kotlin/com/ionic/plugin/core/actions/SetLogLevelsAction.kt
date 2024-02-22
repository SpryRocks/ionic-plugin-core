package com.ionic.plugin.core.actions

import com.spryrocks.kson.IJsonObjectProperties

open class SetLogLevelsAction<TDelegate : Delegate<TMappers>, TMappers : Mappers>(args: IJsonObjectProperties): BaseAction<TDelegate, TMappers>() {
    private val logLevels = args.optJsonArray("logLevels")

    override fun onExecute() {
        val logLevels = mappers.mapLogLevelsFromArg(logLevels)
        this.callback.setLogLevels(logLevels)
        success()
    }
}
