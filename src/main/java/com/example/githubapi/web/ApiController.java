package com.example.githubapi.web;

import com.example.githubapi.application.ApiUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class ApiController {

    private final ApiUseCase api;

    @GetMapping(value = "/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return api.gerUserDetails(name)
                .map(data -> ResponseEntity.ok(data))
                .orElse(ResponseEntity.notFound().build());
    }
}
