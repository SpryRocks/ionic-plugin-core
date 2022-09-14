package com.spryrocks.kson.utils

import com.spryrocks.kson.JsonArray
import com.spryrocks.kson.JsonValue

class JsonArrayIterator(private val jsonArray: JsonArray): Iterator<JsonValue> {
    private var index = -1

    override fun hasNext(): Boolean {
        return index < jsonArray.size - 1
    }

    override fun next(): JsonValue {
        return jsonArray.get(++index)
    }
}
