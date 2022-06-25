package com.ionic.plugin.core.actions

import com.ionic.plugin.core.PluginException

open class CurrentAction<TAction : Action?>(val _currentActionLock: Any) {
    var currentAction: TAction? = null
//    @Throws(PluginException::class)
    fun set(action: TAction) {
//        synchronized(_currentActionLock) {
            if (currentAction != null) {
                throw PluginException("The action is already running")
            }
            currentAction = action
//        }
    }

    fun clear(action: TAction) {
//        synchronized(_currentActionLock) {
            if (currentAction === action) {
                currentAction = null
            }
//        }
    }

    fun get(): TAction? {
//        synchronized(_currentActionLock) {
            return currentAction
//        }
    }

    class Cancelable<TAction : CancelableAction?>(currentActionLock: Any) :
        CurrentAction<TAction>(currentActionLock) {
        fun cancel() {
            var currentAction: TAction
//            synchronized(_currentActionLock) {
                if (this.currentAction == null) {
                    return
                }
                currentAction = this.currentAction!!
//            }
            currentAction.cancel()
        }
    }
}