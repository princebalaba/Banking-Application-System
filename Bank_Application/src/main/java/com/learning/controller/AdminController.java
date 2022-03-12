package com.learning.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Role;
import com.learning.entity.StaffDTO;
import com.learning.enums.ERole;
import com.learning.enums.EStatus;
import com.learning.exceptions.AccountDisabledException;
import com.learning.exceptions.RoleNotFoundException;
import com.learning.jwt.JwtUtils;
import com.learning.payload.requset.CreateStaffRequest;
import com.learning.payload.requset.SetEnableRequest;
import com.learning.payload.requset.SigninRequest;
import com.learning.payload.response.JwtResponse;
import com.learning.security.service.UserDetailsImpl;
import com.learning.service.impl.AdminServiceImpl;
import com.learning.service.impl.RoleServiceImpl;
import com.learning.service.impl.StaffServiceImpl;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 11.-오후 4:43:52
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private RoleServiceImpl roleService;
	@Autowired
	private AdminServiceImpl adminService ;
	
//	@PostMapping("/authenticate")
//	public ResponseEntity<?> signin(@Valid @RequestBody SigninRequest signinRequest) {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(signinRequest.getUserName(), signinRequest.getPassword()));
//
//		
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		
//		String jwt = jwtUtils.generateToken(authentication);
//		// get user data/ principal
//	
////		StaffDetailsImpl staffDetailsImpl = (StaffDetailsImpl) authentication.getPrincipal();
//
////		List<String> roles = staffDetailsImpl.getAuthorities().stream().map(e -> e.getAuthority())
////				.collect(Collectors.toList());
////		EStatus status= staffDetailsImpl.getStatus();
////		if(status.equals(EStatus.DISABLED)) {
////			throw new AccountDisabledException("this account has been disabled");
////		}
////		// return new token
////		return ResponseEntity.status(200)
////				.body(new JwtResponse(jwt, staffDetailsImpl.getId(), staffDetailsImpl.getUsername(), roles));
//
//	}
	
	@PostMapping("/staff")
	public ResponseEntity<?> createStaff(@Valid @RequestBody CreateStaffRequest request) {
		
//		StaffDTO staff = new StaffDTO(1,EStatus.ENABLE, null,request.getStaffUserName(), request.getStaffFullName(), passwordEncoder.encode(request.getStaffPassword()));;
//		staff.setFullname(request.getStaffFullName());
//		staff.setUsername(request.getStaffUserName());
//		staff.setPassword(passwordEncoder.encode(request.getStaffPassword()));
//		Optional<Role> role1 = roleService.getRoleName(ERole.ROLE_STAFF);

			Role role = roleService.getRoleName(ERole.ROLE_STAFF)
				.orElseThrow(() -> new RoleNotFoundException("this staff role has not found"));
		Set<Role> roles = new HashSet<>();
		roles.add(role);
//		staff.setRoles(roles);
//		System.out.println(staff);
//		adminService.addStaff(staff);
		return ResponseEntity.status(200).body("staff added");

	}
	
	@GetMapping("/staff")
	public ResponseEntity<?> getAllStaff() {
		List<StaffDTO> staffs = new ArrayList<>();
		staffs = adminService.getAllStaff();
		return ResponseEntity.status(200).body(staffs);

	}
	
	public ResponseEntity<?> setStaffEnabled(@RequestBody SetEnableRequest request){
		return ResponseEntity.ok(adminService.setEnable(request));
	}
}
