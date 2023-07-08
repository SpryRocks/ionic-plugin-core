package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import com.ionic.plugin.core.logger.*
import com.ionic.plugin.core.utils.defaultCoroutineContext
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.js.JsExport

@JsExport
abstract class BaseAction<TDelegate : Delegate<TMappers>, TMappers : Mappers> : Action, CoroutineScope {
    private var _callback: PluginCallback<TDelegate, BaseAction<TDelegate, TMappers>, TMappers>? = null
    private val callback get() = _callback!!

    private var _call: CallContext? = null
    protected val call: CallContext
        get() = _call!!

    private var _delegate: TDelegate? = null
    val delegate: TDelegate
        get() = _delegate!!

    internal fun initialize(
        call: CallContext,
        callback: PluginCallback<TDelegate, BaseAction<TDelegate, TMappers>, TMappers>,
        delegate: TDelegate,
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
        } catch (e: Throwable) {
            error(e)
        }
    }

    protected open fun executeAsync(block: () -> Unit) {
        try {
            print("Used default implementation for executeAsync() method without effects")
            block()
        } catch (error: Throwable) {
            error(error)
        }
    }

    protected open fun executeSync(block: () -> Unit) {
        try {
            print("Used default implementation for executeSync() method without effects")
            block()
        } catch (error: Throwable) {
            error(error)
        }
    }

    override val coroutineContext = Job()

    protected fun executeSuspend(context: CoroutineContext = defaultCoroutineContext, block: suspend () -> Unit) {
        launch(coroutineContext) {
            try {
                block()
            } catch (e: Throwable) {
                error(e)
            }
        }
    }

    @Throws(PluginException::class)
    protected abstract fun onExecute()

    fun success(data: Any? = null, finish: Boolean = true) = resultSafely(finish) {
        delegate.mappers.reportSuccess(data, call, finish)
    }

    fun error(error: Throwable? = null, finish: Boolean = true) = resultSafely(finish) {
        delegate.mappers.reportError(error, call, finish)
    }

    private fun resultSafely(finish: Boolean, block: () -> Unit) {
        synchronized(_lock) {
            if (!isRunning) return
            block()
            if (finish) finish()
        }
    }

    private fun finish() {
        cancelTimeout()
        callback.finishActionSafely(this)
        state.value = State.FINISHED
    }

    private val isRunning: Boolean
        get() = state.value == State.RUNNING

    val mappers get() = delegate.mappers

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

    override fun logger(tag: String?): ILogger = Logger(
        this@BaseAction::class.simpleName,
        tag,
        object : IPluginLogger {
            override fun sendLog(
                action: String?,
                tag: String?,
                level: LogLevel,
                message: String,
                params: Array<out LogParam>
            ) = callback.sendLog(action, tag, level, message, params)
        },
    )
}
