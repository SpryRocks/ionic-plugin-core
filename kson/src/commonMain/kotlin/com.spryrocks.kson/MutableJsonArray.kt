package com.spryrocks.kson

import kotlinx.serialization.json.JsonElement

class MutableJsonArray internal constructor(override val list: MutableList<JsonElement>): JsonArray(list) {
    constructor(): this(mutableListOf())
}
