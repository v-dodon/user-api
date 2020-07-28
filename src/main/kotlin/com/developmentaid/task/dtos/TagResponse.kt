package com.developmentaid.task.dtos

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TagResponse(
        @JsonProperty("items")val tags: List<Tag>,
        @JsonProperty("has_more") val hasMore: Boolean
)