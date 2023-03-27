package com.SecureSeat.Booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	
	@Bean
	SecurityFilterChain defSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
//		.requestMatchers("/allEmps").hasRole("EMPLOYEE")
//		.requestMatchers("/employee").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().formLogin()
		.and().httpBasic();
		
		return http.build();
		
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
