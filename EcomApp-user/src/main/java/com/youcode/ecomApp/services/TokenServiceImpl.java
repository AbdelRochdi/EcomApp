package com.youcode.ecomApp.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youcode.ecomApp.entities.ConfirmationToken;
import com.youcode.ecomApp.entities.UserEntity;
import com.youcode.ecomApp.repositories.ConfirmationTokenRepository;
import com.youcode.ecomApp.repositories.UserRepository;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private UserRepository userRepository;

	public String confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);

		if (confirmationToken == null) {
			throw new IllegalStateException("token not found");
		}

		if (confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("email already confirmed");
		}

		LocalDateTime expiredAt = confirmationToken.getExpiresAt();

		if (expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("token expired");
		}

		setConfirmedAt(token);

		UserEntity tokenUser = confirmationToken.getUserEntity();

		tokenUser.setEmailVerificationStatus(true);

		userRepository.save(tokenUser);

		return "confirmed";
	}

	@Override
	public void createToken(ConfirmationToken confirmationToken) {

		confirmationTokenRepository.save(confirmationToken);
	}

	public int setConfirmedAt(String token) {
		return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
	}
}
