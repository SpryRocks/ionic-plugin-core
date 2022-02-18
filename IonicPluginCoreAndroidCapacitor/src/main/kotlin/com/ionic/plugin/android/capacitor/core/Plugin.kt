package com.ionic.plugin.android.capacitor.core

import com.getcapacitor.PluginCall
import com.ionic.plugin.core.actions.BaseAction

abstract class Plugin : com.getcapacitor.Plugin() {
    protected fun run(action: BaseAction<*>, call: PluginCall) {
//        initializeAndRun(action, call)
    }

//    private fun initializeAndRun(action: BaseAction, call: PluginCall) {
//        action.initialize(this, call)
////
////        when (action) {
////            is SyncAction -> action.runSync()
////            is AsyncAction -> action.runAsync()
////        }
//    }
}
