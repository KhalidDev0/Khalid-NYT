package com.khalid.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

// Once we have endpoints that needs device uuid... etc
class CommonHeadersInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(chain.request())
}