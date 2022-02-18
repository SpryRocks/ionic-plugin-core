package com.ionic.plugin.core

import com.cordova.core.actions.LogUtils.debug
import com.ionic.plugin.core.actions.BaseAction
import com.ionic.plugin.core.actions.Delegate
import com.ionic.plugin.core.actions.Factory
import com.ionic.plugin.core.actions.Mappers
import com.ionic.plugin.core.base.CallContext
import kotlinx.serialization.json.JsonArray

abstract class Plugin protected constructor(val factory: Factory)
    : Delegate, BaseAction.Callback {
    private val _actionsLockObject = Any()

    override var errorMapper: Mappers.IErrorMapper = Mappers.DefaultErrorMapper()
        protected set

    protected fun pluginInitialize() {
        registerActions(factory)
    }

    protected abstract fun registerActions(factory: Registration?)

    fun execute(action: String, args: JsonArray, callbackContext: CallContext): Boolean {
        debug("plugin action: $action, args: $args")
        try {
            val baseAction = factory.createAction(action, args, callbackContext) ?: return false
            setCurrentActionAndRunSafely(baseAction)
        } catch (e: PluginException) {
            callbackContext.result(errorMapper.map(e))
        }
        return true
    }

    @Throws(PluginException::class)
    private fun setCurrentActionAndRunSafely(action: BaseAction<*>) {
//        synchronized(_actionsLockObject) {
            beforeActionRun(action)
//        }
        action.run()
    }

    override fun finishActionSafely(action: BaseAction<*>) {
//        synchronized(_actionsLockObject) {
            actionFinished(action)
//        }
    }

    @Throws(PluginException::class)
    protected abstract fun beforeActionRun(action: BaseAction<*>)
    protected abstract fun actionFinished(action: BaseAction<*>)

    //region Action Delegate
//    val context: Context
//        get() = cordova.getContext()

//    val threadPool: ExecutorService
//        get() = cordova.getThreadPool()

//    val activity: Activity
//        get() = cordova.getActivity()

//    fun startActivityForResult(@NonNull intent: Intent?, requestCode: Int) {
//        cordova.startActivityForResult(this, intent, requestCode)
//    }

//    fun finishActivity(requestCode: Int) {
//        cordova.getActivity().finishActivity(requestCode)
//    }
    //endregion
}