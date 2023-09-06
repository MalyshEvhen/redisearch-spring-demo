package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain appFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.disable());
        http
		.authorizeHttpRequests(authorize -> authorize
			.anyRequest().permitAll()
		)
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
	return http.build();
    }
}
