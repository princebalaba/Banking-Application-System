package com.learning.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.AccountDTO;
import com.learning.entity.Role;
import com.learning.entity.UserDTO;
import com.learning.enums.Approved;
import com.learning.enums.ERole;
import com.learning.exceptions.IdNotFoundException;
import com.learning.jwt.JwtUtils;
import com.learning.payload.requset.AccountRequest;
import com.learning.payload.requset.SigninRequest;
import com.learning.payload.requset.SignupRequest;
import com.learning.repo.RoleRepo;
import com.learning.repo.UserRepository;
import com.learning.response.AccountResponseEntity;
import com.learning.response.CustomerRegisterResponse;
import com.learning.response.JwtResponse;
import com.learning.security.service.UserDetailsImpl;
import com.learning.service.StaffService;
import com.learning.service.UserService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
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
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signupRequest) {
		// can u create user object?
		// can u initialize the values based on the signuprequest object?
		
		

		UserDTO user = new UserDTO();

		ERole eRole = ERole.ROLE_CUSTOMER;
		Role role = new Role();
		role.setRoleName(eRole);
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		user.setUsername(signupRequest.getUserName());
		user.setFullname(signupRequest.getFullName());
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		
		user.setRoles(roles);
		UserDTO newUser = userService.addUser(user);
		CustomerRegisterResponse response = new CustomerRegisterResponse();
		response.setCustomerId(newUser.getId());
		response.setFullName(newUser.getFullname());
//		response.setPhoneNumber(null); // for null now 
		response.setPassword(newUser.getPassword());
		response.setUserName(newUser.getUsername());


		return ResponseEntity.status(201).body(response);
				
			

	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> signin(@Valid @RequestBody SigninRequest signinRequest) {
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
		return ResponseEntity.status(200).body(new JwtResponse(jwt, userDetailsImpl.getId(), userDetailsImpl.getUsername(), roles));

	}
	
	@GetMapping("/{customerID}/account")
	public ResponseEntity<?> getCustomerAccuountById(@PathVariable ("customerID") long customerId) {
		Set<AccountDTO> accounts = new HashSet<>(); 
		UserDTO user = userService.getUserById(customerId).orElseThrow(()-> new IdNotFoundException("id has not found for the user" ));
		accounts = user.getAccount();
		List<AccountResponseEntity> responses = new ArrayList<>();
		accounts.forEach(e -> {
			AccountResponseEntity account = new AccountResponseEntity();
			account.setAccountBalance(e.getAccountBalance());
			account.setAccountNumber(e.getAccountNumber());
			// create accounts
			
		});
		
		return ResponseEntity.ok("working");

	}
	
	@PostMapping(":{customerId}/account")
		public ResponseEntity<?> createAccount(@PathVariable ("customerID") long customerId, @RequestBody AccountRequest request) {
			AccountDTO account = new AccountDTO (); 
			account.setAccountBalance(request.getAccountBalance());
			account.setAccountType(request.getAccountType());
			account.setCustomerId(customerId);
			account.setApproved(Approved.NO);
			LocalDateTime now = LocalDateTime.now();  
			account.setDateOfCreation(now);
			UserDTO user = userService.getUserById(customerId).orElseThrow(()-> new IdNotFoundException("Id not found")); 
			Set<AccountDTO> accounts = user.getAccount();
			accounts.add(account);
			user.setAccount(accounts);
			UserDTO updated = userService.updateUser(user, customerId);
			return ResponseEntity.status(200).body(updated);
			
	}
	
	

}
