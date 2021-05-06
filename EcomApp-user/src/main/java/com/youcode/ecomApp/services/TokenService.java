package com.youcode.ecomApp.services;

import com.youcode.ecomApp.entities.ConfirmationToken;

public interface TokenService {

	String confirmToken(String token);
	
	void createToken(ConfirmationToken confirmationToken);
	
	int setConfirmedAt(String token);
	
}
