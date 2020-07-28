package com.developmentaid.task.service

import com.developmentaid.task.dtos.Tag
import com.developmentaid.task.endpoints.UserEndpoint
import com.developmentaid.task.util.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import com.developmentaid.task.util.RequestUtil.Companion.increasePageNo

class TagService(val userEndpoint: UserEndpoint) {

    /**
     * Get the users' tags for the given users' ids
     */
    suspend fun getUserTags(userIds: List<Int>, tags: List<String>, filter: String) : List<Tag> {
        if (userIds.isEmpty()){
            return emptyList()
        }
        val ids = userIds.joinToString(FIELDS_SEPARATOR)

        val filteredTags = mutableListOf<Tag>()
        val queryParams = mutableMapOf(SITE_QUERY_PARAM to STACKOVERFLOW, PAGE_QUERYPARAM to FIRST_PAGE,
                PAGE_SIZE_QUERYPARAM to PAGE_SIZE, FILTER_QUERY_PARAM to filter)
        val userTags = handleUserTagsRequest(filteredTags, ids, queryParams, tags)

        return userTags.filter { tag ->  tags.contains(tag.name)}
    }

    /**
     * Sends the get users tags request and filters the response by given tags' names
     */
    suspend private fun handleUserTagsRequest(tags: MutableList<Tag>, ids: String,
                                              queryParams: MutableMap<String, String>,
                                              askedTags:List<String>): List<Tag> {
        coroutineScope {
            val responseDeffered = async {userEndpoint.getUserTags(ids, queryParams) }
            val response = responseDeffered.await()
            tags.addAll(getFilteredTags(response.tags, askedTags))
            if (response.hasMore) {
                increasePageNo(queryParams)
                handleUserTagsRequest(tags, ids, queryParams, askedTags)
            }
        }
        return tags
    }

    /**
     * Filter the tags by the given tags' names
     */
    private fun getFilteredTags(tags: List<Tag>, askedTags: List<String>): List<Tag> {
        return tags.filter {tag -> askedTags.contains(tag.name) }
    }
}