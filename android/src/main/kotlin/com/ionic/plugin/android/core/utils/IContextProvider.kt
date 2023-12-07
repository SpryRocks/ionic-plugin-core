package com.ionic.plugin.android.core.utils

import android.content.Context

interface IContextProvider {
    val context: Context
}

interface WithContextProvider: IContextProvider {
    val contextProvider: IContextProvider
    override val context get() = contextProvider.context
}
