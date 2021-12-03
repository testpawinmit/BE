package com.mit.pawin.config;

import com.mit.pawin.controller.AuthenticationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private static final Logger log = LoggerFactory.getLogger(SpringSecurityConfiguration.class);

	@Bean
	public PasswordEncoder passwordEncoder(){

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()");

		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()");

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()");

		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()");

		http.csrf().disable()
		.authorizeRequests().antMatchers("/helloadmin").hasRole("ADMIN")
		.antMatchers("/hellouser").hasAnyRole("USER","ADMIN")
		.antMatchers("/hris/web/authenticate", "/hris/web/register", "/hris/web/handlingForgotPassword", "/hris/web/uploadingFile",
				"/swagger-ui.html","/webjars/**", "/configuration/**", "/images/**", "/swagger-resources", "/v2/**").permitAll().anyRequest().authenticated()
		.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).
		and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and().addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
