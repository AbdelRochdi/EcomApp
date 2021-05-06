package com.youcode.ecomApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youcode.ecomApp.entities.UserRole;

public interface RoleRepository extends JpaRepository<UserRole, Long>{

	UserRole findByTitle(String title);
	
}
