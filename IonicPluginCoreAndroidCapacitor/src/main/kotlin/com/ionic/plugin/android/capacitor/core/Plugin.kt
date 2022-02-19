package com.ionic.plugin.android.capacitor.core

import com.ionic.plugin.core.actions.Delegate

abstract class Plugin<TActionKey, TDelegate : Delegate> :
    com.ionic.plugin.android.core.Plugin<TActionKey, TDelegate>() {
}
