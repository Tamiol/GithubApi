package com.example.githubapi.application;

import com.example.githubapi.domain.Branch;
import com.example.githubapi.domain.Repository;
import com.example.githubapi.exception.UserNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ApiService implements ApiUseCase {

    private final RestTemplate restTemplate;
    private String BaseUrl = "https://api.github.com/users/";

    public ApiService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Repository> gerUserDetails(String name) {
        String response;
        try{
            response = restTemplate.getForObject(BaseUrl + name + "/repos", String.class);
        } catch (HttpClientErrorException e){
            throw new UserNotFoundException(name);
        }

        Repository[] repositories;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            repositories = objectMapper.treeToValue(rootNode, Repository[].class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }

        if(repositories.length < 1) {return Collections.emptyList();}

        return Stream.of(repositories)
                .filter(e -> !e.isFork())
                .map(repository -> {
                    repository.setBranches(getBranches(repository.getBranchesUrl()));
                    return repository;
                })
                .collect(Collectors.toList());
    }

    private List<Branch> getBranches(String url) {
        String branchUrl = url.replace("{/branch}", "");
        Branch[] response = restTemplate.getForObject(branchUrl, Branch[].class);
        return  List.of(response);
    }
}
