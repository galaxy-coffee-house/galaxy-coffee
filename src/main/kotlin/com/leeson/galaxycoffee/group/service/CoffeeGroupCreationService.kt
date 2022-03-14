package com.leeson.galaxycoffee.group.service

import com.leeson.galaxycoffee.common.util.flatMap
import com.leeson.galaxycoffee.user.domain.User
import org.springframework.stereotype.Service

@Service
class CoffeeGroupCreationService {
    fun create(users: Result<List<User>>): (Result<Int>) -> Result<List<List<User>>> {
        return { userCountPerCoffeeGroup ->
            userCountPerCoffeeGroup.flatMap { size ->
                users.map {
                    it.chunked(size)
                }
            }
        }
    }
}
