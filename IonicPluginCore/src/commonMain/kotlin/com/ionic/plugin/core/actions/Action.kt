package com.ionic.plugin.core.actions

expect interface Action

interface CancelableAction : Action {
    fun cancel()
}
