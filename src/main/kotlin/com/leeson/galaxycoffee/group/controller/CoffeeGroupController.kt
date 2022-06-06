package com.leeson.galaxycoffee.group.controller

import com.leeson.galaxycoffee.external.dto.CommandDto
import com.leeson.galaxycoffee.external.request.CommandRequest
import com.leeson.galaxycoffee.group.service.CoffeeGroupCreationService
import com.leeson.galaxycoffee.user.service.UserSearchService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CoffeeGroupController(
    private val userSearchService: UserSearchService,
    private val coffeeGroupCreationService: CoffeeGroupCreationService,
) {
    @PostMapping("/coffee-groups")
    fun createCoffeeGroup(
        @RequestBody
        request: CommandRequest,
    ) {
        val command = CommandDto.of(request)
        val users = userSearchService.searchAll(command)
        coffeeGroupCreationService.create(users)(Result.success(3))
    }
}
