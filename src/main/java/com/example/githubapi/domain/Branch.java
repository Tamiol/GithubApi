package com.example.githubapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Branch {

    @JsonProperty("name")
    private String name;

    @JsonProperty("sha")
    private String commitSha;
}
