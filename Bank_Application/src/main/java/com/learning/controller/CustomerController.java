package com.learning.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.learning.entity.AccountTypeDTO;
import com.learning.entity.ApprovedDTO;
import com.learning.entity.Role;
import com.learning.entity.UserDTO;
import com.learning.enums.AccountType;
import com.learning.enums.Approved;
import com.learning.enums.ERole;
import com.learning.exceptions.IdNotFoundException;
import com.learning.exceptions.NoDataFoundException;
import com.learning.exceptions.RoleNotFoundException;
import com.learning.jwt.JwtUtils;
import com.learning.payload.requset.AccountRequest;
import com.learning.payload.requset.SigninRequest;
import com.learning.payload.requset.SignupRequest;
import com.learning.payload.requset.UpdateRequest;
import com.learning.payload.response.AccountApproaval;
import com.learning.payload.response.AccountResponseEntity;
import com.learning.payload.response.AccountTransactionResponse;
import com.learning.payload.response.CustomerRegisterResponse;
import com.learning.payload.response.JwtResponse;
import com.learning.payload.response.UpdateResponse;
import com.learning.repo.RoleRepo;
import com.learning.repo.UserRepository;
import com.learning.security.service.UserDetailsImpl;
import com.learning.service.StaffService;
import com.learning.service.UserService;
import com.learning.service.impl.AccountTypeServiceImpl;
import com.learning.service.impl.ApprovedServiceImpl;
import com.learning.service.impl.RoleServiceImpl;
import com.learning.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	UserServiceImpl userService;
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
	@Autowired
	private AccountTypeServiceImpl accountTypeService;
	@Autowired
	private ApprovedServiceImpl approvedService;

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signupRequest) {
		// can u create user object?
		// can u initialize the values based on the signuprequest object?

		UserDTO user = new UserDTO();

		Role role = roleService.getRoleName(ERole.ROLE_CUSTOMER)
				.orElseThrow(() -> new RoleNotFoundException("this role has not found"));
//		Role role = new Role();
//		role.setRoleName(eRole);
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

	@PostMapping("/{customerId}/account")
	public ResponseEntity<?> createAccount(@PathVariable("customerId") long customerId,
			@RequestBody AccountRequest request) {
		String type = request.getAccountType().name();
		Optional<AccountTypeDTO> roles = accountTypeService.getAccountTypeByName(request.getAccountType());
		if (roles.isEmpty()) {
			throw new RoleNotFoundException("role is not found ");
		}
		Optional<ApprovedDTO> approved = approvedService.getRoleName(Approved.NO);
		if (approved.isEmpty()) {
			throw new RoleNotFoundException("role is not found ");
		}

		AccountDTO account = new AccountDTO();
		account.setAccountBalance(request.getAccountBalance());
		account.setAccountType(roles.get());
		account.setCustomerId(customerId);
		account.setApproved(approved.get());
		LocalDateTime now = LocalDateTime.now();
		account.setDateOfCreation(now);
//		double accNo = Math.random() * 100000000;
//		long roundAccNo = (long) accNo;
//		account.setAccountNumber(roundAccNo);
		UserDTO user = userService.getUserById(customerId).orElseThrow(() -> new IdNotFoundException("Id not found"));
		Set<AccountDTO> accounts = user.getAccount();
		accounts.add(account);
		user.setAccount(accounts);
		userService.updateUser(user, customerId);

		AccountResponseEntity response = new AccountResponseEntity();
		response.setAccountBalance(account.getAccountBalance());
		response.setAccountNumber(account.getAccountNumber());
		response.setAccountType(account.getAccountType());
		response.setApproved(account.getApproved());
		response.setCustomerId(account.getCustomerId());
		response.setDateOfCreation(account.getDateOfCreation());
		return ResponseEntity.status(200).body(response);

	}

//	@PreAuthorize("hasRole('STAFF')")
	@PutMapping("{customerId}/account/{accountNo}")
	public ResponseEntity<?> approveAccount(@PathVariable("customerId") long customerId,
			@PathVariable("accountNo") long accountNo, @RequestBody AccountRequest request) {
		// possibly user AccountRequest
		UserDTO user = staffService.getUserById(customerId).orElseThrow(() -> new IdNotFoundException("Id not found"));
		Set<AccountDTO> accounts = user.getAccount();

		accounts.forEach(e -> {
			if (e.getAccountNumber() == accountNo) {
				e.setApproved(approvedService.getRoleName(Approved.YES).get());

			}
		});

		user.setAccount(accounts);
		userService.updateUser(user, customerId);
		AccountApproaval response = new AccountApproaval();
		response.setAccountNumber(accountNo);
		accounts.forEach(e -> {
			if (e.getAccountNumber() == accountNo) {
				response.setAccountStatus(e.getApproved().getApprovedStatus());

			}
		});

		return ResponseEntity.status(200).body(response);
	}

	@GetMapping("{customerId}/account")
	public ResponseEntity<?> getAccounts(@PathVariable("customerId") long customerId) {
		UserDTO user = userService.getUserById(customerId).orElseThrow(() -> new IdNotFoundException("Id not found "));
		Set<AccountDTO> accounts = user.getAccount();
		Set<AccountResponseEntity> responses = new HashSet<>();
		accounts.forEach(e -> {
			AccountResponseEntity response = new AccountResponseEntity();
			response.setAccountBalance(e.getAccountBalance());
			response.setAccountNumber(e.getAccountNumber());
			response.setAccountType(e.getAccountType());
			response.setApproved(e.getApproved());
			response.setCustomerId(e.getCustomerId());
			response.setDateOfCreation(e.getDateOfCreation());
			responses.add(response);

		});

		return ResponseEntity.status(200).body(responses);
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<?> updateCustomer(@PathVariable("customerId") long customerId,
			@Valid @RequestBody UpdateRequest request) {
		UserDTO user = new UserDTO();
		 userService.getUserById(customerId).orElseThrow(() -> new IdNotFoundException("id not found"));
		user.setFullname(request.getFullname());
		user.setPhone(request.getPhone());
		user.setPan(request.getPan());
		user.setAadhar(request.getAadhar());
		user.setSecretQuestion(request.getSecretQuestion());
		user.setSecretAnswer(request.getSecretAnswer());
		user.setPanimage(request.getPanimage());
		user.setAarchar(request.getAarchar());

		UserDTO updated = userService.updateUser(user, customerId);
		UpdateResponse response = new UpdateResponse();
		response.setCustomerId(updated.getId());
		response.setFullname(updated.getFullname());
		response.setPhone(updated.getPhone());
		response.setPan(updated.getPhone());
		response.setAadhar(updated.getAadhar());
		response.setSecretQuestion(updated.getSecretQuestion());
		response.setSecretAnswer(updated.getSecretAnswer());
		response.setPanimage(updated.getPanimage());
		response.setAarchar(updated.getPanimage());

		return ResponseEntity.status(200).body(response);
		
	}
	
	@GetMapping("{customerId}/account/{accountid}")
	public ResponseEntity<?> getAccountFromId(@PathVariable("customerId") long customerId,@PathVariable("accountid") long accountid){
		UserDTO user = userService.getUserById(customerId).orElseThrow(()-> new IdNotFoundException("ID not found"));
		AccountDTO account = null; 
		Iterator<AccountDTO> it = user.getAccount().iterator();
		while(it.hasNext()) {
			AccountDTO acc= it.next();
			if(acc.getAccountNumber()==accountid) {
				account = acc;
			}
		}
		if(account == null) {
			throw new IdNotFoundException("Id not found");
		}
		AccountTransactionResponse response = new AccountTransactionResponse();
		response.setAccountBalance(account.getAccountBalance());
		response.setAccountNumber(account.getAccountNumber());
		response.setAccountType(account.getAccountType());
		response.setEnabled(account.getEnableDisabled());
		response.setTransactions(account.getTransactions());
		return ResponseEntity.status(200).body(response);
	}
	@PostMapping("{customerId}/beneficiary")
	public ResponseEntity<?> createBeneficiary(@PathVariable("customerId") long customerId,@PathVariable("accountid") long accountid){
		
		
		
		return null;
		
	}

}
