package com.ionic.plugin.core

import com.ionic.plugin.core.actions.BaseAction
import kotlin.reflect.KClass

interface Registration {
    fun registerAction(action: String, actionType: KClass<out BaseAction<*>>);
}
