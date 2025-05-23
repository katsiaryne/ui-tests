package org.example.response.components;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Resource(Integer id,
                       String name,
                       Integer year,
                       String color,
                       @JsonProperty(value = "pantone_value")
                       String pantoneValue
) {
}
