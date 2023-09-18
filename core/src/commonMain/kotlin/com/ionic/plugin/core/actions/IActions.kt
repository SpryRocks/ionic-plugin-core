package com.ionic.plugin.core.actions

interface IActions<TDelegate : Delegate<TMappers>, TMappers : Mappers> {
    fun beforeActionRun(action: BaseAction<TDelegate, TMappers>) {}
    fun actionFinished(action: BaseAction<TDelegate, TMappers>) {}
}
