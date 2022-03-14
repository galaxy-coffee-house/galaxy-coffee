package com.leeson.galaxycoffee.external.request

import com.fasterxml.jackson.annotation.JsonProperty

data class SlackCommandRequest(
    val token: String,

    @JsonProperty("team_id")
    override val workspaceId: String,

    @JsonProperty("team_domain")
    override val workspaceName: String,

    @JsonProperty("channel_id")
    override val channelId: String,

    @JsonProperty("channel_name")
    override val channelName: String,

    @JsonProperty("user_id")
    override val userId: String,

    @JsonProperty("user_name")
    override val userName: String,

    override val command: String,

    @JsonProperty("text")
    override val message: String,

    @JsonProperty("api_app_id")
    val appId: String,

    @JsonProperty("is_enterprise_install")
    val enterpriseInstalled: Boolean,

    @JsonProperty("response_url")
    val responseUrl: String,

    @JsonProperty("trigger_id")
    val triggerId: String,
) : CommandRequest(
    command = command,
    message = message,
    workspaceId = workspaceId,
    workspaceName = workspaceName,
    channelId = channelId,
    channelName = channelName,
    userId = userId,
    userName = userName,
)