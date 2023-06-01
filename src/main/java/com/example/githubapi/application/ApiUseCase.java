package com.example.githubapi.application;

import com.example.githubapi.domain.Repository;

import java.util.List;

public interface ApiUseCase {

    List<Repository> gerUserDetails(String name);
}
