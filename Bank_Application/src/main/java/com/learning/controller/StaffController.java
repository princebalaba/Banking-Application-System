package com.learning.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.learning.entity.BeneficiaryDTO;
import com.learning.entity.Transaction;
import com.learning.entity.UserDTO;
import com.learning.enums.ERole;
import com.learning.exceptions.TransactionInvalidException;
import com.learning.exceptions.UnauthrorizedException;

import com.learning.enums.Active;
import com.learning.enums.Approved;
import com.learning.enums.CreditDebit;
import com.learning.jwt.JwtUtils;
import com.learning.payload.requset.SigninRequest;
import com.learning.payload.requset.TransferRequestStaff;
import com.learning.payload.response.JwtResponse;
import com.learning.payload.response.StaffGetAccountResponse;
import com.learning.repo.BeneficiaryRepo;
import com.learning.security.service.UserDetailsImpl;
import com.learning.service.AccountService;
import com.learning.service.BeneficiaryService;
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
	AccountService accountService;
	
	@Autowired
	BeneficiaryService beneficiaryService;
	
	
	@Autowired
	BeneficiaryRepo beneficiaryRepo;
	
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

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtUtils.generateToken(authentication);
		// get user data/ principal
	
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetailsImpl.getAuthorities().stream().map(e -> e.getAuthority())
				.collect(Collectors.toList());
		// return new token
		boolean isadmin = false; 
		for( int i =0 ; i < roles.size() ; i++) {
			if (roles.get(i).equals(ERole.ROLE_STAFF.name())) {
				isadmin= true;
			}
		}
		if(!isadmin) {
			throw new UnauthrorizedException("unauthorized access");
		}
		return ResponseEntity.status(200)
				.body(new JwtResponse(jwt, userDetailsImpl.getId(), userDetailsImpl.getUsername(), roles));

	}
	@PreAuthorize("hasRole('STAFF')")
	@GetMapping("/account/{accountNo}")
	public ResponseEntity<?> getStatementOfAccount(@PathVariable ("accountNo") Long accountNo){
		AccountDTO response = accountService.getAccount(accountNo);
		
		
		return ResponseEntity.status(200)
				.body(response);
		
	}
//	/josh is working on it 
	@PreAuthorize("hasRole('STAFF')")
	@GetMapping("/beneficiary")
	public ResponseEntity<?> getUnapprovedBeneficiaries(){
		
		
		
		List<StaffGetAccountResponse> response = new ArrayList<>() ;
		List<BeneficiaryDTO> toBeApproved = beneficiaryService.getAllUnapprovedBeneficiaries();
				
		
		
		return ResponseEntity.status(200)
				.body(toBeApproved);
		
	}
	@PreAuthorize("hasRole('STAFF')")
	@PutMapping("/beneficiary/{beneficiaryId}")
	public ResponseEntity<?> getApprovedBeneficiary (@PathVariable("beneficiaryId") Long beneficiaryId){
		
		Boolean benefiBoolean = beneficiaryRepo.existsById(beneficiaryId);
		if(!benefiBoolean) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Sorry beneficiary not approved");
		}
		
		
		BeneficiaryDTO beneficiaryToBeApproved = beneficiaryService.getBeneficiaryById(beneficiaryId);
		
		if(beneficiaryToBeApproved.getActive().equals(Active.NO)) {
			beneficiaryToBeApproved.setActive(Active.YES);
		}else {
			beneficiaryToBeApproved.setActive(Active.NO);
		}
	
		
		beneficiaryService.updateBeneficiary(beneficiaryToBeApproved);
		return ResponseEntity.status(200)
				.body(beneficiaryToBeApproved);
		
	}
	@PreAuthorize("hasRole('STAFF')")
	@GetMapping("/accounts/approve")
	public ResponseEntity<?> getUnapprovedAccounts() {
		
		//no impl yet
		return ResponseEntity.ok(staffService.getUnapprovedAccounts());
	}
	@PreAuthorize("hasRole('STAFF')")
	@PutMapping("/accounts/approve/{accountId}")
	public ResponseEntity<?> approveAccount( @PathVariable("accountId") Long accountId) {
	AccountDTO response = accountService.getAccount(accountId);
	
	if(response.getApproved().equals(Approved.NO)){
		response.setApproved(Approved.YES);
		
	}else {
		response.setApproved(Approved.NO);
	}
	
	AccountDTO updatedAccount = accountService.updateAccount(response);
		
		return ResponseEntity.ok(response);
	}
//
	@PreAuthorize("hasRole('STAFF')")
	@GetMapping("/customer")
	public ResponseEntity<?> getAllCustomers() {
		return ResponseEntity.ok(staffService.getAllCustomers());
	}
//
	@PutMapping("/customer/{customerId}")
	public ResponseEntity<?> setCustomerEnabledDisabled(@PathVariable("customerId") Long customerId) {
		staffService.setCustomerEnabledDisabled(customerId);
		return ResponseEntity.ok(customerId +"has been enabled");
	}
	
	//////////////
	
	
	
	
	@PreAuthorize("hasRole('STAFF')")
	@GetMapping("/customer/{customerID}")
	public ResponseEntity<?> getCustomer(@PathVariable("customerID") Long customerId) {
		
	
		return ResponseEntity.ok(	staffService.getCustomerDetailsByID(customerId));
	}
	

	
	@PreAuthorize("hasRole('STAFF')")
	@PutMapping("/transfer")
	public ResponseEntity<?> staffTransfer(@RequestBody TransferRequestStaff request) {
		
		
		Optional<AccountDTO> accountFrom = accountService.findAccountById(request.getFromAccNumber());
		Optional<AccountDTO> toAccount = accountService.findAccountById(request.getToAccNumber());
		Double amount = request.getAmount();
		String reason = request.getReason();
		if(accountFrom.isEmpty() || toAccount.isEmpty()) {
			throw new TransactionInvalidException("from " + request.getFromAccNumber() + " to "
					+ request.getToAccNumber() + " Account Number Not Valid");
		}
		if(!userService.userExistsById(accountFrom.get().getCustomerId())) {
			throw new TransactionInvalidException("from " + request.getFromAccNumber() + " to "
					+ request.getToAccNumber() + " Account Number Not Valid");
		}
		AccountDTO from = accountFrom.get();
		AccountDTO to = toAccount.get();
		
		//temp for accountFrom 
				AccountDTO temp = from;
			
				temp.setAccountBalance(from.getAccountBalance() - amount);

				Transaction transaction = new Transaction();
				transaction.setDateTime(LocalDateTime.now());
				transaction.setReference(request.getReason());
				transaction.setAmount(request.getAmount());
				transaction.setType(CreditDebit.CREDIT);
				Set<Transaction> transactions = temp.getTransactions();
				transactions.add(transaction);
				temp.setTransactions(transactions);
				accountService.updateAccount(from.getAccountNumber(), temp);

				
				AccountDTO temp2 = to;
				temp2.setAccountBalance(temp2.getAccountBalance() + amount);
			
				Transaction transaction2 = new Transaction();
				transaction2.setDateTime(LocalDateTime.now());
				transaction2.setReference(request.getReason());
				transaction2.setAmount(request.getAmount());
				transaction2.setType(CreditDebit.DEBIT);
				Set<Transaction> transactions2 = temp2.getTransactions();
				transactions2.add(transaction2);
				temp2.setTransactions(transactions2);
				accountService.updateAccount(to.getAccountNumber(), temp2);
		
		
		return ResponseEntity.status(200).body("transfer successs");
	}

}
