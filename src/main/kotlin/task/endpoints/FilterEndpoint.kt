package com.developmentaid.task.endpoints

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import task.dtos.Filter

interface FilterEndpoint {
    @GET("filters/create")
    fun createFilter(@QueryMap map: Map<String, String>) : Call<Filter>
}