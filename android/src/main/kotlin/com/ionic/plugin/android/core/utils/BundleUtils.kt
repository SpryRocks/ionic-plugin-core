package com.ionic.plugin.android.core.utils

import android.os.Bundle

fun Bundle.optStringSafely(key: String): String? {
    if (!containsKey(key)) return null
    return getString(key)
}

fun Bundle.getStringSafely(key: String): String {
    return optStringSafely(key) ?: throw IllegalArgumentException("String with '$key' not found in the bundle")
}

fun Bundle.optIntSafely(key: String): Int? {
    if (!containsKey(key)) return null
    return getInt(key)
}

fun Bundle.getIntSafely(key: String): Int {
    return optIntSafely(key) ?: throw IllegalArgumentException("Int with '$key' not found in the bundle")
}

fun Bundle.optBooleanSafely(key: String): Boolean? {
    if (!containsKey(key)) return null
    return getBoolean(key)
}

fun Bundle.getBooleanSafely(key: String): Boolean {
    return optBooleanSafely(key) ?: throw IllegalArgumentException("Boolean with '$key' not found in the bundle")
}
