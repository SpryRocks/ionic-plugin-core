package com.spryrocks.kson.utils

import com.spryrocks.kson.*

internal fun convertToJsonElement(kElement: kotlinx.serialization.json.JsonElement): JsonElement {
    when (kElement) {
        is kotlinx.serialization.json.JsonPrimitive -> {
            // null
            if (kElement is kotlinx.serialization.json.JsonNull) {
                return JsonNull()
            }
            // string
            val content = kElement.content
            if (kElement.isString) {
                return JsonString(kElement.content)
            }
            // int
            val intValue = content.toIntOrNull()
            if (intValue != null) return JsonInt(intValue)
            // boolean
            val booleanValue = content.toBooleanStrictOrNull()
            if (booleanValue != null) return JsonBoolean(booleanValue)
        }
        // object
        is kotlinx.serialization.json.JsonObject -> return JsonObject(kElement)
        // array
        is kotlinx.serialization.json.JsonArray -> return JsonArray(kElement)
    }

    throw Exception("Unknown type: " + kElement::class.simpleName)
}
