package com.developmentaid.task.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class Tag (
        @JsonProperty("user_id")var userId: Int,
        @JsonProperty("name")var name: String
)