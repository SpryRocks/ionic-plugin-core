package com.ionic.plugin.core.actions

@JsExport
actual external interface Callback<TDelegate : Delegate, TAction : BaseAction<TDelegate>> {
    actual fun finishActionSafely(action: TAction)
}