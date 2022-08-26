package com.ionic.plugin.android.core

import android.app.Activity

interface WrapperDelegate: com.ionic.plugin.core.WrapperDelegate {
    val activity: Activity
}
