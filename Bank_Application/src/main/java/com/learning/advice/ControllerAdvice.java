package com.learning.advice;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.apierrors.ApiError;
import com.learning.exceptions.AccountAlreadyExistsException;
import com.learning.exceptions.AccountDisabledException;
import com.learning.exceptions.AccountNotApprovedException;
import com.learning.exceptions.BalanceNonPositiveException;
import com.learning.exceptions.IdNotFoundException;
import com.learning.exceptions.NoDataFoundException;
import com.learning.exceptions.RoleNotFoundException;
import com.learning.exceptions.SecretDetailsDoNotMatchException;
import com.learning.exceptions.TransactionInvalidException;
import com.learning.exceptions.UnauthrorizedException;
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler implements AuthenticationEntryPoint {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);
	
	
	
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
		apiError.setDebugMessage("check your account Id");
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(UnauthrorizedException.class)
	public ResponseEntity<?> unauthrorizedException(UnauthrorizedException e) {
		Map<String, String> map = new HashMap<>();
		
	
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, e.getMessage(), e);
		apiError.setDebugMessage("check your account access");
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

//	@ExceptionHandler(Exception.class)
//	protected ResponseEntity<?> handleMethodException(Exception e) {
//		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
//		apiError.setMessage(e.getMessage());
//		return buildResponseEntity(apiError);
//
//	}
	
	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<?> roleNotFoundException(RoleNotFoundException e) {
		Map<String, String> map = new HashMap<>();
		map.put("message", "role not found");
		System.out.println(e);
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		System.out.println(apiError);
		apiError.setDebugMessage("check your roles");
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(TransactionInvalidException.class)
	public ResponseEntity<?> transactionInvalidException(TransactionInvalidException e) {
		Map<String, String> map = new HashMap<>();
		map.put("message", e.getMessage());
		System.out.println(e);
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		apiError.setDebugMessage("check accounts");
		return buildResponseEntity(apiError);
	}
	

	@ExceptionHandler(AccountDisabledException.class)
	public ResponseEntity<?> accountDisabledException(AccountDisabledException e) {
		Map<String, String> map = new HashMap<>();
		map.put("message", e.getMessage());
		System.out.println(e);
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, e.getMessage(), e);
		apiError.setDebugMessage("check your account");
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> idNotFoundException(IdNotFoundException e) {
		Map<String, String> map = new HashMap<>();
		map.put("message", e.getMessage());
		System.out.println(e.getMessage());
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, e.getMessage(), e);
		apiError.setDebugMessage("check your Id");
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(BalanceNonPositiveException.class)
	public ResponseEntity<?> balanceNonPositiveException(BalanceNonPositiveException e) {
		Map<String, String> map = new HashMap<>();
		map.put("message", "check your input number");
		System.out.println(e.getMessage());
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, e.getMessage(), e);
		apiError.setDebugMessage("check your balance");
		
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> accessDeniedException(AccessDeniedException e) {
		Map<String, String> map = new HashMap<>();
		map.put("message", "check your roles");
		
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, e.getMessage(), e);
		apiError.setDebugMessage("access denied");
		
		
		return buildResponseEntity(apiError);
	}
	

	@ExceptionHandler(SecretDetailsDoNotMatchException.class)
	public ResponseEntity<?> secretDetailsDoNotMatchException(SecretDetailsDoNotMatchException e) {
		Map<String, String> map = new HashMap<>();
		
		
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, e.getMessage(), e);
		apiError.setDebugMessage("Wrong answer");
		
		
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException e) {
		Map<String, String> map = new HashMap<>();
		
		
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, e.getMessage(), e);
		apiError.setDebugMessage("check your data input");
		
		
		return buildResponseEntity(apiError);
	}
	
	
	
	@ExceptionHandler(AccountNotApprovedException.class)
	public ResponseEntity<?> accountNotApprovedException(AccountNotApprovedException e) {
		Map<String, String> map = new HashMap<>();
		
		
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		apiError.setDebugMessage("check your account");
		
		
		return buildResponseEntity(apiError);
	}
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("Unauthorized error: {}", authException.getMessage());

	    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	    // this response it is of json type.
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    // status code as unauthorized

	    final Map<String, Object> body = new HashMap<>();
	    body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
	    body.put("error", "Unauthorized");
	    body.put("message", authException.getMessage());
	    body.put("path", request.getServletPath());

	    final ObjectMapper mapper = new ObjectMapper();
	    mapper.writeValue(response.getOutputStream(), body);
		
	}
}
