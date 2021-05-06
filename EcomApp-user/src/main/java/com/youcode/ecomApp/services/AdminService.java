package com.youcode.ecomApp.services;

import com.youcode.ecomApp.entities.UserEntity;

public interface AdminService {
	
	UserEntity disableUser(String id);

}
