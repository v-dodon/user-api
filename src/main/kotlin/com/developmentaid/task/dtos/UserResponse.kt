package com.developmentaid.task.dtos

import com.developmentaid.task.dtos.User
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserResponse(
        @JsonProperty("items")var items: List<User>,
        @JsonProperty("has_more")var hasMore: Boolean
)