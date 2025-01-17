package com.devsuperior.bds02.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.DBException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {

		StandardError error = new StandardError();
		HttpStatus status = HttpStatus.NOT_FOUND;

		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Resource not Found");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(status).body(error);

	}

	@ExceptionHandler(DBException.class)
	public ResponseEntity<StandardError> dataBaseException(DBException e, HttpServletRequest request) {

		StandardError error = new StandardError();
		HttpStatus status = HttpStatus.BAD_REQUEST;

		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Database Exception");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(status).body(error);

	}
}