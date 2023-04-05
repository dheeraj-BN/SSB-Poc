package com.SecureSeat.Booking.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.SecureSeat.Booking.filter.JwtAuthFilter;
import com.SecureSeat.Booking.service.LoginServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomSucessHandler customSuccessHandler;

	@Autowired
	private LoginServiceImpl userDetailsService;

	@Autowired
	private JwtAuthFilter authFilter;

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		// Configuring authentication manager with authentication provider
		return new ProviderManager(Collections.singletonList(authenticationProvider()));
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		// Configuring authentication provider with user details service and password encoder
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		// Configuring logout success handler
		return new CustomLogoutSuccessHandler();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		// Configuring CORS policy
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setExposedHeaders(Arrays.asList("Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// Configuring HttpSecurity for security filter chain
		return http
			.csrf().disable()
			.cors().configurationSource(corsConfigurationSource()).and()
			.authorizeHttpRequests()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/api/admin/**").hasRole("ADMIN")
				.requestMatchers("/api/employee/**").hasAnyRole("EMPLOYEE", "ADMIN")
				.requestMatchers("/api/developer/**").hasAnyRole("DEVELOPER", "ADMIN")
				.requestMatchers("/swagger-ui.html").authenticated()
				.anyRequest().permitAll()
			.and()
			.httpBasic()
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/clear/logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
			.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authenticationProvider(authenticationProvider())
			.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
			.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// Configuring password encoder
		return new BCryptPasswordEncoder();
	}
}