package task.service

import com.developmentaid.task.endpoints.FilterEndpoint
import task.ServiceBuilder
import task.util.BASE_QUERY_PARAM
import task.util.FIELDS_SEPARATOR
import task.util.INCLUDE_QUERY_PARAM
import task.util.NONE_FILTER_TYPE

class FilterService {

    fun createFilter(includeFields: List<String>): String {
        val request = ServiceBuilder.createService(FilterEndpoint::class.java)

        val fields = includeFields.joinToString(FIELDS_SEPARATOR)
        val queryParams = mapOf(INCLUDE_QUERY_PARAM to fields, BASE_QUERY_PARAM to NONE_FILTER_TYPE)

        val response = request.createFilter(queryParams).execute()
        return response.body()?.items?.find { item -> includeFields.containsAll(item.includedFields) }?.filter.orEmpty()
    }
}