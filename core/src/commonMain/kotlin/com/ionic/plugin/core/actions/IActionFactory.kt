package com.ionic.plugin.core.actions

interface IActionCreator<TDelegate : Delegate, TAction : BaseAction<TDelegate>> {
    fun createAction(call: CallContext): TAction
}

interface IActionFactory<TActions, TDelegate : Delegate, TAction : BaseAction<TDelegate>>  {
    fun createAction(action: TActions, call: CallContext): TAction
}
