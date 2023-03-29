package com.SecureSeat.Booking.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomSucessHandler customSuccessHandler;
	
	
	@Bean
	SecurityFilterChain defSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.securityContext().requireExplicitSave(false)
		.and().cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("*"));
               //config.setAllowedOriginPatterns((Collections.singletonList("http://localhos:3001")));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(7200L);
                return config;
            }
        })
		
		
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
//		.loginProcessingUrl("/login")
//		.loginPage("/login")
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