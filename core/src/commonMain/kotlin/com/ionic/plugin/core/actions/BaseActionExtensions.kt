package com.ionic.plugin.core.actions

import com.spryrocks.kson.JsonObject

inline fun <TDelegate : Delegate, reified TData> BaseAction<TDelegate>.success(data: TData) =
    this.success(JsonObject.fromObject(data))
