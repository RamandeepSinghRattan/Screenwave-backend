package com.example.UserAuthenticationService.security;

import com.example.UserAuthenticationService.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTTokenGenerator implements SecurityTokenGeneratorI {
    @Override
    public Map<String , String> createToken(User user) {
        Map<String,Object> userData=new HashMap<>();
        userData.put("email",user.getEmail());
        System.out.println(userData);
        String token =generateToken(userData,user.getEmail()) ;

        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("message", "Login successfully");
        return map;
    }

    private String generateToken(Map<String, Object> userData, String email) {
    String jwtToken= Jwts.builder()
            .setClaims(userData)
            .setSubject(email)
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256,"MySecretKeyForUserAuthenticationServiceOfMovieApp")
            .compact();
    return jwtToken;
    }

}
