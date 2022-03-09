package com.leeson.galaxycoffee.slack.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class SlackUser(@JsonProperty("id") val id: String) {
    fun toUser(): User = User()
}

// TODO user를 domain에서 오는 걸로 변경
class User
