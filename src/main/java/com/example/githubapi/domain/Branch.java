package com.example.githubapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Branch {

    @JsonProperty("name")
    private String name;

    @JsonProperty("commit/sha")
    private String commitSha;
}
