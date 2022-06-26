package com.ionic.plugin.core.actions

expect interface Callback<TDelegate : Delegate, TAction : BaseAction<TDelegate>> {
    fun finishActionSafely(action: TAction)
    val errorMapper: IErrorMapper
}
