package com.leeson.galaxycoffee.external.request

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(`as` = SlackCommandRequest::class)
sealed class CommandRequest(
    open val command: String,
    open val message: String,
    open val workspaceId: String,
    open val workspaceName: String,
    open val channelId: String,
    open val channelName: String,
    open val userId: String,
    open val userName: String,
)
