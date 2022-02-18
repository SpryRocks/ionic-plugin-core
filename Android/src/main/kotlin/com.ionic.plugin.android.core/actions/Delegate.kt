package com.ionic.plugin.android.core.actions

import android.app.Activity
import android.content.Context

interface Delegate : com.ionic.plugin.core.actions.Delegate {
    fun getContext(): Context

//    val threadPool: ExecutorService

    val activity: Activity

//    fun startActivityForResult(intent: Intent, requestCode: Int)
//    fun finishActivity(requestCode: Int)
}
