package com.mit.pawin.config;

import com.mit.pawin.controller.AuthenticationController;
import com.mit.pawin.util.CommonUtil;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtUtil extends CommonUtil {

	private String secret;
	private int jwtExpirationInMs;
	private int refreshExpirationDateInMs;

	@Value("${jwt.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Value("${jwt.expirationDateInMs}")
	public void setJwtExpirationInMs(int jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}
	
	@Value("${jwt.refreshExpirationDateInMs}")
	public void setRefreshExpirationDateInMs(int refreshExpirationDateInMs) {
		this.refreshExpirationDateInMs = refreshExpirationDateInMs;
	}

	static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

	public String generateToken(UserDetails userDetails) {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ username={}, password={}}", userDetails.getUsername(), userDetails.getPassword());

		Map<String, Object> claims = new HashMap<>();

		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

		/*if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			claims.put("isAdmin", true);
		}
		if (roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			claims.put("isUser", true);
		}*/

		claims.put("isAdmin", true);

		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ subject={}}", subject);

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

	}
	
	public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ subject={}}", subject);

		Calendar date = Calendar.getInstance();
		long t= date.getTimeInMillis();
		Date afterAddingTenMins=new Date(t + (10 * ONE_MINUTE_IN_MILLIS));

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(afterAddingTenMins)
				.setExpiration(new Date(System.currentTimeMillis() + refreshExpirationDateInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

	}

	public boolean validateToken(String authToken) {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ authToken={}}", authToken);

		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {

			log.info("Exception {}", ex.getMessage());

			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {

			log.info("Exception {}", ex.getMessage());

			throw ex;
		}
	}

	public String getUsernameFromToken(String token) {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ token={}}", token);

		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject();

	}

	public List<SimpleGrantedAuthority> getRolesFromToken(String token) {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ token={}}", token);

		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		List<SimpleGrantedAuthority> roles = null;

		Boolean isAdmin = claims.get("isAdmin", Boolean.class);
		Boolean isUser = claims.get("isUser", Boolean.class);

		if (isAdmin != null && isAdmin) {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		if (isUser != null && isAdmin) {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return roles;

	}

}
