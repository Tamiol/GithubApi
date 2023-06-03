package com.example.githubapi.web;

import com.example.githubapi.application.ApiUseCase;
import com.example.githubapi.domain.Repository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ApiController {

    private final ApiUseCase api;

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Repository> getByName(@PathVariable String name) {
        return api.gerUserDetails(name);
    }
}
