package com.EcomPanier.demo.beans;

import javax.persistence.*;

//@Entity
//@Table(name="cart")
public class Cart {
	
	
	private long cartId;
	private UserEntity userId;
	private Products productId;
	private int quantity;
	private double price;
	
	
	public Cart() {
	}


	public Cart(long cartId, UserEntity userId, Products productId, int quantity, double price) {
		this.cartId = cartId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}
	

	public Cart(UserEntity userId, Products productId, int quantity, double price) {

		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}


	public long getCartId() {
		return cartId;
	}


	public void setCartId(long cartId) {
		this.cartId = cartId;
	}


	public UserEntity getUserId() {
		return userId;
	}


	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}


	public Products getProductId() {
		return productId;
	}


	public void setProductId(Products productId) {
		this.productId = productId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
	
	
}
