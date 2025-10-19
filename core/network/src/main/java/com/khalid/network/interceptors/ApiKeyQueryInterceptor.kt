package com.khalid.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyQueryInterceptor(private val apiKey: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url.newBuilder().addQueryParameter("api-key", apiKey).build()
        return chain.proceed(original.newBuilder().url(url).build())
    }
}