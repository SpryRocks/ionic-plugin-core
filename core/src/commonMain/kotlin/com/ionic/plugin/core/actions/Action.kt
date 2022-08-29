package com.ionic.plugin.core.actions

interface Action

interface CancelableAction : Action {
    fun cancel()
}
