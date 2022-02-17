package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import com.ionic.plugin.core.base.CallbackContext
import com.ionic.plugin.core.base.PluginResult
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

abstract class BaseAction<TDelegate : Delegate>
protected constructor()
    : Action {
    private val _args: JsonArray? = null

    var callback: Callback? = null

    var _delegate: TDelegate? = null
    var callbackContext: CallbackContext? = null

    val delegate: TDelegate
        get() { return _delegate!! }

    private val _lock = Any()

    //@Volatile
    private var state = State.NONE
    fun run() {
        //synchronized(_lock) {
        if (state != State.NONE) {
            return
        }
        state = State.RUNNING
        //}
        executeActionSafe(object : ExecuteAction {
            @Throws(PluginException::class)
            override fun execute() {
                onExecute()
            }
        })
    }

    private fun executeActionSafe(action: ExecuteAction) {
        try {
            action.execute()
        } catch (e: PluginException) {
            resultError(e)
        } catch (e: Exception) {
            resultError(PluginException(e.message, e))
        }
    }

    @Throws(PluginException::class)
    protected abstract fun onExecute()
    protected abstract fun resultSuccess();

    protected fun resultSuccess(message: Int) {
        result(PluginResult(PluginResult.Status.OK, message), true)
    }

    protected fun resultSuccess(message: String) {
        result(PluginResult(PluginResult.Status.OK, message), true)
    }

    protected fun resultSuccess(jsonObject: JsonObject) {
        result(PluginResult(PluginResult.Status.OK, jsonObject), true)
    }

    protected fun resultError(jsonObject: JsonObject) {
        result(PluginResult(PluginResult.Status.ERROR, jsonObject), true)
    }

    protected fun resultError(message: String) {
        result(PluginResult(PluginResult.Status.ERROR, message), true)
    }

    protected fun resultError(e: PluginException) {
        result(delegate!!.errorMapper.map(e), true)
    }

    protected fun success() {
        resultSuccess()
    }

    protected abstract fun result(pluginResult: PluginResult, finish: Boolean);

//    protected fun result(pluginResult: PluginResult, finish: Boolean) {
//        synchronized(_lock) {
//            if (!isRunning) return
//            if (!finish) {
//                pluginResult.setKeepCallback(true)
//            }
//            callbackContext.sendPluginResult(pluginResult)
//            if (finish) {
//                finish()
//            }
//        }
//    }

    private fun finish() {
        cancelTimeout()
        callback!!.finishActionSafely(this)
        state = State.FINISHED
    }

    private val isRunning: Boolean
        private get() = state == State.RUNNING

//    @get:NonNull
//    protected val context: Context
//        protected get() = delegate!!.context

//    protected fun executeAsync(action: ExecuteAction) {
//        delegate!!.threadPool.execute(object : Runnable() {
//            override fun run() {
//                executeActionSafe(action)
//            }
//        })
//    }

    interface Callback {
        fun finishActionSafely(action: BaseAction<*>)
    }

//    @Nullable
//    private var timeoutTimer: CountDownTimer? = null

//    fun setTimeout(interval: Int, runnable: Runnable) {
//        val timer: CountDownTimer = object : CountDownTimer(interval, 1000) {
//            fun onTick(l: Long) {}
//            fun onFinish() {
//                synchronized(timeoutTimer_lock) {
//                    if (timeoutTimer === this) {
//                        timeoutTimer = null
//                        runnable.run()
//                    }
//                }
//            }
//        }
//        synchronized(timeoutTimer_lock) {
//            timeoutTimer = timer
//            timer.start()
//        }
//    }

    fun cancelTimeout() {
//        synchronized(timeoutTimer_lock) {
//            if (timeoutTimer != null) {
//                timeoutTimer.cancel()
//                timeoutTimer = null
//            }
//        }
    }

    private enum class State {
        NONE, RUNNING, FINISHED
    }

    interface ExecuteAction {
        @Throws(PluginException::class)
        fun execute()
    }

    companion object {
        private val timeoutTimer_lock = Any()
    }
}