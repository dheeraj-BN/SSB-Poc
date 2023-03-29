package com.SecureSeat.Booking.config;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.Role;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSucessHandler implements AuthenticationSuccessHandler {
	
	
	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
	private UserDetailsRepo userRepo;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    	String userEmail=authentication.getName();
    	System.out.println("customSuccess"+ userEmail);
    	int userId;
    	List<Employee> employeeData= empRepo.findByEmployeeEmail(userEmail);
    	System.out.println("CustomSuccess" + employeeData);
    	userId=employeeData.get(0).getEmployeeId();
    	System.out.println("custom success "+userId);
    	Optional<UserDeatils> user=userRepo.findByEmployee(employeeData.get(0));
    	System.out.println("custom success"+user.get().getUserId());
    	System.out.println(authorities);
    	for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                response.sendRedirect("/api/admin/test/"+user.get().getUserId());
                return;
            } else if (authority.getAuthority().equals("ROLE_EMPLOYEE")) {
                response.sendRedirect("/api/employee/test/"+user.get().getUserId());
                return;
            }else if(authority.getAuthority().equals("ROLE_DEVELOPER")){
            	response.sendRedirect("/api/developer/test/"+user.get().getUserId());
            	 return;
            }
        }
    	throw new IllegalStateException("User has no valid roles.");
    }

}

