package com.wosika.kale.demo.entity

data class BaseResult<T>(
    var error_code: Int,
    var reason: String,
    var result: T
)
