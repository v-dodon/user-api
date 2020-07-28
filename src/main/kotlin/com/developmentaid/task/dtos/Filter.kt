package com.developmentaid.task.dtos

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Filter(
        @JsonProperty("items")var items:List<Item>
)
