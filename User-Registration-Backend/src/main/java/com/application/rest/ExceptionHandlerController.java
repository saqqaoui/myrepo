package com.application.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.application.exception.ApiError;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { TransactionSystemException.class })
	protected ResponseEntity<Object> handleConflict(TransactionSystemException ex, WebRequest request) {
		ApiError apiError = new ApiError();

		if (ex.getRootCause() instanceof ConstraintViolationException) {

			final ConstraintViolationException e = (ConstraintViolationException) ex.getRootCause();

			final List<String> errors = new ArrayList<String>();
			for (final ConstraintViolation<?> violation : e.getConstraintViolations()) {
				errors.add(violation.getMessage());
			}

			apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		}

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}