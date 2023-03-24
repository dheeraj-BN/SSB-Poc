package com.SecureSeat.Booking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.UserDetailsRepo;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDetailsRepo userRepo;
	

	@Override
	public Optional<UserDeatils> findUserByUsername(int id) {
		return userRepo.findByUserId(id);
	}
}
