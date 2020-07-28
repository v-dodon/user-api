package task.service

import com.developmentaid.task.dtos.User
import com.developmentaid.task.endpoints.UserEndpoint
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import task.util.*
import task.util.RequestUtil.Companion.increasePageNo

class UserService(val userEndpoint: UserEndpoint) {
    /**
     * Creates the request for getUsers and applies the query params
     */
   suspend fun getUsers(sortField: String, minField: String, filter: String, location: List<String>, answeredQuestion: Int) : List<User> {

        val queryParams = mutableMapOf(SORT_QUERY_PARAM to sortField, MIN_QUERY_PARAM to minField,
                SITE_QUERY_PARAM to STACKOVERFLOW, PAGE_QUERYPARAM to FIRST_PAGE, PAGE_SIZE_QUERYPARAM to PAGE_SIZE, FILTER_QUERY_PARAM to filter)

        val filteredUsers = mutableListOf<User>()

        return getUsersRestResponse(filteredUsers, location, answeredQuestion, queryParams)
    }


    /**
     * Sends the get users request and filters the response by location and number of answered questions
     */
    suspend private fun getUsersRestResponse(filteredUsers: MutableList<User>, location: List<String>, answeredQuestion: Int, queryParams: MutableMap<String, String>) : List<User> {
        coroutineScope {
            val responseDeffered = async { userEndpoint.getUsers(queryParams) }
            val response = responseDeffered.await()
            filteredUsers.addAll(getFilteredUsers(response.items, location, answeredQuestion))
            if (response.hasMore && response.items.isNotEmpty()) {
                increasePageNo(queryParams)
                getUsersRestResponse(filteredUsers, location, answeredQuestion, queryParams)
            }
        }

        return filteredUsers
    }

    /**
     * Filters users by location and number of answered questions
     */
    private fun getFilteredUsers(users: List<User>, location: List<String>, answeredQuestion: Int) : List<User> {
        return users.filter { user -> location.contains(user.location) && answeredQuestion < user.answerCount }
    }
}