package com.ionic.plugin.core.actions

interface Callback<TDelegate : Delegate, TAction : BaseAction<TDelegate>> {
    fun finishActionSafely(action: TAction)
    val errorMapper: IErrorMapper
}
