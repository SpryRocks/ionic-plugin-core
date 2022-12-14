package com.spryrocks.kson.utils

fun <T> require(name: String, block: (name: String) -> T?) =
    block(name) ?: throw Exception("value with name '${name}' is null")

fun <T> require(index: Int, block: (index: Int) -> T?) =
    block(index) ?: throw Exception("value with index '${index}' is null")
