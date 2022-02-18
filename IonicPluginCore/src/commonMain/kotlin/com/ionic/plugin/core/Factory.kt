package com.ionic.plugin.core

import com.ionic.plugin.core.actions.BaseAction
import com.ionic.plugin.core.actions.Delegate
import com.ionic.plugin.core.actions.CallContext

fun interface Factory<TActionKey, TDelegate: Delegate, TAction: BaseAction<TDelegate>> {
    fun createAction(action: TActionKey, call: CallContext): TAction
}
