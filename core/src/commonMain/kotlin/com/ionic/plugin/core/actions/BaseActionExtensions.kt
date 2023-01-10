package com.ionic.plugin.core.actions

import com.spryrocks.kson.JsonObject

inline fun <TDelegate : Delegate<TMappers>, reified TData, TMappers : Mappers>
        BaseAction<TDelegate, TMappers>.success(data: TData, finish: Boolean = true, serialize: Boolean) {
    if (serialize) {
        this.success(JsonObject.fromObject(data), finish)
    } else {
        this.success(data, finish)
    }
}
