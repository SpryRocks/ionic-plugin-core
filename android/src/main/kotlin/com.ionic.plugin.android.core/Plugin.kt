package com.ionic.plugin.android.core

import android.app.Activity
import android.content.Context
import com.ionic.plugin.android.core.actions.WrapperDelegate
import com.ionic.plugin.android.core.actions.Delegate

abstract class Plugin<TActionKey, TDelegate : Delegate> :
    com.ionic.plugin.core.Plugin<TActionKey, TDelegate>() {
    private var _wrapperDelegate: WrapperDelegate? = null
    private val wrapperDelegate: WrapperDelegate
        get() = _wrapperDelegate!!
    open val activity: Activity
        get() = wrapperDelegate.activity
    val context: Context
        get() = activity

    protected fun init(wrapperDelegate: WrapperDelegate) {
        this._wrapperDelegate = wrapperDelegate
    }

    //region Action Delegate
//    fun startActivityForResult(@NonNull intent: Intent?, requestCode: Int) {
//        cordova.startActivityForResult(this, intent, requestCode)
//    }

//    fun finishActivity(requestCode: Int) {
//        cordova.getActivity().finishActivity(requestCode)
//    }
    //endregion
}
