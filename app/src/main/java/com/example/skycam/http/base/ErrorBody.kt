package com.example.skycam.http.base

data class ErrorBody(
    val statusCode: Int,
    val error: String,
    val message: String,
)