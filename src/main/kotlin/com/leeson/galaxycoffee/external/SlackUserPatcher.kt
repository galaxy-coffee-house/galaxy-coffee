package com.leeson.galaxycoffee.external

import com.leeson.galaxycoffee.common.util.sequence
import com.leeson.galaxycoffee.external.dto.CommandDto
import com.leeson.galaxycoffee.user.domain.User
import com.leeson.galaxycoffee.user.service.UserPatcher
import org.springframework.stereotype.Service

@Service
class SlackUserPatcher : UserPatcher {
    override fun searchAll(command: Result<CommandDto>): Result<List<User>> {
        return listOf(User("testId", "testName")).sequence()
    }
}
