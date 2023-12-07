package com.ionic.plugin.android.core.utils

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.ionic.plugin.core.utils.wrapSafely
import kotlin.coroutines.Continuation

fun <T> registerContinuationActivityResult(
    activityResultObserver: IActivityResultObserver,
    it: Continuation<T>,
    test: (requestCode: Int, resultCode: Int, data: Intent?) -> Boolean,
    block: (requestCode: Int, resultCode: Int, data: Intent?) -> T,
) {
    activityResultObserver.add(object : IActivityResult {
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
            if (!test(requestCode, resultCode, data)) return false
            it.wrapSafely {
                return@wrapSafely block(requestCode, resultCode, data)
            }
            return true
        }
    })
}

fun <T> registerReceiver(
    activity: Activity,
    action: String,
    block: (intent: Intent) -> T,
) {
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            block(intent)
            activity.unregisterReceiver(this)
        }
    }
    activity.registerReceiver(receiver, IntentFilter(action))
}

fun <T> registerContinuationReceiver(
    activity: Activity,
    it: Continuation<T>,
    action: String,
    block: (intent: Intent) -> T,
) {
    registerReceiver(activity, action) { intent ->
        it.wrapSafely { block(intent) }
    }
}
