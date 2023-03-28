package com.SecureSeat.Booking.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.Role;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.EmployeeRepo;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

@Component
public class SSBUsernamePasswordAuthentication implements AuthenticationProvider {

	@Autowired
	private UserDetailsRepo userRepo;

	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        String role=null;
        List<Employee> employee =  empRepo.findByEmployeeEmail(username);
        if(employee.size() > 0 ) {
        	username=employee.get(0).getEmployeeEmail();
	    	UserDeatils user = userRepo.findByEmployee(employee.get(0)).get();
	    	if (passwordEncoder.matches(password,user.getPassword() )) {
	    		
	    		Set<Role> roles = user.getRoles();
	    		for (Role role1 : roles) {
		    		role=role1.getRoleName();
		    		//System.out.println(role);
		    	}
	    		//System.out.println(roles);

		    	//System.out.println(authorities);
		    	return new UsernamePasswordAuthenticationToken(username, password,getGrantedAuthorities(roles));
		    	
	    	}else {
	        	throw new BadCredentialsException("Invalid password!");
        }
        }else {
            throw new BadCredentialsException("No user registered with this details!");
        }
	    	
        
		
	}
	private List<GrantedAuthority> getGrantedAuthorities(Set<Role> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+authority.getRoleName().toUpperCase()));
        }
        System.out.println(grantedAuthorities);
        return grantedAuthorities;
    }

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
