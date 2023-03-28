package com.SecureSeat.Booking.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSucessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    	System.out.println(authorities);
    	for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                response.sendRedirect("/api/admin/test");
                return;
            } else if (authority.getAuthority().equals("ROLE_EMPLOYEE")) {
                response.sendRedirect("/api/employee/test");
                return;
            }else if(authority.getAuthority().equals("ROLE_DEVELOPER")){
            	response.sendRedirect("/api/developer/test");
            	 return;
            }
        }
    	throw new IllegalStateException("User has no valid roles.");
    }

}


