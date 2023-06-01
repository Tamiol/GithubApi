package com.example.githubapi.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String name) {
        super("User not found with provided name: " + name);
    }
}
