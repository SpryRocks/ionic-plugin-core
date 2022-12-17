package com.ionic.plugin.android.core.actions

import android.content.Context
import com.ionic.plugin.android.core.WrapperDelegate
import com.ionic.plugin.core.actions.Mappers
import java.util.concurrent.ExecutorService

interface Delegate<TMappers : Mappers> : com.ionic.plugin.core.actions.Delegate<TMappers>, WrapperDelegate {
    val context: Context

    val threadPool: ExecutorService

//    fun startActivityForResult(intent: Intent, requestCode: Int)
//    fun finishActivity(requestCode: Int)
}
