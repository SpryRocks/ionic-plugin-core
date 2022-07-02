package com.ionic.plugin.core.actions

import kotlin.reflect.KFunction1

interface IActionConstructor<TDelegate: Delegate, TAction: BaseAction<TDelegate>>: IActionCreator<TDelegate, TAction> {
    val action: KFunction1<CallContext, TAction>

    override fun createAction(call: CallContext) = action(call)
}
