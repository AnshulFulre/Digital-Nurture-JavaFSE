package com.cognizant.spring_learn.controller;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader){
        log.info("authenticate started");
        log.debug("Authorization Header : {}",authHeader);

        String user = getUser(authHeader);

        String token = generateJwt(user);

        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        log.info("authenticate ended");

        return map;
    }

    private String getUser(String authHeader){
        log.info("getUser STARTED");

        // Remove "Basic "
        String encodedCredentials = authHeader.substring(6);
        log.debug("Encoded Credentials : {}", encodedCredentials);

        // Decode Base64
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);

        String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
        log.debug("Decoded Credentials : {}", credentials);

        //Extract username
        String user = credentials.substring(0, credentials.indexOf(":"));
        log.debug("Username : {}", user);

        log.info("getUser ENDED");

        return user;
    }

    private String generateJwt(String user){
        log.info("START");

        String token = Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 1200000))
                .signWith(SignatureAlgorithm.HS256, "secretkey")
                .compact();
        log.debug("Generated Token : {}", token);

        log.info("END");
        return token;

    }
}
