package com.ionic.plugin.core.base

import com.ionic.plugin.core.actions.*

abstract class ContextWithCall<TDelegate : Delegate<TMappers>, TMappers : Mappers>: Context<TDelegate, TMappers>() {
    private var _call: CallContext? = null
    protected val call: CallContext
        get() = _call!!

    internal fun initialize(
        call: CallContext,
        callback: PluginCallbackInternal<TDelegate, BaseAction<TDelegate, TMappers>, TMappers>,
        delegate: TDelegate,
    ) {
        _call = call
        super.initialize(callback, delegate)
    }
}
