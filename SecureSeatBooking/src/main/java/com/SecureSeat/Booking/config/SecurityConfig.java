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

import com.SecureSeat.Booking.filter.JwtAuthFilter;
import com.SecureSeat.Booking.service.LoginServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

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
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    
    @Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
	    return new CustomLogoutSuccessHandler();
	}

	

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
//        requestHandler.setCsrfRequestAttributeName("_csrf");
//        
//          CookieCsrfTokenRepository csrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
// 
        return http
            .csrf().disable()
            .cors().configurationSource(new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://10.191.80.120:3000"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(Arrays.asList("Authorization","location","ROLE","DEVELOPER-ID","EMPLOYEE-ID","ADMIN-ID"));
                    config.setMaxAge(7200L);
                    return config;
                }
            })
            .and()
//            .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler)
//            		.ignoringRequestMatchers("/login")
//                    .csrfTokenRepository(csrfTokenRepository))
//            .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests()
            .requestMatchers("/login").permitAll()
            //.requestMatchers("/api/logout").permitAll() 
//            .requestMatchers("/swagger-ui.html").permitAll()
            .requestMatchers("/api/admin/**").hasRole("ADMIN")
            .requestMatchers("/api/employee/**").hasRole("EMPLOYEE")
            .requestMatchers("/api/developer/**").hasRole("DEVELOPER")
            .requestMatchers("/swagger-ui.html").permitAll()
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
        return new BCryptPasswordEncoder();
    }
}