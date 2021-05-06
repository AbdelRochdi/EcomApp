package com.youcode.ecomApp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "roles")
public class UserRole implements Serializable{

	private static final long serialVersionUID = -5897830821783534600L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "title",unique = true)
	private String title;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRole",cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JsonIgnoreProperties("userRole")
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
