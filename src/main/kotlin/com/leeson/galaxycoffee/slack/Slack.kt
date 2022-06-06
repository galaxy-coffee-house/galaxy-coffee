package com.leeson.galaxycoffee.slack

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.leeson.galaxycoffee.common.util.flatMap
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.create

object Slack {
    private const val token = "xoxb-3187049929136-3160445844981-2BpCelEEXj8g0urxzoXNrco5"
    private val client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        chain.proceed(newRequest)
    }.build()

    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://slack.com")
        .addConverterFactory(JacksonConverterFactory.create(ObjectMapper()))
        .build()

    inline fun <reified T> createService(): T {
        return retrofit.create()
    }
}

suspend fun <T : SlackResponse<R>, R> Call<T>.await(): Result<R> = runCatching { await() }.flatMap { it.evaluate() }

abstract class SlackResponse<T> {
    abstract val data: T?

    @JsonProperty("ok")
    var ok: Boolean = false

    @JsonProperty("error")
    val error: String? = null
}

fun <T> SlackResponse<T>.evaluate(): Result<T> {
    return if (ok) {
        Result.success(data!!)
    } else {
        Result.failure(SlackResponseException(error!!))
    }
}

class SlackResponseException(message: String) : Exception(message)
