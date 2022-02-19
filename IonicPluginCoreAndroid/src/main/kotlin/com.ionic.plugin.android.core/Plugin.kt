package com.ionic.plugin.android.core

import android.app.Activity
import android.content.Context
import com.ionic.plugin.android.core.actions.WrapperDelegate
import com.ionic.plugin.core.actions.Delegate

abstract class Plugin<TActionKey, TDelegate : Delegate> :
    com.ionic.plugin.core.Plugin<TActionKey, TDelegate>() {
    var _wrapperDelegate: WrapperDelegate? = null
    val wrapperDelegate: WrapperDelegate
        get() = _wrapperDelegate!!
    open val activity: Activity
        get() = wrapperDelegate.activity
    val context: Context
        get() = activity

    //region Action Delegate
//    fun startActivityForResult(@NonNull intent: Intent?, requestCode: Int) {
//        cordova.startActivityForResult(this, intent, requestCode)
//    }

//    fun finishActivity(requestCode: Int) {
//        cordova.getActivity().finishActivity(requestCode)
//    }
    //endregion
}
