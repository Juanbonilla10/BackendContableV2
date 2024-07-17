/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author L E N O V O
 */
public class TokenUtils {
    
    private final static String ACCESS_TOKEN_SECRET = "875E25B0488D93602667280F8EDDF6EDCE48D9B5BFF7403191BEB92BA8995DC0";
    private final static Long ACCESS_TOKEN_VALIDITY_TOKEN = 300L;
    
    public static String createToken(String name,String email){
        Long expirationTime = ACCESS_TOKEN_VALIDITY_TOKEN * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        System.out.println("Acceso hasta : " .concat(expirationDate.toString()));
        
        Map<String,Object> extra =  new HashMap<>();
        extra.put("nombre", name);
        
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
        
    }
    
    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        
        String email = claims.getSubject();
        
        return new UsernamePasswordAuthenticationToken(email,null,Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }
}
