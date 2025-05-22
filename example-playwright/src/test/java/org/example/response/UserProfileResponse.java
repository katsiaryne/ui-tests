package org.example.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record UserProfileResponse(String name,
                                  String job,
                                  @JsonIgnoreProperties
                                  Integer id,
                                  @JsonIgnoreProperties
                                  String createdAt,
                                  @JsonIgnoreProperties
                                  String updatedAt) {
}
