package com.ionic.plugin.android.core.actions

import android.app.Activity
import com.ionic.plugin.android.core.WrapperDelegate
import com.ionic.plugin.android.core.utils.IActivityProvider
import com.ionic.plugin.core.actions.Mappers

abstract class BaseAction<TDelegate : Delegate<TMappers>, TWrapperDelegate : WrapperDelegate, TMappers : Mappers>
    : com.ionic.plugin.core.actions.BaseAction<TDelegate, TMappers>(),
    IActivityProvider {
    override val activity: Activity
        get() {
            val call = this.call
            if (call is CallContext) {
                return call.wrapperDelegate.activity
            }
            throw java.lang.Error("Call should have android CallContext type")
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
