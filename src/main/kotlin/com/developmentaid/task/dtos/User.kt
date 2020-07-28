package com.developmentaid.task.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class User (
        @JsonProperty("user_id")var userId: Int,
        @JsonProperty("answer_count")var answerCount: Int,
        @JsonProperty("display_name")var displayName: String,
        @JsonProperty("link")var link: String,
        @JsonProperty("location")var location: String?,
        @JsonProperty("profile_image")val profileImage: String?,
        @JsonProperty("question_count")val questionCount: Int
)