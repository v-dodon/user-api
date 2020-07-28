package com.developmentaid.task.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class Item (
        @JsonProperty("filter")val filter: String,
        @JsonProperty("filter_type")val filterType: String,
        @JsonProperty("included_fields")val includedFields: List<String>
        )