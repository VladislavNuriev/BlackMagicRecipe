package com.example.blackmagicrecipe.data.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RetryInterceptor() : Interceptor {
    private val maxRetries: Int = 10

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response: Response?
        var retryCount = 0
        var lastException: IOException? = null

        while (retryCount < maxRetries) {
            try {
                response = chain.proceed(request)
                if (response.isSuccessful || response.code !in 500..599) {
                    return response
                }
                response.close()
            } catch (e: IOException) {
                Log.d("LOADING_INTERCEPTOR", "intercept: $e")
                lastException = e
            }

            retryCount++
            if (retryCount < maxRetries) {
                Thread.sleep(5000)
            }
        }

        throw lastException ?: IOException("Loading failed")
    }
}