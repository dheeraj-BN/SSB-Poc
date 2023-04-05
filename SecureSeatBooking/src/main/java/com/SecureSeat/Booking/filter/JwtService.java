package com.SecureSeat.Booking.filter;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.controller.LoginController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	// Initialize logger
	private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

	// JWT secret key
	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	// Extracts the username from a JWT.
	public String extractUsername(String token) throws SignatureException {
		String username = null;
		try {
			// Parse the JWT using the secret key
			Claims claims = Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token).getBody();
			// Retrieve the username from the "sub" field of the token claims
			username = claims.get("sub", String.class);
		} catch (Exception e) {
			// If the JWT is invalid or the username cannot be extracted, throw a
			// SignatureException
			logger.warn("Invalid Token " + e);
			throw new SignatureException("Invalid JWT token");
		}
		// Log the extracted username
		logger.info("Extracted Username " + username);
		// System.out.println("JWTSERVICE-"+username);
		return username;
	}

	// Extracts the expiration date from a JWT.
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	// Extracts a claim from a JWT using a claims resolver function.
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	// Extracts all claims from a JWT.
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	// Checks if a JWT is expired.
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	// Validates a JWT.
	// Also checks if the token has expired.
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		// System.out.println(username);
		// System.out.println(userDetails.getUsername());
		logger.info("Validating token for the user " + username);

		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// Generates a JWT token for the given user name and roles.
	public String generateToken(String userName, List<String> roles) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("roles", roles);
		return createToken(claims, userName);
	}

	// Creates a JWT token by setting the claims, subject, issued and expiration dates.
	// Signs the token using the secret key.
	private String createToken(Map<String, Object> claims, String userName) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 18000000))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	// Gets the secret key by decoding the secret and creating an HMAC SHA key.
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}