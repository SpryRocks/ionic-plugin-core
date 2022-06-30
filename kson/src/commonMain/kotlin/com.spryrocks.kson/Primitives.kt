package com.spryrocks.kson

abstract class JsonPrimitive(internal open val value: Any?) : JsonElement() {
    override fun toString(): String {
        val value = value
        if (value is String) return "\"${value.replace("\n", "\\n")}\""
        return value.toString()
    }
}

class JsonNull : JsonPrimitive(null)

class JsonString(public override val value: String) : JsonPrimitive(value)

class JsonInt(public override val value: Int) : JsonPrimitive(value)

class JsonBoolean(public override val value: Boolean) : JsonPrimitive(value)
