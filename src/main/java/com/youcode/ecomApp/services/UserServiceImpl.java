package com.youcode.ecomApp.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.youcode.ecomApp.entities.UserEntity;
import com.youcode.ecomApp.repositories.UserRepository;
import com.youcode.ecomApp.shared.Utils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Utils utils;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserEntity createUser(UserEntity userEntity) {

		UserEntity checkUser = userRepository.findByEmail(userEntity.getEmail().toLowerCase());

		if (checkUser != null) {
			throw new RuntimeException("user already exists");
		}

		userEntity.setEmail(userEntity.getEmail().toLowerCase());
		
		userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));

		userEntity.setUserId(utils.generateUserId(32));

		UserEntity newUserEntity = userRepository.save(userEntity);

		return newUserEntity;
	}

	@Override
	public void deleteById(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException(userId);

		userRepository.delete(userEntity);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email.toLowerCase()); 

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail().toLowerCase(), userEntity.getPassword(), new ArrayList<>());
	}

	@Override
	public UserEntity getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return userEntity;

	}

	@Override
	public UserEntity getByUserId(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException(userId);

		return userEntity;

	}

	@Override
	public UserEntity updateUser(String id, UserEntity userEntity) {

		UserEntity updatedUser = userRepository.findByUserId(id);

		if (userEntity == null)
			throw new UsernameNotFoundException(id);

		updatedUser.setFirstName(userEntity.getFirstName());
		updatedUser.setLastName(userEntity.getLastName());
		updatedUser.setPhone(userEntity.getPhone());

		UserEntity userUpdated = userRepository.save(updatedUser);

		return userUpdated;
	}

	@Override
	public List<UserEntity> getUsers(int page, int limit) {

		if (page > 0)
			page -= 1;

		Pageable pageable = PageRequest.of(page, limit);

		Page<UserEntity> userPage = userRepository.findAll(pageable);

		List<UserEntity> users = userPage.getContent();

		return users;
	}

}
