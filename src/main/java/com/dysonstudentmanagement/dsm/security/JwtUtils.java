package com.dysonstudentmanagement.dsm.security;

import com.dysonstudentmanagement.dsm.service.UserPrimaryDataService;
import com.dysonstudentmanagement.dsm.service.impl.UserPrimaryDataServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
/*
JwtUtils class

Generate the json web token for front-end and back-end get the authorization , includes user information.

Original Author: Fang-Yu Kung 28/04/2024
 */
public class JwtUtils {
    private static String signKey = "dysongroup9";
    private  static  Long expire =  43200000L;

    private final UserPrimaryDataService userPrimaryDataService;

    @Autowired
    public JwtUtils(UserPrimaryDataService userPrimaryDataService) {
        this.userPrimaryDataService = userPrimaryDataService;
    }

    public String generateJwt(String userID) {
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("alg", "HS256");
        headerClaims.put("typ", "JWT");

        Map<String, Object> payloadClaims = new HashMap<>();
        payloadClaims.put("userID", userID);
        Date expiration = new Date(System.currentTimeMillis() + expire);
        payloadClaims.put("exp", expiration.getTime() / 1000);
        if (userPrimaryDataService != null) {
            payloadClaims.put("userType", userPrimaryDataService.getUserPrimaryDataById(userID).getUserType());
        } else {
            throw new IllegalStateException("UserPrimaryDataService is not initialized");
        }

        String jwt = Jwts.builder()
                .setHeader(headerClaims)
                .setClaims(payloadClaims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .compact();
        return jwt;

    }

    public String getUserIDFromJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject();
    }

    public static Claims parseJwt(String jwt) {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt);
        Claims claims = jws.getBody();
        return claims;
    }

}
