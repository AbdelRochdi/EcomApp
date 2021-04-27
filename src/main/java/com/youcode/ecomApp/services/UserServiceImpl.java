package com.youcode.ecomApp.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.youcode.ecomApp.entities.ConfirmationToken;
import com.youcode.ecomApp.entities.UserEntity;
import com.youcode.ecomApp.entities.UserRole;
import com.youcode.ecomApp.repositories.RoleRepository;
import com.youcode.ecomApp.repositories.UserRepository;
import com.youcode.ecomApp.shared.Utils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private TokenService tokenService;

	@Autowired
	private Utils utils;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserEntity createUser(UserEntity userEntity, String title) throws MessagingException {

		UserEntity checkUser = userRepository.findByEmail(userEntity.getEmail().toLowerCase());
		
		UserRole userRole = roleRepository.findByTitle(title);

		if (checkUser != null) {
			throw new IllegalStateException("user already exists");
		}

		userEntity.setEmail(userEntity.getEmail().toLowerCase());
		
		userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));

		userEntity.setUserId(utils.generateUserId(32));	
		
		userRepository.save(userEntity);
		
		userRole.addUser(userEntity);
		
		roleRepository.save(userRole);
		
		String token = UUID.randomUUID().toString(); 
		
		ConfirmationToken confirmationToken = new ConfirmationToken();
		
		confirmationToken.setToken(token);
		confirmationToken.setCreatedAt(LocalDateTime.now());
		confirmationToken.setExpiresAt(LocalDateTime.now().plusDays(7));
		confirmationToken.setUserEntity(userEntity);
		
		tokenService.createToken(confirmationToken);
		
		String link = "http://localhost:8080/api/users/confirm?token=" + token;
		
		sendEmail(userEntity, buildEmail(userEntity.getFirstName(), link));
	
		return userEntity;
	}

	@Override
	public void deleteById(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException(userId);

		userRepository.delete(userEntity);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email.toLowerCase()); 

		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		
		if (!userEntity.isEmailVerificationStatus())
			throw new IllegalStateException("account disabled");
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userEntity.getUserRole().getTitle()));

		return new User(userEntity.getEmail().toLowerCase(), userEntity.getPassword(), authorities);
	}

	@Override
	public UserEntity getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return userEntity;

	}

	@Override
	public UserEntity getByUserId(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException(userId);

		return userEntity;

	}

	@Override
	public UserEntity updateUser(String id, UserEntity userEntity) {

		UserEntity updatedUser = userRepository.findByUserId(id);

		if (updatedUser == null)
			throw new UsernameNotFoundException(id);

		updatedUser.setFirstName(userEntity.getFirstName());
		updatedUser.setLastName(userEntity.getLastName());
		updatedUser.setPhone(userEntity.getPhone());

		UserEntity userUpdated = userRepository.save(updatedUser);

		return userUpdated;
	}

	@Override
	public List<UserEntity> getUsers(int page, int limit) {

		if (page > 0)
			page -= 1;

		Pageable pageable = PageRequest.of(page, limit);

		Page<UserEntity> userPage = userRepository.findAll(pageable);

		List<UserEntity> users = userPage.getContent();

		return users;
	}
	
	public void sendEmail(UserEntity user, String message) throws MessagingException {

        String text = message;

        String host = "smtp.gmail.com";
        String to = user.getEmail();
        String username = "youcode.absence@gmail.com";
        String password = "youcode2021";

        Properties prpos = System.getProperties();
        prpos.put("mail.smtp.auth", true);
        prpos.put("mail.smtp.starttls.enable", true);
        prpos.put("mail.smtp.host", host);
        prpos.put("mail.smtp.port", "587");

        javax.mail.Session session = javax.mail.Session.getDefaultInstance(prpos, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage m = new MimeMessage(session);
        m.setFrom(new InternetAddress(username));
        m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress((to)));
        m.setSubject("Basma Online Store");
        m.setContent(text, "text/html");

        Transport.send(m);
        System.out.println("Message sent");
    }
	
	public String buildEmail(String name, String link) {
		return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" + "\n"
				+ "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" + "\n"
				+ "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
				+ "    <tbody><tr>\n" + "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" + "        \n"
				+ "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n"
				+ "          <tbody><tr>\n" + "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n"
				+ "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                  <tbody><tr>\n" + "                    <td style=\"padding-left:10px\">\n"
				+ "                  \n" + "                    </td>\n"
				+ "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n"
				+ "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n"
				+ "                    </td>\n" + "                  </tr>\n" + "                </tbody></table>\n"
				+ "              </a>\n" + "            </td>\n" + "          </tr>\n" + "        </tbody></table>\n"
				+ "        \n" + "      </td>\n" + "    </tr>\n" + "  </tbody></table>\n"
				+ "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
				+ "    <tbody><tr>\n" + "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n"
				+ "      <td>\n" + "        \n"
				+ "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                  <tbody><tr>\n"
				+ "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n"
				+ "                  </tr>\n" + "                </tbody></table>\n" + "        \n" + "      </td>\n"
				+ "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" + "    </tr>\n"
				+ "  </tbody></table>\n" + "\n" + "\n" + "\n"
				+ "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
				+ "    <tbody><tr>\n" + "      <td height=\"30\"><br></td>\n" + "    </tr>\n" + "    <tr>\n"
				+ "      <td width=\"10\" valign=\"middle\"><br></td>\n"
				+ "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n"
				+ "        \n"
				+ "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name
				+ ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\""
				+ link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>"
				+ "        \n" + "      </td>\n" + "      <td width=\"10\" valign=\"middle\"><br></td>\n"
				+ "    </tr>\n" + "    <tr>\n" + "      <td height=\"30\"><br></td>\n" + "    </tr>\n"
				+ "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" + "\n" + "</div></div>";
	}
	


	

}
