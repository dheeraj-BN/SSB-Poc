package com.SecureSeat.Booking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.filter.JwtAuthFilter;
import com.SecureSeat.Booking.filter.JwtService;
import com.SecureSeat.Booking.model.AuthenticationRequest;
import com.SecureSeat.Booking.model.AuthenticationResponse;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;
import com.SecureSeat.Booking.service.LoginService;

@RestController
//@CrossOrigin("http://10.191.80.118:3001")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private EmployeeRepo empRepo;

	@Autowired
	private UserDetailsRepo userRepo;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Autowired
	private AuthenticationManager authenticationManager;

	// Handles the POST request for user login
		@PostMapping("/login")
		public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
			
			try {
				// Authenticates the user using the provided username and password
				Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsername(), authenticationRequest.getPassword()));
				// Sets the authentication object to the security context holder
				SecurityContextHolder.getContext().setAuthentication(authentication);

				String name = authentication.getName();
				int id = 0;
				String token;
				boolean passwordStatus = false;
				// Retrieves the employee details using the authenticated user's email
				List<Employee> employee = empRepo.findByEmployeeEmail(name);
				if (employee.size() > 0) {
					// Retrieves the user details using the retrieved employee
					UserDeatils user = userRepo.findByEmployee(employee.get(0)).get();
					id = user.getUserId();
					passwordStatus=user.isStatus();
				}

				// Retrieves the user's roles from the authentication object
				List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList());

				if (authentication.isAuthenticated()) {
					// Generates a JWT token using the user's email and roles
					token = jwtService.generateToken(name, roles);
					logger.info("Generated token for user: "+authenticationRequest.getUsername());
				} else {
					throw new UsernameNotFoundException("Invalid User !");
				}

				String roless = "ROLE_" + roles.get(0);
				
				// Creates an AuthenticationResponse object containing the user's details and JWT token
				AuthenticationResponse authenticationResponse = new AuthenticationResponse(id, name, roless, token,passwordStatus);
				logger.info("Generated response for login request ");
				return ResponseEntity.ok(authenticationResponse);

			} catch (BadCredentialsException e) {
				logger.error("Exception occurred while authenticating user", e);
				throw new RuntimeException("Incorrect email or password", e);
			}
		}
	


	//test api for checking authorization for admin
	@GetMapping("/api/admin/test/{userId}")
	public String adminHome(@PathVariable int userId) {
		return "ADMIN HOME";
	}

	//test api for checking authorization for employee
	@GetMapping("/api/employee/test/{userId}")
	public String userHome(@PathVariable int userId) {
		return "USER HOME";
	}

	//test api for checking authorization for developer
	@GetMapping("/api/developer/test/{userId}")
	public String developerHome(@PathVariable int userId) {
		return "Developer HOME";
	}
	
	//mapping when the logout is successful
	@GetMapping("/clear/logout")
	public String login() {
		return "return to Login Page";
	}

//	@GetMapping("/login")
//	public String loginres(@RequestParam("email") String email, @RequestParam String password) {
//		//System.out.println(role);
//		//System.out.println(email);
//		if (email == null || email.isEmpty() || !email.contains("@") || !email.contains(".")) {
//			return "Enter valid Email id";
//		} else {
//
//			return loginService.loginResponse(email, password);
//		}
//
//	}

//	@ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
//        if (ex instanceof MethodArgumentNotValidException) {
//            MethodArgumentNotValidException manvEx = (MethodArgumentNotValidException) ex;
//            BindingResult result = manvEx.getBindingResult();
//            List<String> errors = new ArrayList<>();
//            result.getAllErrors().forEach(error -> {
//                String errorMessage = error.getDefaultMessage();
//                errors.add(errorMessage);
//            });
//            return ResponseEntity.badRequest().body(errors);
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//        }
//    }

}