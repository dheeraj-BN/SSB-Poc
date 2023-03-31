package com.SecureSeat.Booking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.SecureSeat.Booking.config.SSBUsernamePasswordAuthentication;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.service.LoginService;

@RestController
//@CrossOrigin("http://10.191.80.118:3001")
@RequestMapping("")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private EmployeeRepo empRepo;

	@Autowired
	private SSBUsernamePasswordAuthentication authentication;
	@GetMapping("/api/user/{id}")
	public Optional<UserDeatils> findById(@PathVariable int id) {
		return loginService.findUserByUsername(id);
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/api/admin/test/{userId}")
	public String adminHome(@PathVariable int userId) {
		return "ADMIN HOME";
	}
	
	@GetMapping("/api/employee/test/{userId}")
	public String userHome(@PathVariable int userId) {
		return "USER HOME";
	}
	
	@GetMapping("/api/developer/test/{userId}")
	public String developerHome(@PathVariable int userId) {
		return "Developer HOME";
	}

	@GetMapping("/employee")
	public Employee findEmployeeByName(@RequestParam String email) {
		return loginService.findEmployeeByName(email);
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
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manvEx = (MethodArgumentNotValidException) ex;
            BindingResult result = manvEx.getBindingResult();
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> {
                String errorMessage = error.getDefaultMessage();
                errors.add(errorMessage);
            });
            return ResponseEntity.badRequest().body(errors);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

	@GetMapping("/allEmps")
	public List<Employee> findAllEmps() {
		return loginService.findAllEmployees();
	}
	
	@RequestMapping("/user")
    public Employee getUserDetailsAfterLogin(Authentication authentication) {
        List<Employee> customers = empRepo.findByEmployeeEmail(authentication.getName());
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }

    }
	
	
}