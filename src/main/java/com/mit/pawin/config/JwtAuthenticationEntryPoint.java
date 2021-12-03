package com.mit.pawin.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mit.pawin.controller.AuthenticationController;
import com.mit.pawin.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, ErrorController {

	@Autowired
	private ResponseDto responseDto;

	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()");

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		Exception exception = (Exception) request.getAttribute("exception");

		String message;

		if (exception != null) {

			LinkedHashMap res = new LinkedHashMap();
			res.put("response", responseDto.getTokenExpir());
			res.put("message", "Token Expired");

			//byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("cause1", exception.toString()));
			byte[] body = new ObjectMapper().writeValueAsBytes(res);

			response.getOutputStream().write(body);

		} else {

			if (authException.getCause() != null) {
				message = authException.getCause().toString() + " " + authException.getMessage();
			} else {
				message = authException.getMessage();
			}

			byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));

			response.getOutputStream().write(body);
		}
	}

	public String getErrorPath() {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()");

		return null;
	}
}
