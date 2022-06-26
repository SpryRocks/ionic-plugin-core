package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import com.spryrocks.kson.JsonObject
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import kotlinx.serialization.json.JsonArray
import kotlin.js.JsExport

@JsExport
abstract class BaseAction<TDelegate : Delegate> : Action {
    private var _callback: Callback<TDelegate, BaseAction<TDelegate>>? = null

    private var _call: CallContext? = null
    protected val call: CallContext
        get() = _call!!

    private var _delegate: TDelegate? = null
    val delegate: TDelegate
        get() = _delegate!!

    internal fun initialize(
        call: CallContext,
        callback: Callback<TDelegate, BaseAction<TDelegate>>,
        delegate: TDelegate
    ) {
        _call = call
        _callback = callback
        _delegate = delegate
    }

    private val _lock = SynchronizedObject()

    private var state = atomic(State.NONE)

    fun run() {
        synchronized(_lock) {
            if (state.value != State.NONE) {
                return
            }
            state.value = State.RUNNING
        }

        try {
            executeAsync { onExecute() }
        } catch (e: PluginException) {
            error(e)
        } catch (e: Exception) {
            error(PluginException(e.message, e))
        }
    }

    protected open fun executeAsync(block: () -> Unit) {
        print("Used default implementation for executeAsync() method without effects")
        block()
    }

    protected open fun executeSync(block: () -> Unit) {
        print("Used default implementation for executeSync() method without effects")
        block()
    }

    @Throws(PluginException::class)
    protected abstract fun onExecute()

    protected fun success() {
        result(CallContextResult(true), true);
    }

    protected fun success(message: Int) {
        result(CallContextResult(true, message), true)
    }

    protected fun success(message: String) {
        result(CallContextResult(true, message), true)
    }

    protected fun success(jsonObject: JsonObject) {
        result(CallContextResult(true, jsonObject), true)
    }

    protected fun error(jsonObject: JsonObject) {
        result(CallContextResult(false, jsonObject), true)
    }

    protected fun error(message: String) {
        result(CallContextResult(false, message), true)
    }

    protected fun error(e: PluginException) {
        result(delegate.errorMapper.map(e), true)
    }

    protected fun result(result: CallContextResult, finish: Boolean) {
        synchronized(_lock) {
            if (!isRunning) return
            if (!finish) {
//                pluginResult.setKeepCallback(true)
            }
            call.result(result)
            if (finish) {
                finish()
            }
        }
    }

    private fun finish() {
        cancelTimeout()
        _callback!!.finishActionSafely(this)
        state.value = State.FINISHED
    }

    private val isRunning: Boolean
        get() = state.value == State.RUNNING

//    private val timeoutTimer_lock = Any()
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
}
