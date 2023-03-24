package com.SecureSeat.Booking.service;

import java.util.Optional;

import com.SecureSeat.Booking.entity.UserDeatils;

public interface LoginService {

	Optional<UserDeatils> findUserByUsername(int id);

}