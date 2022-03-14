package com.leeson.galaxycoffee.user.service

import com.leeson.galaxycoffee.external.dto.CommandDto
import com.leeson.galaxycoffee.user.domain.User

interface UserPatcher {
    fun searchAll(command: Result<CommandDto>): Result<List<User>>
}
