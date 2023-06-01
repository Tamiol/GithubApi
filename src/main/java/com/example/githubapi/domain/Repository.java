package com.example.githubapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Repository {

    @JsonProperty("name")
    private String repositoryName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String ownerLogin;

    @JsonProperty(value = "fork", access = JsonProperty.Access.WRITE_ONLY)
    private boolean fork;

    @JsonProperty(value = "branches_url", access = JsonProperty.Access.WRITE_ONLY)
    private String branchesUrl;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Branch> branches;
}
