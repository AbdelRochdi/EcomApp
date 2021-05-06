package com.youcode.ecomApp.services;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.youcode.ecomApp.entities.UserEntity;


public interface UserService extends UserDetailsService {
	
	UserEntity createUser(UserEntity userEntity, String title) throws MessagingException;
	
	UserEntity updateUser(String id, UserEntity userEntity);
	
	UserEntity getUser(String email);

	List<UserEntity> getUsers(int page, int limit);
	
	UserEntity getByUserId(String userId);
	
	void deleteById(String userId);

	void sendEmail(UserEntity user, String message) throws MessagingException;
}
