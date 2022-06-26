package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized

open class CurrentAction<TAction : Action?>(val _currentActionLock: SynchronizedObject) {
    var currentAction: TAction? = null
    fun set(action: TAction) {
        synchronized(_currentActionLock) {
            if (currentAction != null) {
                throw PluginException("The action is already running")
            }
            currentAction = action
        }
    }

    fun clear(action: TAction) {
        synchronized(_currentActionLock) {
            if (currentAction === action) {
                currentAction = null
            }
        }
    }

    fun get(): TAction? {
        synchronized(_currentActionLock) {
            return currentAction
        }
    }

    class Cancelable<TAction : CancelableAction>(currentActionLock: SynchronizedObject) :
        CurrentAction<TAction>(currentActionLock) {
        fun cancel() {
            val currentAction = synchronized(_currentActionLock) { this.currentAction }
            currentAction?.cancel()
        }
    }
}