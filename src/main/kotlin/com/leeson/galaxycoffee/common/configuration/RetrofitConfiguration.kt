package com.leeson.galaxycoffee.common.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.leeson.galaxycoffee.external.connection.SlackConnection
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Configuration
class RetrofitConfiguration(
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun slackConnection() =
        Retrofit.Builder()
            .baseUrl("https://hooks.slack.com")
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build()
            .create(SlackConnection::class.java)
}