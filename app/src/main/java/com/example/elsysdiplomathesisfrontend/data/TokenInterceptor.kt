package com.example.elsysdiplomathesisfrontend.data

import com.example.elsysdiplomathesisfrontend.data.store.DataStoreManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val dataStore: DataStoreManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return runBlocking {
            val requestBuilder = chain.request().newBuilder()

            if (chain.request().url.toString()
                    .equals("http://172.161.136.71:3000/comments") || chain.request().url.toString()
                    .contains("/new")
            ) {
                requestBuilder.addHeader(
                    "Authorization",
                    "Bearer ${dataStore.getString("ACCESS_TOKEN")}"
                )
            }

            chain.proceed(requestBuilder.build())
        }
    }
}
