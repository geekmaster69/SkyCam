package com.example.skycam

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val reques = chain.request().newBuilder()
            .addHeader("Accept:", "multipart/form-data")
            .build()
        return chain.proceed(reques)
    }
}