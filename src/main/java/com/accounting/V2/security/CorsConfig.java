/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author L E N O V O
 */
@Configuration
public class CorsConfig {
    
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                
                registry.addMapping("/login")
                        .allowedOrigins("http://127.0.0.1:5500")
                        .allowedMethods("*")
                        .exposedHeaders("*");
                
                registry.addMapping("/api/**")
                        .allowedOrigins("http://127.0.0.1:5500")
                        .allowedMethods("*");
                
            }
        };
    }
    
}
