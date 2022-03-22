package com.learning.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Role;
import com.learning.entity.StaffDTO;
import com.learning.enums.ERole;
import com.learning.enums.EStatus;
import com.learning.exceptions.AccountDisabledException;
import com.learning.exceptions.IdNotFoundException;
import com.learning.exceptions.RoleNotFoundException;
import com.learning.exceptions.UnauthrorizedException;
import com.learning.jwt.JwtUtils;
import com.learning.payload.requset.CreateStaffRequest;
import com.learning.payload.requset.SetEnableRequest;
import com.learning.payload.requset.SigninRequest;
import com.learning.payload.response.AdminGetStaffResponse;
import com.learning.payload.response.JwtResponse;
import com.learning.security.service.UserDetailsImpl;
import com.learning.service.impl.AdminServiceImpl;
import com.learning.service.impl.RoleServiceImpl;
import com.learning.service.impl.StaffServiceImpl;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 11.-오후 4:43:52
 */
@CrossOrigin
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
	@Autowired
	private StaffServiceImpl staffService ;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> signin(@Valid @RequestBody SigninRequest signinRequest) {
	
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtUtils.generateToken(authentication);
		// get user data/ principal
	
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetailsImpl.getAuthorities().stream().map(e -> e.getAuthority())
				.collect(Collectors.toList());
		// return new token
		boolean isadmin = false; 
		for( int i =0 ; i < roles.size() ; i++) {
			if (roles.get(i).equals(ERole.ROLE_SUPER_ADMIN.name())) {
				isadmin= true;
			}
		}
		if(!isadmin) {
			throw new UnauthrorizedException("unauthorized access");
		}
		
		return ResponseEntity.status(200).body(new JwtResponse(jwt, userDetailsImpl.getId(), userDetailsImpl.getUsername(), roles));

	}
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	@PostMapping("/staff")
	public ResponseEntity<?> createStaff(@Valid @RequestBody CreateStaffRequest request) {
		
		StaffDTO staff = new StaffDTO();
		staff.setFullname(request.getStaffFullName());
		staff.setUsername(request.getStaffUserName());
		staff.setPassword(passwordEncoder.encode(request.getStaffPassword()));
		staff.setStatus(EStatus.ENABLE);

			Role role = roleService.getRoleName(ERole.ROLE_STAFF)
				.orElseThrow(() -> new RoleNotFoundException("this staff role has not found"));
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		staff.setRoles(roles);
		adminService.addStaff(staff);
		return ResponseEntity.status(200).body("staff added");

	}
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	@GetMapping("/staff")
	public ResponseEntity<?> getAllStaff() {
		List<StaffDTO> staffs = new ArrayList<>();
		staffs = adminService.getAllStaff();
		List<AdminGetStaffResponse> response = new ArrayList();
		for (int i = 0 ; i < staffs.size() ; i++) {
			AdminGetStaffResponse staff = new AdminGetStaffResponse();
			staff.setStaffId(staffs.get(i).getId());
			staff.setStaffName(staffs.get(i).getFullname());
			staff.setStatus(staffs.get(i).getStatus());
			response.add(staff);
		}
		return ResponseEntity.status(200).body(response);

	}
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	@PutMapping("/{staffid}")
	public ResponseEntity<?> setStaffEnabled(@PathVariable ("staffid") long staffid){
	
		StaffDTO staff = (StaffDTO) staffService.getUserById(staffid).orElseThrow(()-> new IdNotFoundException("Staff status not changed"));
		System.out.println("stat: " + staff.getStatus().equals(EStatus.DISABLED));
		if(staff.getStatus().equals(EStatus.DISABLED)) {
			staff.setStatus(EStatus.ENABLE);
		} else {
			staff.setStatus(EStatus.DISABLED);	
		}
		System.out.println(staff);
		adminService.updateStaffStatus(staff);
		Map<String, String> response = new LinkedHashMap<>();
		response.put("staffId", " : " + staffid);
		response.put("status", " : " + staff.getStatus());
		return ResponseEntity.status(200).body(response);
	}
}
