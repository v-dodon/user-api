package com.developmentaid.task.util

const val SORT_QUERY_PARAM = "sort"
const val MIN_QUERY_PARAM = "min"
const val FILTER_QUERY_PARAM = "filter"
const val SITE_QUERY_PARAM = "site"
const val STACKOVERFLOW = "stackoverflow"
const val PAGE_QUERYPARAM = "page"
const val PAGE_SIZE_QUERYPARAM = "pageSize"
const val PAGE_SIZE = "100"
const val FIRST_PAGE = "1"
const val INCLUDE_QUERY_PARAM = "include"
const val BASE_QUERY_PARAM = "base"
const val NONE_FILTER_TYPE = "none"
const val FIELDS_SEPARATOR = ";"
const val ITEMS_FILTER = ".items"
const val HAS_MORE_FILTER = ".has_more"
const val TAG_NAME_FILTER = "tag.name"
const val TAG_USER_ID_FILTER = "tag.user_id"
const val USER_ID_FILTER = "user.user_id"
const val USER_DISPLAY_NAME_FILTER = "user.display_name"
const val USER_LOCATION_FILTER = "user.location"
const val USER_ANSWERS_COUNT_FILTER = "user.answer_count"
const val USER_QUESTIONS_COUNT_FILTER = "user.question_count"
const val USER_LINK_FILTER = "user.link"
const val USER_PROFILE_IMAGE_FILTER = "user.profile_image"

class RequestUtil {
    companion object {
        fun increasePageNo(queryParams: MutableMap<String, String>) {
            val pageNo = queryParams.get(PAGE_QUERYPARAM)?.toInt()
            queryParams.put(PAGE_QUERYPARAM, pageNo?.inc().toString())
        }
    }
}