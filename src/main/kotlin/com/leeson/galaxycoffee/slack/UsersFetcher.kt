package com.leeson.galaxycoffee.slack

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.leeson.galaxycoffee.slack.entity.SlackUser
import com.leeson.galaxycoffee.slack.entity.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class UsersFetcher(private val slackService: SlackService) {
    constructor() : this(Slack.createService<SlackService>())

    suspend fun fetch(workspaceId: String): Result<List<User>> {
        return slackService.listUsersOfWorkspace(teamId = workspaceId).await()
            .map { users -> users.map(SlackUser::toUser) }
    }
}

interface SlackService {
    @FormUrlEncoded
    @POST("api/users.list")
    fun listUsersOfWorkspace(
        @Field("cursor") cursor: String? = null,
        @Field("limit") limit: Int = 100,
        @Field("team_id") teamId: String
    ): Call<ListingUsersOfWorkspaceResponse>
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class ListingUsersOfWorkspaceResponse(
    @JsonProperty("members") override val data: List<SlackUser>?
) : SlackResponse<List<SlackUser>>()
