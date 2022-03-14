package com.leeson.galaxycoffee.user.domain

import com.leeson.galaxycoffee.common.util.toResult

class User private constructor(
    val id: String,
    val name: String,
) {
    companion object {
        operator fun invoke(id: String, name: String): Result<User> {
            return User(id, name).toResult()
        }
    }
}
