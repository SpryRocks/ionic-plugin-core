package com.ionic.plugin.core.actions

import com.ionic.plugin.core.logger.WithLogger

interface Action : WithLogger

interface CancelableAction : Action {
    fun cancel()
}
