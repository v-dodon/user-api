package com.developmentaid.task.service

import com.developmentaid.task.endpoints.UserEndpoint
import kotlinx.coroutines.runBlocking
import com.developmentaid.task.ServiceBuilder
import com.developmentaid.task.dtos.StackOverflowUser
import com.developmentaid.task.util.*

class StackOverflowUsersService {
    private val userEndpoint = ServiceBuilder.createService(UserEndpoint::class.java)
    private val userService = UserService(userEndpoint)
    private val tagService = TagService(userEndpoint)

    private fun getStackOverflowUsers(): List<StackOverflowUser> {
        val filterService = FilterService()
        val includedFields = listOf(USER_ID_FILTER, USER_DISPLAY_NAME_FILTER, USER_LINK_FILTER, USER_LOCATION_FILTER,
                USER_ANSWERS_COUNT_FILTER, USER_QUESTIONS_COUNT_FILTER, USER_PROFILE_IMAGE_FILTER, ITEMS_FILTER,
                HAS_MORE_FILTER, TAG_NAME_FILTER, TAG_USER_ID_FILTER)

        val createdFilter = filterService.createFilter(includedFields)
        val locations = listOf("Romania", "Moldova")
        val minAnsweredQuestion = 1
        val tags = listOf("java", ".net", "docker", "C#")
        val stackOverflowUsers = mutableListOf<StackOverflowUser>()
        runBlocking {
            val users = userService.getUsers("reputation", "223", createdFilter,
                    locations, minAnsweredQuestion)
            val userIds = users.map { user -> user.userId }
            val userTags = tagService.getUserTags(userIds, tags, createdFilter)

            stackOverflowUsers.addAll(users.map { user ->
                StackOverflowUser(user, userTags.filter { tag ->
                    user.userId.equals(tag.userId)
                }.map { tag -> tag.name })
            }
                    .filter { user -> user.tags.isNotEmpty() })
        }


        return stackOverflowUsers
    }

    fun printStackOverflowUsers() {
        getStackOverflowUsers().forEach { stackOverflowUser -> println(stackOverflowUser) }
    }
}