package com.ionic.plugin.core.actions

actual interface Callback<TDelegate : Delegate, TAction : BaseAction<TDelegate>> {
    actual fun finishActionSafely(action: TAction)
}