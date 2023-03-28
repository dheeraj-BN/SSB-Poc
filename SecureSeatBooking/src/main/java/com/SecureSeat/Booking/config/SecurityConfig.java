package com.SecureSeat.Booking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomSucessHandler customSuccessHandler;
	
	
	@Bean
	SecurityFilterChain defSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.securityContext().requireExplicitSave(false)
		.and().authorizeHttpRequests()
		.requestMatchers("/api/admin/**").hasRole("ADMIN")
		.requestMatchers("/api/employee/**").hasRole("EMPLOYEE")
		.requestMatchers("/api/developer/**").hasRole("DEVELOPER")
		.requestMatchers("/api/allEmps").authenticated()
		.anyRequest().permitAll()
		.and()
		.httpBasic()
		.and()
		.formLogin()
		.successHandler(customSuccessHandler)
		.and()
		.csrf().disable();
		
		return http.build();	
		
		
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}