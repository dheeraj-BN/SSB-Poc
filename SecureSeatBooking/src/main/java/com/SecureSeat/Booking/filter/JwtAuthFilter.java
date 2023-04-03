package com.SecureSeat.Booking.filter;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.SecureSeat.Booking.controller.LoginController;
import com.SecureSeat.Booking.service.LoginServiceImpl;

import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);
	
    @Autowired
    private JwtService jwtService;

    @Autowired
    private LoginServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {	
    	logger.debug("Authenticating user  ");
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        List<String> roles = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
            roles = jwtService.extractClaim(token, claims -> claims.get("roles", List.class));
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                if (roles != null) {
                    List<GrantedAuthority> authorities = roles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .collect(Collectors.toList());
                    authToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                }
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        //System.out.println(request.getRequestURI());
        if (request.getRequestURI().equals("/clear/logout")) {
        	//System.out.println(request.getRequestURI());
        	logger.info("Clearing user credentials from Security Context for Logout ");
            SecurityContextHolder.clearContext();
            logger.info("Cleared Security Context ");
        }
        filterChain.doFilter(request, response);
        logger.debug("User Authenticated Successfully.  ");
    }catch(Exception e) {
    	logger.error("Error Authenticating User "+e.getMessage());
    	throw new SignatureException("Invalid Token");
    }
    }



}