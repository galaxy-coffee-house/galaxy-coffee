package com.leeson.galaxycoffee.external.dto

import com.leeson.galaxycoffee.common.util.sequence
import com.leeson.galaxycoffee.external.request.CommandRequest
import com.leeson.galaxycoffee.external.request.SlackCommandRequest

sealed class CommandDto(
    val command: String,
    val type: CommandType,
    val message: String,
) {
//    fun getCoffeeGroupSize(): Result<Int> {
//        return when (type) {
//            CommandType.COFFEE -> null
//            else -> Result.failure()
//        }
//    }

    companion object {
        fun of(request: CommandRequest): Result<CommandDto> {
            return CommandType(request.command, request.message)
                .map { type ->
                    when (request) {
                        is SlackCommandRequest -> SlackCommandDto.of(request, type)
                    }
                }
        }
    }
}

sealed class CommandType {
    class Coffee private constructor() : CommandType() {
        companion object {
            const val command = "/coffee"

            fun from(message: String): Result<CommandType> {
//                return parameterTypeConstructors
//                    .map { it.invoke(message) }
//                    .sequence()
//                    .map(::Coffee)
            }
        }
    }

    companion object {
        operator fun invoke(
            command: String,
            message: String,
        ): Result<CommandType> {
            return when (command) {
                Coffee.command -> Coffee.from(message)
                else -> Result.failure(NotSupportedCommandException(command))
            }
        }
    }
}

class NotSupportedCommandException(command: String) : RuntimeException("제공하지 않는 명령 유형입니다. [/$command]")
class RequiredCommandParameterTypeException : RuntimeException("필수 명령 매개변수 유형입니다.")
