package com.SecureSeat.Booking.exception;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleException(HttpServletRequest req, Exception ex) {
		Map<String, String> error = new HashMap<>();
		error.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
		return new ResponseEntity<>("A null pointer exception occurred: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		return new ResponseEntity<>("An illegal argument exception occurred: " + ex.getMessage(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TimeoutException.class)
	public ResponseEntity<String> handleTimeoutException(TimeoutException ex) {
		return new ResponseEntity<>("A timeout occurred: " + ex.getMessage(), HttpStatus.REQUEST_TIMEOUT);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(HttpServletRequest req,
			ValidationException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<Map<String, String>> handleHttpMediaTypeNotSupportedException(HttpServletRequest req,
			HttpMediaTypeNotSupportedException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("status", String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()));
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
}
