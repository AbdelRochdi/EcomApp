package com.EcomPanier.demo.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class UserRole implements Serializable{

	private static final long serialVersionUID = -5897830821783534600L;
	

	private Long id;
	private String title;

	private List<UserEntity> userEntities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<UserEntity> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(List<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}
	
	public void addUser(UserEntity userEntity) {
		if ( userEntities == null) {
			userEntities = new ArrayList<UserEntity>();
		}
		
		userEntities.add(userEntity);
		
		userEntity.setUserRole(this);
	}
		
}
