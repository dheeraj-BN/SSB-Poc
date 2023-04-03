package com.SecureSeat.Booking.model;

import java.util.List;

public class AuthenticationResponse {

	private int id;
    private String email;
    private String roles;
    private String token;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public AuthenticationResponse(int id, String email, String roles, String token) {
		super();
		this.id = id;
		this.email = email;
		this.roles = roles;
		this.token = token;
	}
}
