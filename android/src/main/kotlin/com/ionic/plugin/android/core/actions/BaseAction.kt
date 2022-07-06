package com.ionic.plugin.android.core.actions

import android.app.Activity

abstract class BaseAction<TDelegate : Delegate, TWrapperDelegate : WrapperDelegate> :
    com.ionic.plugin.core.actions.BaseAction<TDelegate>() {
    open val activity: Activity
        get() {
            val call = this.call;
            if (call is CallContext) {
                return call.wrapperDelegate.activity
            }
            throw java.lang.Error("Call should have CallContext type")
        }

    override fun executeAsync(block: () -> Unit) {
        delegate.threadPool.execute {
            try {
                block()
            } catch (error: Throwable) {
                error(error)
            }
        }
    }

    override fun executeSync(block: () -> Unit) {
        delegate.activity.runOnUiThread {
            try {
                block()
            } catch (error: Throwable) {
                error(error)
            }
        }
    }
}
