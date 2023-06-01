package com.example.githubapi.web;

import com.example.githubapi.application.ApiUseCase;
import com.example.githubapi.domain.Repository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("user")
@AllArgsConstructor
public class ApiController {

    private final ApiUseCase api;

    @GetMapping(value = "/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Repository> getByName(@PathVariable String name) {
        return api.gerUserDetails(name);
    }
}
