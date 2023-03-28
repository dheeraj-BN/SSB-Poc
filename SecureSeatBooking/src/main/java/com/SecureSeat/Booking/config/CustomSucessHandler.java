package com.SecureSeat.Booking.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
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
        // get the authenticated user's roles
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        System.out.println(roles);

        // redirect the user to the appropriate page based on their role
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin/home");
        } else if (roles.contains("ROLE_USER")) {
            response.sendRedirect("/user/home");
        } else {
            response.sendRedirect("/");
        }
    }

}

