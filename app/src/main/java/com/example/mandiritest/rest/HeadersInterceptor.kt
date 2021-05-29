package com.example.mandiritest.rest

import androidx.annotation.NonNull
import com.example.mandiritest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*

class HeadersInterceptor : Interceptor {

    @NonNull
    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("X-Api-Key", BuildConfig.API_KEY)
                .build()
        return chain.proceed(newRequest)
    }
}
