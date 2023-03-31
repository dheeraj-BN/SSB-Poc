package com.SecureSeat.Booking.config;

import java.util.Arrays;
import java.util.Collections;

import org.springdoc.webmvc.ui.SwaggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.SecureSeat.Booking.filter.JWTTokenGeneratorFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
//@Import(SwaggerConfig.class)
public class SecurityConfig {
	
	@Autowired
	private CustomSucessHandler customSuccessHandler;
	
	
	@Bean
	SecurityFilterChain defSecurityFilterChain(HttpSecurity http) throws Exception {
		
		//http.securityContext().requireExplicitSave(false)
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://10.191.80.120:3000"));
               //config.setAllowedOriginPatterns((Collections.singletonList("http://localhos:3001")));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setExposedHeaders(Arrays.asList("Authorization"));
                config.setMaxAge(7200L);
                return config;
            }
        })
		.and()
		.addFilterBefore(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.authorizeHttpRequests()
		.requestMatchers("/api/admin/**").hasRole("ADMIN")
		.requestMatchers("/api/employee/**").hasRole("EMPLOYEE")
		.requestMatchers("/api/developer/**").hasRole("DEVELOPER")
		//.requestMatchers("/login").permitAll()
		   .requestMatchers("/swagger-ui.html").permitAll()
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