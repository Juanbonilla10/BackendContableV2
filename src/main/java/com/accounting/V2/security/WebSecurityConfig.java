/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.security;


import java.util.Arrays;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author L E N O V O
 */
@Configuration
@AllArgsConstructor
public class WebSecurityConfig {
    
    private final UserDetailsService userDetailService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,AuthenticationManager authenticationManager)throws Exception{
        
        JWTAuthenticationFilter jWTAuthenticationFilter =  new JWTAuthenticationFilter();
        jWTAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jWTAuthenticationFilter.setFilterProcessesUrl("/login");
        
        return http
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests() 
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jWTAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter,UsernamePasswordAuthenticationFilter.class)
                .cors()
                .and()
                .build(); 
    }
    
    
    @Bean
    AuthenticationManager autManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
   
    
}
