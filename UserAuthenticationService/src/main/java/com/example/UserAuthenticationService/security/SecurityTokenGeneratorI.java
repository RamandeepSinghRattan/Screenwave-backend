package com.example.UserAuthenticationService.security;

import com.example.UserAuthenticationService.domain.User;

import java.util.Map;

public interface SecurityTokenGeneratorI {
    Map<String, String> createToken(User user);
}
