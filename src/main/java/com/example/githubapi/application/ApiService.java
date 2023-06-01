package com.example.githubapi.application;

import com.example.githubapi.domain.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ApiService implements ApiUseCase {

    private final RestTemplate restTemplate;
    private String BaseUrl = "https://api.github.com/users/";

    public ApiService() {
        this.restTemplate = new RestTemplate();
    }

    public Optional<?> gerUserDetails(String name) {
        Repository[] response = restTemplate.getForObject(BaseUrl + name + "/repos", Repository[].class);
        System.out.println(Arrays.toString(response));

        for(Repository repository: response){

        }

        return response;
    }
}
