package com.ionic.plugin.android.core.actions

import com.ionic.plugin.android.core.utils.IActivityProvider
import com.ionic.plugin.android.core.utils.IContextProvider
import com.ionic.plugin.core.actions.Mappers
import java.util.concurrent.ExecutorService

interface Delegate<TMappers : Mappers>
    : com.ionic.plugin.core.actions.Delegate<TMappers>,
    IContextProvider,
    IActivityProvider {
    val threadPool: ExecutorService
}
