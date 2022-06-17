package com.ionic.plugin.android.capacitor.core

import android.app.Activity
import com.getcapacitor.PluginCall
import com.ionic.plugin.android.capacitor.core.actions.CallContext
import com.ionic.plugin.core.actions.Delegate
import com.ionic.plugin.android.capacitor.core.actions.WrapperDelegate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class CapacitorPluginWrapper<TActionKey, TDelegate : Delegate> :
    com.getcapacitor.Plugin() {
    abstract val plugin: com.ionic.plugin.core.Plugin<TActionKey, TDelegate>
    private val wrapperDelegate = WrapperDelegateImpl(this)

    override fun load() {
        GlobalScope.launch {
            plugin.load().await()
        }
    }

    protected fun call(action: TActionKey, call: PluginCall) {
        plugin.call(action, CallContext(call, wrapperDelegate))
    }

    class WrapperDelegateImpl<TActionKey, TDelegate : Delegate>(private val wrapper: CapacitorPluginWrapper<TActionKey, TDelegate>) : WrapperDelegate {
        override val activity: Activity
            get() = wrapper.activity
    }
}
