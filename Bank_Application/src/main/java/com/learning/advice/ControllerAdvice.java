package com.learning.advice;

import java.util.HashMap;
import java.util.Map;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learning.apierrors.ApiError;
import com.learning.exceptions.AccountAlreadyExistsException;
import com.learning.exceptions.NoDataFoundException;

public class ControllerAdvice extends ResponseEntityExceptionHandler {
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<?> noDateFoundException(NoDataFoundException e) {
		Map<String, String> map = new HashMap<>();
		map.put("message", "no data found");
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "no data found", e);
		return buildResponseEntity(apiError);
	}

	// this is responsible for handling AccountAlreadyExistsException.
	@ExceptionHandler(AccountAlreadyExistsException.class)
	public ResponseEntity<?> nameAlreadyExistsException(AccountAlreadyExistsException e) {
		Map<String, String> map = new HashMap<>();
		map.put("message", "name already exists");
		System.out.println(e);
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), e);
		System.out.println(apiError);
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) { // @Valid being used when posting to the DB
		ApiError apiError = new ApiError(status);
		apiError.setMessage("Validation Error");
		apiError.addValidationErrors(ex.getFieldErrors());
		apiError.addValidationObjectErrors(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(apiError);

	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {

		return new ResponseEntity<Object>(apiError, apiError.getStatus());

	}

	@ExceptionHandler
	protected ResponseEntity<?> handleMethodAegumentTypeMismatch(MethodArgumentTypeMismatchException e) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(e.getMessage());
		apiError.setDebugMessage(e.getRequiredType().getName());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	protected ResponseEntity<?> handleMethodArgumentNotValidException(ConstraintViolationException ex) {
		// TODO Auto-generated method stub
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);

	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<?> handleMethodException(Exception e) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage(e.getMessage());
		return buildResponseEntity(apiError);

	}
}
