package com.ionic.plugin.core.utils

typealias OnResult = () -> Unit
typealias OnResult1<T> = (value: T) -> Unit
typealias OnError = (error: Throwable) -> Unit
