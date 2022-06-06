package com.leeson.galaxycoffee.user.service

import com.leeson.galaxycoffee.external.dto.CommandDto
import com.leeson.galaxycoffee.user.domain.User
import org.springframework.stereotype.Service

@Service
class UserSearchService(
    private val userPatcher: UserPatcher,
) {
    fun searchAll(command: Result<CommandDto>): Result<List<User>> {
        return userPatcher.searchAll(command)
    }
}
