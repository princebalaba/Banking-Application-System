package com.learning.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.apierrors.ApiError;
import com.learning.entity.AccountDTO;
import com.learning.entity.BeneficiaryDTO;
import com.learning.entity.Role;
import com.learning.entity.UserDTO;
import com.learning.enums.AccountType;
import com.learning.enums.Active;
import com.learning.enums.Approved;
import com.learning.enums.ERole;
import com.learning.exceptions.BalanceNonPositiveException;
import com.learning.exceptions.IdNotFoundException;
import com.learning.exceptions.RoleNotFoundException;
import com.learning.exceptions.SecretDetailsDoNotMatchException;
import com.learning.exceptions.TransactionInvalidException;
import com.learning.jwt.JwtUtils;
import com.learning.payload.requset.AccountRequest;
import com.learning.payload.requset.BeneficiaryPayload;
import com.learning.payload.requset.SigninRequest;
import com.learning.payload.requset.SignupRequest;
import com.learning.payload.requset.TransferRequest;
import com.learning.payload.requset.UpdateRequest;
import com.learning.payload.response.AccountApproaval;
import com.learning.payload.response.AccountResponseEntity;
import com.learning.payload.response.AccountTransactionResponse;
import com.learning.payload.response.BeneficiaryAddedResponse;
import com.learning.payload.response.CustomerGetBeneficiaries;
import com.learning.payload.response.CustomerRegisterResponse;
import com.learning.payload.response.GetCustomerResponse;
import com.learning.payload.response.JwtResponse;
import com.learning.payload.response.UpdateResponse;
import com.learning.security.service.UserDetailsImpl;
import com.learning.service.AccountService;
import com.learning.service.StaffService;
import com.learning.service.UserService;
import com.learning.service.impl.RoleServiceImpl;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	UserService userService;
	@Autowired
	StaffService staffService;

//	@Autowired
//	AccountService accountService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private RoleServiceImpl roleService;
	@Autowired
	private AccountService accountService;

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signupRequest) {
		UserDTO user = new UserDTO();
		Role role = roleService.getRoleName(ERole.ROLE_CUSTOMER)
				.orElseThrow(() -> new RoleNotFoundException("this role has not found"));
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

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtils.generateToken(authentication);
		// get user data/ principal

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
		AccountType roles = request.getAccountType();

		Approved approved = Approved.NO;
		if (request.getAccountBalance() < 0) {
			throw new BalanceNonPositiveException("Account cannot be created");
		}

		AccountDTO account = new AccountDTO();
		account.setAccountBalance(request.getAccountBalance());
		account.setAccountType(roles);
		account.setCustomerId(customerId);
		account.setApproved(approved);
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
		UserDTO user = staffService.getUserById(customerId)
				.orElseThrow(() -> new IdNotFoundException("Please check Account Number"));
		Set<AccountDTO> accounts = user.getAccount();

		accounts.forEach(e -> {
			if (e.getAccountNumber() == accountNo) {
				e.setApproved(Approved.YES);

			}
		});

		user.setAccount(accounts);
		userService.updateUser(user, customerId);
		AccountApproaval response = new AccountApproaval();
		response.setAccountNumber(accountNo);
		accounts.forEach(e -> {
			if (e.getAccountNumber() == accountNo) {
				response.setAccountStatus(e.getApproved());

			}
		});

		return ResponseEntity.status(200).body(response);
	}

	@GetMapping("{customerId}/account")
	public ResponseEntity<?> getAccounts(@PathVariable("customerId") long customerId) {
		Optional<UserDTO> data = userService.getUserById(customerId);
		if (data.isEmpty()) {
			throw new IdNotFoundException("Sorry Customer With " + customerId + " not found");

		}
		UserDTO user = data.get();
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

	@GetMapping("{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable("customerId") long customerId) {
		UserDTO user = userService.getUserById(customerId)
				.orElseThrow(() -> new IdNotFoundException("Sorry Customer With " + customerId + " not found"));
		GetCustomerResponse response = new GetCustomerResponse();
		response.setFullName(user.getFullname());
		response.setAadhar(user.getAadhar());
		response.setPan(user.getPan());
		response.setPhone(user.getPhone());
		response.setUsername(user.getUsername());

		return ResponseEntity.status(200).body(response);
	}

	@GetMapping("{customerId}/account/{accountid}")
	public ResponseEntity<?> getAccountFromId(@PathVariable("customerId") long customerId,
			@PathVariable("accountid") long accountid) {
		UserDTO user = userService.getUserById(customerId)
				.orElseThrow(() -> new IdNotFoundException("Sorry customer With " + customerId + " not found"));
		AccountDTO account = null;
		Iterator<AccountDTO> it = user.getAccount().iterator();
		while (it.hasNext()) {
			AccountDTO acc = it.next();
			if (acc.getAccountNumber() == accountid) {
				account = acc;
			}
		}
		if (account == null) {
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

	@GetMapping("{customerId}/beneficiary")
	public ResponseEntity<?> getBeneficiary(@PathVariable("customerId") long customerId) {
		List<CustomerGetBeneficiaries> response = userService.getCustomerBeneficiaries(customerId);
		return ResponseEntity.status(200).body(response);

	}

	// Make sure user has an account before adding beneficiary. or errors
	@PostMapping("{customerId}/beneficiary")
	public ResponseEntity<?> createBeneficiary(@PathVariable("customerId") Long customerId,
			@RequestBody BeneficiaryPayload payload) {
		System.out.println("Payload: " + payload.getAccountType() + ". " + payload.getAccountNumber() + ". "
				+ payload.getActive());
		Boolean userExists = userService.userExistsById(customerId);
		Boolean accountExists = accountService.accountExists(payload.getAccountNumber());
		if (!userExists || !accountExists) {
			throw new IdNotFoundException("Sorry Beneficiary With " + customerId + " not added");
		}

		if (accountExists) {
			BeneficiaryDTO ben = new BeneficiaryDTO();
//		System.out.println("Acc exists");

			ben.setAccountNumber(payload.getAccountNumber());
			Long beneficiaryAccountUserId = accountService.getAccountByAccountNumber((payload.getAccountNumber()))
					.getCustomerId();
			String beneficiaryName = userService.getUser(beneficiaryAccountUserId).getFullname();
			ben.setName(beneficiaryName);
			ben.setActive(Active.YES);
			ben.setAccountType(payload.getAccountType());
			ben.setAddedDate(LocalDate.now());
			ben.setUserId(customerId);

			UserDTO user = userService.getUser(customerId);
			Set<BeneficiaryDTO> userBeneficiaries = user.getBeneficiaries();
			userBeneficiaries.add(ben);
			user.setBeneficiaries(userBeneficiaries);
			UserDTO updatedUser = userService.updateUser(user);

			BeneficiaryAddedResponse response = new BeneficiaryAddedResponse();
			response.setActive(ben.getActive());
			response.setBeneficiaryAccountNo(ben.getAccountNumber());
			response.setBeneficiaryName(ben.getName());

			return ResponseEntity.status(200).body("Beneficiary with: " + payload.getAccountNumber() + "  added");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Failed to add Beneficiary with " + payload.getAccountNumber());

		}

	}

	@DeleteMapping("{customerId}/beneficiary/{beneficiaryId}")
	public ResponseEntity<?> deleteBeneficiary(@PathVariable("customerId") Long customerId,
			@PathVariable("beneficiaryId") Long beneficiaryId) {
		Boolean userExists = userService.userExistsById(customerId);

		Boolean beneficiaryExists = userService.userExistsById(customerId);

		if (!beneficiaryExists || !userExists) {

			throw new IdNotFoundException("Beneficiary Not Deleted");
		}

		UserDTO user = userService.getUser(customerId);

		Set<BeneficiaryDTO> userBens = user.getBeneficiaries();
		userBens.removeIf(ben -> ben.getAccountNumber().equals(beneficiaryId));

		user.setBeneficiaries(userBens);

		UserDTO updatedUser = userService.updateUser(user);

		return ResponseEntity.status(200).body("Beneficiary Deleted Scuccessfully");

	}

	@PutMapping("/transfer")
	public ResponseEntity<?> transfer(@Valid @RequestBody TransferRequest request) {
		UserDTO user = userService.getUserById(request.getCustomer())
				.orElseThrow(() -> new TransactionInvalidException("from " + request.getFromAccNumber() + " to "
						+ request.getToAccNumber() + " Account Number Not Valid"));
		AccountDTO accountFrom = accountService.getAccount(request.getFromAccNumber());
		AccountDTO toAccount = accountService.getAccount(request.getToAccNumber());
		Double amount = request.getAmount();
		String reason = request.getReason();

		AccountDTO temp = accountFrom;
		// deal with the user from
		temp.setAccountBalance(accountFrom.getAccountBalance() - amount);
//		Set<AccountDTO> accountsFrom = user.getAccount();
//		accountsFrom.remove(accountFrom);
//		accountsFrom.add(temp);
//		user.setAccount(accountsFrom);
//		userService.updateUser(user, request.getCustomer());
		accountService.updateAccount(accountFrom.getAccountNumber(), temp);

		// deal with the uer to
		UserDTO toAccountHolder = userService.getUserById(toAccount.getCustomerId())
				.orElseThrow(() -> new TransactionInvalidException("from " + request.getFromAccNumber() + " to "
						+ request.getToAccNumber() + " Account Number Not Valid"));
		temp = toAccount;
		temp.setAccountBalance(temp.getAccountBalance() + amount);
		accountService.updateAccount(toAccount.getAccountNumber(), temp);
//		Set<AccountDTO> accountsTo = toAccountHolder.getAccount();
//		accountsTo.remove(toAccount);
//		accountsTo.add(temp);
//		toAccountHolder.setAccount(accountsTo);
//		userService.updateUser(toAccountHolder, toAccount.getCustomerId());

		return ResponseEntity.status(200).body("transaction Scuccessfully");

	}

	@GetMapping("/{username}/forgot/question/answer")
	public ResponseEntity<?> secretQuestionAnswer(@PathVariable UpdateRequest updatesRequest, UpdateResponse updateResponse
			) throws SecretDetailsDoNotMatchException {
		
		// updatesRequest.getSecretQuestion();
		// updateResponse.getSecretAnswer();
		if (updateResponse.getSecretAnswer().equals(updatesRequest.getSecretAnswer())) {
			System.out.println("Secret Answer matches Secret Question");
		} else {
			throw new SecretDetailsDoNotMatchException("Sorry your secret details are not matching");
		}

		return ResponseEntity.status(200).body("Details Validated");

	}

	@PutMapping("/{username}/forgot")
	public ResponseEntity<?> updatePassword(@PathVariable SigninRequest signinRequest, SigninRequest newPassword) {
		signinRequest.getUserName();
		newPassword.setPassword(newPassword.getPassword());
		if(newPassword.equals(signinRequest.getPassword())){
			System.out.println("Sorry password not updated");
		}else {
			//System.out.println("new password updated");
		}
		
			
		
		return ResponseEntity.status(200).body("new password updated");
	}

}
