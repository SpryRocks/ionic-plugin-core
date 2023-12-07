package com.ionic.plugin.core.utils

import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

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

fun <T> Continuation<T>.wrapSafely(block: () -> T) {
    try {
        resume(block())
    } catch (e: Throwable) {
        resumeWithException(e)
    }
}
