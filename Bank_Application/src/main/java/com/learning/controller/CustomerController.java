package com.learning.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Role;
import com.learning.entity.UserDTO;
import com.learning.enums.ERole;
import com.learning.jwt.JwtUtils;
import com.learning.payload.requset.SignupRequest;
import com.learning.repo.RoleRepo;
import com.learning.repo.UserRepository;
import com.learning.service.UserService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	UserService userService; 
	@Autowired
	private RoleRepo roleRepository;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signupRequest) {
		// can u create user object?
		// can u initialize the values based on the signuprequest object?
		ERole roles = ERole.ROLE_CUSTOMER;
	

		UserDTO user = new UserDTO();

		
	
		user.setUsername(signupRequest.getUserName());
		user.setFullname(signupRequest.getFullName());
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		
		userService.addUser(user);



		return ResponseEntity.status(201).body(user);
				
			

	}


}
