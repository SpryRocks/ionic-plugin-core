package com.ionic.plugin.core.actions

import kotlin.reflect.KFunction1

interface IActionConstructor<
        TDelegate : Delegate,
        TAction : BaseAction<TDelegate>,
        TArgs,
        >
    : IActionCreator<TDelegate, TAction> {
    val action: KFunction1<TArgs, TAction>
    fun mapper(call: CallContext): TArgs

    override fun createAction(call: CallContext) = action(mapper(call))
}
