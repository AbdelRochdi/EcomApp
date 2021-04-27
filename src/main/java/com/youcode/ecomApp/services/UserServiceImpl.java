package com.youcode.ecomApp.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.youcode.ecomApp.entities.UserEntity;
import com.youcode.ecomApp.entities.UserRole;
import com.youcode.ecomApp.repositories.RoleRepository;
import com.youcode.ecomApp.repositories.UserRepository;
import com.youcode.ecomApp.shared.Utils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private Utils utils;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserEntity createUser(UserEntity userEntity, String title) {

		UserEntity checkUser = userRepository.findByEmail(userEntity.getEmail().toLowerCase());
		
		UserRole userRole = roleRepository.findByTitle(title);

		if (checkUser != null) {
			throw new IllegalStateException("user already exists");
		}

		userEntity.setEmail(userEntity.getEmail().toLowerCase());
		
		userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));

		userEntity.setUserId(utils.generateUserId(32));	
		
		userRole.addUser(userEntity);
		
		roleRepository.save(userRole);
		
		return userEntity;
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
		
		if (!userEntity.isEmailVerificationStatus())
			throw new IllegalStateException("account disabled");
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userEntity.getUserRole().getTitle()));

		return new User(userEntity.getEmail().toLowerCase(), userEntity.getPassword(), authorities);
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

		if (updatedUser == null)
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
