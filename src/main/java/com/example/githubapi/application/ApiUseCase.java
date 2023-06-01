package com.example.githubapi.application;

import com.example.githubapi.domain.Repository;

import java.util.List;
import java.util.Optional;

public interface ApiUseCase {

    Optional<?> gerUserDetails(String name);
}
