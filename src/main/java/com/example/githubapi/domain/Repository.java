package com.example.githubapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonPropertyOrder({"name", "login", "branches"})
public class Repository {

    @JsonProperty("name")
    private String repositoryName;

    @JsonProperty("login")
    private String ownerLogin;

    @JsonProperty(value = "fork", access = JsonProperty.Access.WRITE_ONLY)
    private boolean fork;

    @JsonProperty(value = "branches_url", access = JsonProperty.Access.WRITE_ONLY)
    private String branchesUrl;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Branch> branches;
}
