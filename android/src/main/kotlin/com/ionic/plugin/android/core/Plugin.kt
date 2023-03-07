package com.ionic.plugin.android.core

import android.app.Activity
import android.content.Context
import com.ionic.plugin.core.actions.Mappers
import com.ionic.plugin.core.actions.Delegate as CoreDelegate

abstract class Plugin<TActionKey, TDelegate : CoreDelegate<TMappers>, TMappers : Mappers> :
    com.ionic.plugin.core.Plugin<TActionKey, TDelegate, TMappers>() {
    protected override val wrapperDelegate: WrapperDelegate
        get() = super.wrapperDelegate as WrapperDelegate

    open val activity: Activity get() = wrapperDelegate.activity
    val context: Context get() = activity
}
