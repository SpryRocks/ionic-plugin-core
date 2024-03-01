package com.ionic.plugin.core.utils

import kotlinx.coroutines.isActive
import kotlin.coroutines.*

class ContinuationCallback<T> {
    private var continuation: Continuation<T>? = null

    fun register(continuation: Continuation<T>) {
        this.continuation?.resumeWithException(Exception("Operation cancelled"))
        this.continuation = continuation
    }

    fun resume(value: T) {
        continuation?.resume(value)
        continuation = null
    }

    fun resumeWithException(exception: Throwable) {
        continuation?.resumeWithException(exception)
        continuation = null
    }
}

fun <T> Continuation<T>.wrapAndResumeCatching(block: () -> T) {
    try {
        if (this.context.isActive) {
            resume(block())
        }
    } catch (e: Throwable) {
        if (this.context.isActive) {
            resumeWithException(e)
        }
    }
}

suspend inline fun <T> suspendCoroutineSafely(crossinline block: (Continuation<T>) -> Unit): T {
    return suspendCoroutine {
        val wrapper = object : Continuation<T> {
            override val context get() = it.context
            override fun resumeWith(result: Result<T>) {
                if (it.context.isActive) {
                    it.resumeWith(result)
                }
            }
        }
        block(wrapper)
    }
}
