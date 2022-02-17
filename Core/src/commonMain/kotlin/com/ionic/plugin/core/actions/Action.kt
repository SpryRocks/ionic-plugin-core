package com.ionic.plugin.core.actions

interface Action {
    interface Cancelable : Action {
        fun cancel()
    }
}
