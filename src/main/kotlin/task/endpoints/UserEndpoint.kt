package com.developmentaid.task.endpoints

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import task.dtos.TagResponse
import task.dtos.UserResponse

interface UserEndpoint {
    @GET("users")
    suspend fun getUsers(@QueryMap map: Map<String, String>) : UserResponse
    @GET("users/{ids}/tags")
    suspend fun getUserTags(@Path("ids") userIds: String, @QueryMap map: Map<String, String>): TagResponse
}