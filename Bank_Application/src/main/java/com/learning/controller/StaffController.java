package com.learning.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.UserDTO;
import com.learning.jwt.JwtUtils;
import com.learning.payload.requset.SigninRequest;
import com.learning.response.JwtResponse;
import com.learning.security.service.UserDetailsImpl;
import com.learning.service.StaffService;
import com.learning.service.UserService;
import com.learning.service.impl.RoleServiceImpl;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
	@Autowired
	UserService userService;
	@Autowired
	StaffService staffService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private RoleServiceImpl roleService;

	
	@PostMapping("/authenticate")
	public ResponseEntity<?> signinStaff(@Valid @RequestBody SigninRequest signinRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getUserName(), signinRequest.getPassword()));
		/*
		 * Interface defining the minimum security information associated with the
		 * current threadof execution. The security context is stored in a
		 * SecurityContextHolder.
		 * 
		 * Changes the currently authenticated principal, or removes the
		 * authenticationinformation.
		 * 
		 */
		System.out.println(signinRequest.toString());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("passed context holder");
		String jwt = jwtUtils.generateToken(authentication);
		// get user data/ principal
		System.out.println(jwt);
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetailsImpl.getAuthorities().stream().map(e -> e.getAuthority())
				.collect(Collectors.toList());
		// return new token
		return ResponseEntity.status(200)
				.body(new JwtResponse(jwt, userDetailsImpl.getId(), userDetailsImpl.getUsername(), roles));

	}
	@GetMapping("/account/{accountNo}")
	public ResponseEntity<?> getStatementOfAccount(@Valid @RequestBody SigninRequest signinRequest) {
	
		UserDTO response = null;
		
		
		return ResponseEntity.status(200)
				.body(response);
		
	}


}
