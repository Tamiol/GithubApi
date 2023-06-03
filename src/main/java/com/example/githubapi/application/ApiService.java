package com.example.githubapi.application;

import com.example.githubapi.domain.Branch;
import com.example.githubapi.domain.Repository;
import com.example.githubapi.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiService implements ApiUseCase {

    private final RestTemplate restTemplate;
    private String BaseUrl = "https://api.github.com/users/";

    public ApiService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Repository> gerUserDetails(String name) {
        JsonNode response;
        try{
            response = restTemplate.getForObject(BaseUrl + name + "/repos", JsonNode.class);
        } catch (HttpClientErrorException ex){
            switch (ex.getStatusCode().value()) {
                case 404 ->  throw new UserNotFoundException(name);
                default -> throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }

        List<Repository> repositories = new ArrayList<>();
        for (JsonNode objNode : response) {
            Repository rep = Repository.builder()
                    .repositoryName(objNode.get("name").asText())
                    .ownerLogin(objNode.get("owner").get("login").asText())
                    .fork(objNode.get("fork").asBoolean())
                    .branchesUrl(objNode.get("branches_url").asText())
                    .build();
            repositories.add(rep);
        }

        if(repositories.isEmpty()) {return repositories;}

        return repositories.stream()
                .filter(e -> !e.isFork())
                .map(repository -> {
                    repository.setBranches(getBranches(repository.getBranchesUrl()));
                    return repository;
                })
                .collect(Collectors.toList());
    }

    private List<Branch> getBranches(String url) {
        String branchUrl = url.replace("{/branch}", "");
        JsonNode response = restTemplate.getForObject(branchUrl, JsonNode.class);

        List<Branch> branches = new ArrayList<>();
        for (JsonNode objNode : response) {
            Branch rep = Branch.builder()
                    .name(objNode.get("name").asText())
                    .commitSha(objNode.get("commit").get("sha").asText())
                    .build();
            branches.add(rep);
        }
        return branches;
    }
}
