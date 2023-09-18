package com.ionic.plugin.core.events

import com.ionic.plugin.core.actions.Delegate
import com.ionic.plugin.core.actions.Mappers
import com.ionic.plugin.core.base.Context
import com.spryrocks.kson.JsonObject

abstract class EventBase<TDelegate : Delegate<TMappers>, TMappers : Mappers>: Context<TDelegate, TMappers>() {
    val mappers get() = delegate.mappers

    abstract val name: String

    abstract fun getData(): JsonObject
}
