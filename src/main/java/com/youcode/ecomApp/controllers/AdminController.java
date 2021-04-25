package com.youcode.ecomApp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youcode.ecomApp.entities.UserEntity;
import com.youcode.ecomApp.services.AdminService;
import com.youcode.ecomApp.services.UserService;

@RestController
@RequestMapping("/api")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/admin")
	public ResponseEntity<UserEntity> registerAdmin(@RequestBody @Valid UserEntity userEntity) {

		userService.createUser(userEntity, "admin");
		
		return new ResponseEntity<>(userEntity,HttpStatus.CREATED);

	}
	
	@PutMapping("/admin/{userId}")
	public ResponseEntity<UserEntity> disableUser(@PathVariable String userId) {

		UserEntity userEntity =  adminService.disableUser(userId);
		
		return new ResponseEntity<>(userEntity,HttpStatus.ACCEPTED);

	}
	
	
	
}
