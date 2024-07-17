/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author L E N O V O
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
            throws AuthenticationException {
        
        AuthCredentials  authCredentials =  new AuthCredentials();
        
        try {
            authCredentials =  new  ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e) {
        }
        
        UsernamePasswordAuthenticationToken userNamePat = new UsernamePasswordAuthenticationToken(
                authCredentials.getEmail(),
               authCredentials.getPassword(),
                Collections.emptyList());
        
       return getAuthenticationManager().authenticate(userNamePat);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, 
            HttpServletResponse response, 
            FilterChain chain, 
            Authentication authResult) 
            throws IOException, ServletException {
        
        
        UserDetailsImpl  userDetail =  (UserDetailsImpl) authResult.getPrincipal();
        
        String token = TokenUtils.createToken(userDetail.getName(), userDetail.getUsername());
        
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().flush();
        
        
        super.successfulAuthentication(request, response, chain, authResult); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
    
    
}
