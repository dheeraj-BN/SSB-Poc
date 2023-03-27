package com.SecureSeat.Booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	
	@Bean
	SecurityFilterChain defSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.requestMatchers("/api/**").authenticated()
		.requestMatchers("/login").permitAll()
		.and().formLogin()
		.and().httpBasic();
		
		return http.build();
		
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
}
