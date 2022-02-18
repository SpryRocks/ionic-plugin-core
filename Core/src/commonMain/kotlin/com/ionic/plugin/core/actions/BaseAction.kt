package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import com.ionic.plugin.core.base.CallContext
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

abstract class BaseAction<TDelegate : Delegate>
    : Action {
    private val _args: JsonArray? = null

    var callback: Callback? = null

    private var _delegate: TDelegate? = null
    var _call: CallContext? = null

    val delegate: TDelegate
        get() { return _delegate!! }

    internal fun initialize(delegate: TDelegate, call: CallContext) {
        _delegate = delegate
        _call = call
    }

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
            error(e)
        } catch (e: Exception) {
            error(PluginException(e.message, e))
        }
    }

    @Throws(PluginException::class)
    protected abstract fun onExecute()
    protected abstract fun resultSuccess();

    protected fun success() {
        resultSuccess()
    }

    protected fun success(message: Int) {
        result(CallContext.Result(CallContext.Result.Status.OK, message), true)
    }

    protected fun success(message: String) {
        result(CallContext.Result(CallContext.Result.Status.OK, message), true)
    }

    protected fun success(jsonObject: JsonObject) {
        result(CallContext.Result(CallContext.Result.Status.OK, jsonObject), true)
    }

    protected fun error(jsonObject: JsonObject) {
        result(CallContext.Result(CallContext.Result.Status.ERROR, jsonObject), true)
    }

    protected fun error(message: String) {
        result(CallContext.Result(CallContext.Result.Status.ERROR, message), true)
    }

    protected fun error(e: PluginException) {
        result(delegate.errorMapper.map(e), true)
    }

    protected abstract fun result(result: CallContext.Result, finish: Boolean);

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
        get() = state == State.RUNNING

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