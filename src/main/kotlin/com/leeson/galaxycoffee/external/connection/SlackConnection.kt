package com.leeson.galaxycoffee.external.connection

import com.leeson.galaxycoffee.group.SlackMessageSendingRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SlackConnection {
    @Headers(value = ["accept: application/json", "content-type: application/json"])
    @POST("/services/T035H1FTB40/B034CN3GM8F/5PlmGBt5ie1C9VC1vOyPlASu")
    fun sendSlackMessage(
        @Body requestBody: SlackMessageSendingRequest,
    ): Call<Unit>
}