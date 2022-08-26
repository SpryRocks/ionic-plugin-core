package com.ionic.plugin.android.core

import android.app.Activity
import android.content.Context
import com.ionic.plugin.core.actions.Delegate as CoreDelegate

abstract class Plugin<TActionKey, TDelegate : CoreDelegate> :
    com.ionic.plugin.core.Plugin<TActionKey, TDelegate>() {
    protected override val wrapperDelegate: WrapperDelegate
        get() = super.wrapperDelegate as WrapperDelegate

    open val activity: Activity get() = wrapperDelegate.activity
    val context: Context get() = activity

    //region Action Delegate
//    fun startActivityForResult(@NonNull intent: Intent?, requestCode: Int) {
//        cordova.startActivityForResult(this, intent, requestCode)
//    }

//    fun finishActivity(requestCode: Int) {
//        cordova.getActivity().finishActivity(requestCode)
//    }
    //endregion
}
