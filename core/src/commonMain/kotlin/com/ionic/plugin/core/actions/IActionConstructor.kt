package com.ionic.plugin.core.actions

import kotlin.reflect.KFunction1

interface IActionConstructor<
        TDelegate : Delegate<TMappers>,
        TAction : BaseAction<TDelegate, TMappers>,
        TMappers : Mappers,
        TArgs,
        >
    : IActionCreator<TDelegate, TAction, TMappers> {
    val action: KFunction1<TArgs, TAction>
    fun mapper(call: CallContext): TArgs

    override fun createAction(call: CallContext) = action(mapper(call))
}
