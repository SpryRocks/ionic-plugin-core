package com.ionic.plugin.android.core.utils

import android.app.Activity
import android.app.Application

interface IActivityProvider {
    val activity: Activity
}

interface WithActivityProvider:
    IActivityProvider,
    IContextProvider,
    IApplicationProvider {
    val activityProvider: IActivityProvider
    override val activity get() = activityProvider.activity
    override val context get() = activity
    override val application: Application get() = activity.application
}
