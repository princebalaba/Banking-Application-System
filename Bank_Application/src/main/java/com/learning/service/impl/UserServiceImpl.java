package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.AccountDTO;
import com.learning.entity.BeneficiaryDTO;
import com.learning.entity.UserDTO;
import com.learning.exceptions.IdNotFoundException;
import com.learning.payload.response.CustomerGetBeneficiaries;
import com.learning.repo.BeneficiaryRepo;
import com.learning.repo.UserRepository;
import com.learning.service.UserService;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 8.-오후 4:29:09
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepo ;
	//addUser
	
	@Autowired
	BeneficiaryRepo beneficiaryRepo;
	@Override
	public UserDTO addUser(UserDTO user) {
		
		return userRepo.save(user);
	}
	//getUserById
	@Override
	public Optional<UserDTO> getUserById(long id) {
		return userRepo.findById(id) ;
	}


	//updateUser
	@Override
	public UserDTO updateUser(UserDTO user, long id) {
		UserDTO prev = userRepo.findById(id).orElseThrow(()-> new IdNotFoundException("Id not found"));
		
		Set<AccountDTO> accounts = prev.getAccount();
		prev.setAccount(user.getAccount());
			prev.setAadhar(user.getAadhar());
			prev.setAarchar(user.getPanimage());
			prev.setFullname(user.getFullname());
			prev.setPan(user.getPan());
			prev.setPanimage(user.getPanimage());
			prev.setPhone(user.getPhone());
			prev.setSecretAnswer(user.getSecretAnswer());
			prev.setSecretQuestion(user.getSecretQuestion());
			prev.setAccount(accounts);
			prev.setBeneficiaries(user.getBeneficiaries());
			
			userRepo.save(prev);
		return prev;

	}

	@Override
	public UserDTO getUser(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).orElseThrow();
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		// TODO Auto-generated method stub
		
		if(userRepo.existsById(user.getId())) {
		return	userRepo.save(user);
		}
		else {
            throw new RuntimeException("Sorry user With " + user.getId() + " not found");
        }


}
	@Override
	public Boolean userExistsById(Long userId) {
		// TODO Auto-generated method stub
		return userRepo.existsById(userId);
	}
//	@Override
//	public List<CustomerGetBeneficiaries> getCustomerBeneficiaries(Long userId) {
//		// TODO Auto-generated method stub
//		UserDTO user =userRepo.findById(userId).orElseThrow( () -> new IdNotFoundException("User with id: "+ userId +" not found")) ;
//		List<CustomerGetBeneficiaries> response = new ArrayList<CustomerGetBeneficiaries>();
//		Set<BeneficiaryDTO>beneficiaryDTOs = user.getBeneficiaries();
//		System.out.println("hiii");
//		
//		System.out.println(user.getFullname() + user.getBeneficiaries());
//	
//		CustomerGetBeneficiaries benSave = new CustomerGetBeneficiaries();
//		for(BeneficiaryDTO beneficiary : beneficiaryDTOs){
//			System.out.println(beneficiary.getName());
//			benSave.setActive(beneficiary.getActive());
//			benSave.setBeneficiaryAccountNo(beneficiary.getBeneficiaryId());
//			benSave.setBeneficiaryName(beneficiary.getName());
//			
//			response.add(benSave);
//		};
//		
//		return response;
//	}

@Override
public List<CustomerGetBeneficiaries> getCustomerBeneficiaries(Long userId) {
	// TODO Auto-generated method stub
	Optional<UserDTO> user1 =userRepo.findById(userId);
	System.out.println(user1.get());
	if(user1.isEmpty()) {
		return new ArrayList<CustomerGetBeneficiaries>(); 
	}
	UserDTO user = user1.get(); 
	
	
	
	List<CustomerGetBeneficiaries> response = new ArrayList<CustomerGetBeneficiaries>();
	Set<BeneficiaryDTO>beneficiaryDTOs = user.getBeneficiaries();
	
	
	System.out.println(user.getFullname() + user.getBeneficiaries());

	
	for(BeneficiaryDTO beneficiary : beneficiaryDTOs){
		CustomerGetBeneficiaries benSave = new CustomerGetBeneficiaries();
		System.out.println(beneficiary.getName());
		benSave.setActive(beneficiary.getActive());
		benSave.setBeneficiaryAccountNo(beneficiary.getBeneficiaryId());
		benSave.setBeneficiaryName(beneficiary.getName());
		benSave.setBeneficiaryId(beneficiary.getBeneficiaryId());
		
		response.add(benSave);
	};
	
	return response;
}


public void deleteUserById(Long employeeId) {
	
	userRepo.deleteUserDTOById(employeeId);
}
@Override
public UserDTO findByUsername(String username) {
	// TODO Auto-generated method stub
	if(userRepo.findByUsername(username).isEmpty()) {
		throw new IdNotFoundException(username + " doesn't exist");
	}
	return userRepo.findByUsername(username).get();
}
@Override
public Boolean existsByUsername(String username) {
	// TODO Auto-generated method stub
	return userRepo.existsByUsername(username);
}

}
