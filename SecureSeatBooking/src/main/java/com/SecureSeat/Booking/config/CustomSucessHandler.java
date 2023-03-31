package com.SecureSeat.Booking.config;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.Employee;
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
		String userEmail = authentication.getName();
		String redirectUrl = null;
		System.out.println("customSuccess" + userEmail);
		int userId;
		List<Employee> employeeData = empRepo.findByEmployeeEmail(userEmail);
		System.out.println("CustomSuccess" + employeeData);
		userId = employeeData.get(0).getEmployeeId();
		System.out.println("custom success " + userId);
		Optional<UserDeatils> user = userRepo.findByEmployee(employeeData.get(0));
		System.out.println("custom success" + user.get().getUserId());
		System.out.println(authorities);
		for (GrantedAuthority authority : authorities) {
			if (authority.getAuthority().equals("ROLE_ADMIN")) {
				redirectUrl = "/api/admin/test/" + user.get().getUserId();
				response.addHeader("location", redirectUrl);
				response.addHeader("ROLE", "ADMIN");

				return;

			} else if (authority.getAuthority().equals("ROLE_EMPLOYEE")) {
				redirectUrl = "/api/employee/test/" + user.get().getUserId();
				response.addHeader("location", redirectUrl);
				response.addHeader("ROLE", "EMPLOYEE");

				return;

			} else if (authority.getAuthority().equals("ROLE_DEVELOPER")) {
				redirectUrl = "/api/developer/test/" + user.get().getUserId();
				response.addIntHeader("DEVELOPER-ID", user.get().getUserId());
				response.addHeader("location", redirectUrl);
				response.addHeader("ROLE", "DEVELOPER");
				
				return;

			}

		}

		throw new IllegalStateException("User has no valid roles.");

	}

}
