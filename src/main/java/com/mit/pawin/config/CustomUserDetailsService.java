package com.mit.pawin.config;

import com.mit.pawin.controller.AuthenticationController;
import com.mit.pawin.entity.SystemUser;
import com.mit.pawin.model.UserDTO;
import com.mit.pawin.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private CommonService commonService;

	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ username={}}", username);

		List<SimpleGrantedAuthority> roles = null;
		SystemUser user = (SystemUser)commonService.getObjectByColumnName("SystemUser", "username", username);
		if (user != null) {
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getUserRoleCode()));
			return new User(user.getUsername(), user.getPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + username);
	}
	
	public SystemUser save(UserDTO user) {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ username={}, password={}}", user.getUsername(), user.getPassword());

		SystemUser newUser = new SystemUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		//newUser.setRole(user.getRole());
		newUser.setCreatedBy("buddhika");
		newUser.setSysUsrId(1);
		newUser.setSysUsrCode("SYSUSR-1");
		newUser.setUserRoleCode("USROLE-1");
		//return userDao.save(newUser);
		return null;
	}

}
