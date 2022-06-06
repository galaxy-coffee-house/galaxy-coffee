package com.leeson.galaxycoffee.external.dto

import com.leeson.galaxycoffee.external.request.SlackCommandRequest

class SlackCommandDto(
    command: String,
    type: CommandType,
    message: String,
    val workspaceId: String,
    val workspaceName: String,
    val channelId: String,
    val channelName: String,
    val userId: String,
    val userName: String,
    val token: String,
    val appId: String,
    val enterpriseInstalled: Boolean,
    val responseUrl: String,
    val triggerId: String,
): CommandDto(command, type, message) {

//    override fun getCoffeeGroupSize(): Result<Int> {
//        return (2).toResult()
//    }

    companion object {
        fun of(
            request: SlackCommandRequest,
            type: CommandType,
        ): CommandDto {
            return SlackCommandDto(
                command = request.command,
                type = type,
                message = request.message,
                workspaceId = request.workspaceId,
                workspaceName = request.workspaceName,
                channelId = request.channelId,
                channelName = request.channelName,
                userId = request.userId,
                userName = request.userName,
                token = request.token,
                appId = request.appId,
                enterpriseInstalled = request.enterpriseInstalled,
                responseUrl = request.responseUrl,
                triggerId = request.triggerId,
            )
        }
    }
}
