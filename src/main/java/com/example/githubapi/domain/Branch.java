package com.example.githubapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({"name", "sha"})
public class Branch {

    @JsonProperty("name")
    private String name;

    @JsonProperty("sha")
    private String commitSha;
}
