package com.ionic.plugin.core.base

import com.ionic.plugin.core.actions.*

abstract class Context<TDelegate : Delegate<TMappers>, TMappers : Mappers>() {
    private var _callback: PluginCallbackInternal<TDelegate, BaseAction<TDelegate, TMappers>, TMappers>? = null
    internal val callback get() = _callback!!

    private var _delegate: TDelegate? = null
    val delegate: TDelegate
        get() = _delegate!!

    internal fun initialize(
        callback: PluginCallbackInternal<TDelegate, BaseAction<TDelegate, TMappers>, TMappers>,
        delegate: TDelegate,
    ) {
        _callback = callback
        _delegate = delegate
    }
}
