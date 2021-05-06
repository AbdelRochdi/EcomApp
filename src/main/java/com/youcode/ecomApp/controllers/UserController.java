package com.youcode.ecomApp.controllers;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.youcode.ecomApp.services.TokenService;
import com.youcode.ecomApp.services.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/users")
	public ResponseEntity<UserEntity> registerUser(@RequestBody @Valid UserEntity userEntity)
			throws MessagingException {

		UserEntity createdUser = userService.createUser(userEntity, "ROLE_CLIENT");

		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

	}

	@GetMapping("/users")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<UserEntity>> getAllUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "15") int limit) {

		List<UserEntity> users = userService.getUsers(page, limit);

		return new ResponseEntity<>(users, HttpStatus.OK);

	}

	@GetMapping("/users/{userId}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserEntity> getUser(@PathVariable String userId) {

		UserEntity userEntity = userService.getByUserId(userId);

		return new ResponseEntity<>(userEntity, HttpStatus.OK);
	}

	@PutMapping("/users/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserEntity> updateEcomUser(@PathVariable String userId, @RequestBody UserEntity userEntity) {

		UserEntity updatedUser = userService.updateUser(userId, userEntity);

		return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
	}

	@PutMapping("/user")
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public ResponseEntity<UserEntity> updateUser(@PathVariable String userId, @RequestBody UserEntity userEntity) {

		UserEntity updatedUser = userService.updateUser(userId, userEntity);

		return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/users/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> deleteEcomUser(@PathVariable String userId) {

		userService.deleteById(userId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/users/confirm")
	@PreAuthorize("permitAll()")
	public String confirm(@RequestParam("token") String token) {
		return tokenService.confirmToken(token);
	}

}
