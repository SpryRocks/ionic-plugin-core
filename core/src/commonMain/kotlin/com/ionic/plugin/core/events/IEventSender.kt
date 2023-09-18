package com.ionic.plugin.core.events

import com.ionic.plugin.core.actions.Delegate
import com.ionic.plugin.core.actions.Mappers

interface IEventSender<TDelegate : Delegate<TMappers>, TMappers : Mappers> {
    fun sendEvent(event: EventBase<TDelegate, TMappers>)
}
