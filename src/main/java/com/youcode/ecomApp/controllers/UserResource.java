package com.youcode.ecomApp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youcode.ecomApp.entities.UserEntity;
import com.youcode.ecomApp.services.UserService;

@RestController
@RequestMapping("/api")
public class UserResource {

	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public ResponseEntity<UserEntity> registerUser(@RequestBody @Valid UserEntity userEntity) {

		userService.createUser(userEntity);
		
		return new ResponseEntity<>(userEntity,HttpStatus.CREATED);

	}

	@GetMapping("/users")
	public  ResponseEntity<List<UserEntity>> getAllUsers(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "15") int limit ) {
				
		List<UserEntity> users = userService.getUsers(page, limit);

		return new ResponseEntity<>(users,HttpStatus.OK);

	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserEntity> getUser(@PathVariable String userId) {
		 
		UserEntity userEntity = userService.getByUserId(userId);
		
		return new ResponseEntity<>(userEntity,HttpStatus.OK);
	}

	@PutMapping("/users/{userId}") 
	public ResponseEntity<UserEntity> updateEcomUser(@PathVariable String userId, @RequestBody UserEntity userEntity) {
		
		UserEntity updatedUser = userService.updateUser(userId, userEntity);
		
		return new ResponseEntity<>(updatedUser,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<Object> deleteEcomUser(@PathVariable String userId) {
		
		userService.deleteById(userId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
