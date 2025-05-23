package org.example.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.response.components.Resource;
import org.example.response.components.Support;

import java.util.List;

public record ResourceListResponse(Integer page,
                                   @JsonProperty(value = "per_page")
                                   Integer perPage,
                                   Integer total,
                                   @JsonProperty(value = "total_pages")
                                   Integer totalPages,
                                   List<Resource> data,
                                   Support support) {
}
