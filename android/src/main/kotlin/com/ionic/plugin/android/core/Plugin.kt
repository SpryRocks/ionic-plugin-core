package com.ionic.plugin.android.core

import android.app.Activity
import android.content.Context
import com.ionic.plugin.android.core.utils.IActivityProvider
import com.ionic.plugin.android.core.utils.IContextProvider
import com.ionic.plugin.core.actions.Mappers
import com.ionic.plugin.core.actions.Delegate as CoreDelegate

abstract class Plugin<TActionKey, TDelegate : CoreDelegate<TMappers>, TMappers : Mappers>
    : com.ionic.plugin.core.Plugin<TActionKey, TDelegate, TMappers>(),
    IActivityProvider, IContextProvider {
    override val wrapperDelegate: WrapperDelegate
        get() = super.wrapperDelegate as WrapperDelegate

    override val activity: Activity get() = wrapperDelegate.activity
    override val context: Context get() = activity
}
