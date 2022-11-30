package com.spryrocks.kson.utils

fun <T> require(name: String, block: (name: String) -> T?) =
    block(name) ?: throw Exception("value with name '${name}' is null")
