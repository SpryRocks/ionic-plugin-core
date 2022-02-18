package com.ionic.plugin.android.core.actions

import android.content.Context

interface Delegate : com.ionic.plugin.core.actions.Delegate, WrapperDelegate {
    val context: Context

//    val threadPool: ExecutorService

//    fun startActivityForResult(intent: Intent, requestCode: Int)
//    fun finishActivity(requestCode: Int)
}
