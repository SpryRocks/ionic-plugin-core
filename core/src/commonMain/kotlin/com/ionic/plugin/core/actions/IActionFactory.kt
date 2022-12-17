package com.ionic.plugin.core.actions

interface IActionCreator<
        TDelegate : Delegate<TMappers>,
        TAction : BaseAction<TDelegate, TMappers>,
        TMappers : Mappers,
        > {
    fun createAction(call: CallContext): TAction
}

interface IActionFactory<
        TActions, TDelegate : Delegate<TMappers>,
        TAction : BaseAction<TDelegate, TMappers>,
        TMappers : Mappers,
        > {
    fun createAction(action: TActions, call: CallContext): TAction
}
