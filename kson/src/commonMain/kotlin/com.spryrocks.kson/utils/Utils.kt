package com.spryrocks.kson.utils

import com.spryrocks.kson.*

internal fun convertFromKJsonElement(kJsonElement: kotlinx.serialization.json.JsonElement): Any? {
    when (kJsonElement) {
        // null
        is kotlinx.serialization.json.JsonNull -> return null
        // primitive
        is kotlinx.serialization.json.JsonPrimitive -> {
            // string
            val content = kJsonElement.content
            if (kJsonElement.isString) return content
            // int
            val intValue = content.toIntOrNull()
            if (intValue != null) return intValue
            // float
            val floatValue= content.toFloatOrNull()
            if (floatValue != null) return floatValue
            // boolean
            val booleanValue = content.toBooleanStrictOrNull()
            if (booleanValue != null) return booleanValue
        }
        // object
        is kotlinx.serialization.json.JsonObject -> return JsonObject(kJsonElement)
        // array
        is kotlinx.serialization.json.JsonArray -> return JsonArray(kJsonElement)
    }

    throw Exception("Unknown type: " + kJsonElement::class.simpleName)
}
