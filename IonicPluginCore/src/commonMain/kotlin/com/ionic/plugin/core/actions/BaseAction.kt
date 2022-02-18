package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

abstract class BaseAction<TDelegate : Delegate>
    : Action {
    private val _args: JsonArray? = null

    internal var _callback: Callback<TDelegate, BaseAction<TDelegate>>? = null

    internal var _call: CallContext? = null

    internal var _delegate: TDelegate? = null

    val call: CallContext
        get() = _call!!

    val delegate: TDelegate
        get() = _delegate!!

    internal fun initialize(call: CallContext) {
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

    protected fun success() {
        result(CallContext.Result(CallContext.Result.Status.OK), true);
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

    protected fun result(result: CallContext.Result, finish: Boolean) {
//        synchronized(_lock) {
        if (!isRunning) return
        if (!finish) {
//                pluginResult.setKeepCallback(true)
        }
        call.result(result)
        if (finish) {
            finish()
        }
//        }
    }

    private fun finish() {
        cancelTimeout()
        _callback!!.finishActionSafely(this)
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

    interface Callback<TDelegate : Delegate, TAction : BaseAction<TDelegate>> {
        fun finishActionSafely(action: TAction)
    }
}
