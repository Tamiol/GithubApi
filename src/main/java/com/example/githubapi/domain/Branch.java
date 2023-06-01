package com.example.githubapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Branch {

    @JsonProperty("name")
    private String name;

    @JsonProperty("sha")
    private String commitSha;
}
