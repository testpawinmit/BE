package com.mit.pawin.config;

import com.mit.pawin.controller.AuthenticationController;
import com.mit.pawin.dto.ValuePassingDto;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Data
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtTokenUtil;

    private String loginUsername;
    private String newJwtToken;
    private String tokenUsername;

    @Autowired
    private ValuePassingDto valuePassingDto;

    private static final Logger log = LoggerFactory.getLogger(CustomJwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        try {

            // JWT Token is in the form "Bearer token". Remove Bearer word and
            // get  only the Token
            String jwtToken = extractJwtFromRequest(request);

            log.info("jwtToken={}", jwtToken);

            newJwtToken = jwtToken;

            valuePassingDto.setClientIp(extractMachineIpFromRequest(request));

            if (StringUtils.hasText(jwtToken) && jwtTokenUtil.validateToken(jwtToken)) {
                UserDetails userDetails = new User(jwtTokenUtil.getUsernameFromToken(jwtToken), "",
                        jwtTokenUtil.getRolesFromToken(jwtToken));

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                loginUsername = userDetails.getUsername();
                tokenUsername = jwtTokenUtil.getUsernameFromToken(jwtToken);

                valuePassingDto.setUsername(loginUsername);

                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {

                log.info("Cannot set the Security Context");

            }
        } catch (ExpiredJwtException ex) {

            log.info("Exception {}", ex.getMessage());

            String isRefreshToken = request.getHeader("isRefreshToken");
            String requestURL = request.getRequestURL().toString();
            // allow for Refresh Token creation if following conditions are true.
            if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken")) {
                allowForRefreshToken(ex, request);
            } else
                request.setAttribute("exception", ex);
        } catch (BadCredentialsException ex) {

            log.info("Exception {}", ex.getMessage());

            request.setAttribute("exception", ex);
        }
        chain.doFilter(request, response);
    }

    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        // create a UsernamePasswordAuthenticationToken with null values.
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                null, null, null);
        // After setting the Authentication in the context, we specify
        // that the current user is authenticated. So it passes the
        // Spring Security Configurations successfully.
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        // Set the claims so that in controller we will be using it to create
        // new JWT
        request.setAttribute("claims", ex.getClaims());

    }

    private String extractJwtFromRequest(HttpServletRequest request) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        String bearerToken = request.getHeader("Authorization");

        log.info("bearerToken={}" + bearerToken);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    private String extractMachineIpFromRequest(HttpServletRequest request) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        String machineIp = request.getHeader("Client-Ip");
        return machineIp;
    }

}