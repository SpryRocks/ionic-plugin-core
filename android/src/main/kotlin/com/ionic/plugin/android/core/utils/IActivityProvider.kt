package com.ionic.plugin.android.core.utils

import android.app.Activity

interface IActivityProvider {
    val activity: Activity
}

interface WithActivityProvider: IActivityProvider, IContextProvider {
    val activityProvider: IActivityProvider
    override val activity get() = activityProvider.activity
    override val context get() = activity
}
