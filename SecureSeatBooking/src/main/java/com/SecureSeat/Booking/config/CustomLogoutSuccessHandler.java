package com.SecureSeat.Booking.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
	

	//custom logout success handler 
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write("Logout Successful!");
        response.getWriter().flush();
        response.getWriter().close();
    }
}

