package com.ionic.plugin.android.core.utils

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.ionic.plugin.core.utils.resumeSafely
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

typealias ActivityResultTest = (requestCode: Int, resultCode: Int, data: Intent?) -> Boolean
typealias ActivityResultBlock<T> = (requestCode: Int, resultCode: Int, data: Intent?) -> T

fun <T> registerContinuationActivityResult(
    activityResultObserver: IActivityResultObserver,
    it: Continuation<T>,
    test: ActivityResultTest,
    block: ActivityResultBlock<T>,
) {
    activityResultObserver.add(object : IActivityResult {
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
            if (!test(requestCode, resultCode, data)) return false
            it.resumeSafely { block(requestCode, resultCode, data) }
            activityResultObserver.remove(this)
            return true
        }
    })
}

suspend fun <T> suspendActivityResult(
    activityResultObserver: IActivityResultObserver,
    test: ActivityResultTest,
    block: ActivityResultBlock<T>,
): T = suspendCoroutine {
    registerContinuationActivityResult(activityResultObserver, it, test, block)
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
        it.resumeSafely { block(intent) }
    }
}
