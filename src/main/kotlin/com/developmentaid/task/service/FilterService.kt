package com.developmentaid.task.service

import com.developmentaid.task.endpoints.FilterEndpoint
import com.developmentaid.task.ServiceBuilder
import com.developmentaid.task.util.BASE_QUERY_PARAM
import com.developmentaid.task.util.FIELDS_SEPARATOR
import com.developmentaid.task.util.INCLUDE_QUERY_PARAM
import com.developmentaid.task.util.NONE_FILTER_TYPE

class FilterService {

    fun createFilter(includeFields: List<String>): String {
        val request = ServiceBuilder.createService(FilterEndpoint::class.java)

        val fields = includeFields.joinToString(FIELDS_SEPARATOR)
        val queryParams = mapOf(INCLUDE_QUERY_PARAM to fields, BASE_QUERY_PARAM to NONE_FILTER_TYPE)

        val response = request.createFilter(queryParams).execute()
        return response.body()?.items?.find { item -> includeFields.containsAll(item.includedFields) }?.filter.orEmpty()
    }
}