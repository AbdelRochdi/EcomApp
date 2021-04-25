package com.youcode.ecomApp.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.youcode.ecomApp.entities.UserEntity;
import com.youcode.ecomApp.repositories.UserRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity disableUser(String id) {
		UserEntity updatedUser = userRepository.findByUserId(id);

		if (updatedUser == null)
			throw new UsernameNotFoundException(id);
		
		updatedUser.setEmailVerificationStatus(false);

		UserEntity userUpdated = userRepository.save(updatedUser);

		return userUpdated;
	}

}
