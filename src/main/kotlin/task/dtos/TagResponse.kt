package task.dtos

import com.developmentaid.task.dtos.Tag
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TagResponse(
        @JsonProperty("items")val tags: List<Tag>,
        @JsonProperty("has_more") val hasMore: Boolean
)