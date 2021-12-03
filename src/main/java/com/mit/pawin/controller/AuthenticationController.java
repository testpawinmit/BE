package com.mit.pawin.controller;

import com.mit.pawin.config.CustomUserDetailsService;
import com.mit.pawin.config.JwtUtil;
import com.mit.pawin.dto.ResponseDto;
import com.mit.pawin.dto.ValuePassingDto;
import com.mit.pawin.entity.*;
import com.mit.pawin.model.AuthenticationRequest;
import com.mit.pawin.model.UserDTO;
import com.mit.pawin.service.CommonService;
import com.mit.pawin.util.CommonUtil;
import com.mit.pawin.util.TransactionLog;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@RestController
@CrossOrigin
@Data
public class AuthenticationController extends CommonUtil {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CommonService commonService;

	@Autowired
	private ResponseDto responseDto;

	@Value("${jwt.secret}")
	private String signingKey;

	@Value("${mit.pawin.web.token}")
	private String webTokenPath;

	@Value("${mit.pawin.web.refresh.token}")
	private String refreshTokenPath;

	@Value("${mit.pawin.web.access.time}")
	private String accessTimePath;

	@Value("${mit.pawin.base.url}")
	private String baseUrl;

	@Value("${mit.pawin.profile.img}")
	private String profileImgDir;

	SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Value("${mit.pawin.email.password.listener.folder}")
	private String sysUsrCodeFolder;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private TransactionLog transactionLog;

	@Autowired
	private ValuePassingDto valuePassingDto;

	private String username;
	private String clientIp;

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	private String methodAction;

	@Value("${mit.pawin.user.acnt.lock.count}")
	private String acntLockCount;

	@RequestMapping(value = "/hris/web/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid AuthenticationRequest authenticationRequest, HttpServletRequest request)
			throws Exception {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()" + " {username={}, appCode={}, machine={}}", authenticationRequest.getUsername(), authenticationRequest.getAppTypeCode(), authenticationRequest.getMachine());

		LinkedHashMap<Object, Object> res = new LinkedHashMap<>();
		SystemUser systemUser = null;
		username = authenticationRequest.getUsername();

		valuePassingDto.setUsername(username);

		clientIp = extractMachineIpFromRequest(request);

		valuePassingDto.setClientIp(clientIp);

		try {
			try {

				Object appTypeObject = commonService.getObjectByColumnName("AppType", "appTypeCode", authenticationRequest.getAppTypeCode(), "User Login");

				AppType appType = (AppType) appTypeObject;
				String decryptPassword = null;

				if(decryptString(authenticationRequest.getPassword()).get("response").equals("Ok")){
					decryptPassword = String.valueOf(decryptString(authenticationRequest.getPassword()).get("value"));
				}else {
					decryptPassword = null;
				}

				if (!appType.getAppTypeName().equals("WEB") && !appType.getAppTypeName().equals("ALL")) {
					authenticationRequest.setUsername("");
				}

				Object sysUsrObject = commonService.getObjectByColumnName("SystemUser", "username", authenticationRequest.getUsername(), "User Login");
				systemUser = (SystemUser) sysUsrObject;

				if(null == systemUser){
					res.put("response", responseDto.getFail());
					res.put("message", "Username is Invalid.");
					return ResponseEntity.ok(res);
				}

				if(!systemUser.getIsActive()){
					res.put("response", responseDto.getFail());
					res.put("message", "User is Inactive.");
					return ResponseEntity.ok(res);
				}

				if (systemUser.getPassExpir().before(new Date())) {
					authenticationRequest.setPassword(null);
				}

				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsername(), decryptPassword));

			} catch (DisabledException e) {

				log.info("Exception {}", e.getMessage());

				throw new Exception("USER_DISABLED", e);

			} catch (BadCredentialsException e) {

				log.info("Exception {}", e.getMessage());

				if(null != systemUser){
					//2. copy history object
					SystemUserH systemUserH = new SystemUserH();
					BeanUtils.copyProperties(systemUser, systemUserH);
					//3. get id
					long id = commonService.generateId("com.mit.pawin.entity.SystemUserH", "SystemUserH", "sysUsrHId", "User Login");
					systemUserH.setSysUsrHId(id);
					systemUserH.setCreatedBy(systemUser.getUsername());
					//4. save in history table
					Object objectSysUsrH = commonService.addObject(systemUserH, "User Login");

					int loggingCount = systemUser.getUserLoggingCount();
					systemUser.setUserLoggingCount(++loggingCount);
					Object objectSysUsr = commonService.updateObject(systemUser, "User Login");

					if(systemUser.getUserLoggingCount() >= Integer.parseInt(acntLockCount)){

						//2. copy history object
						systemUserH = new SystemUserH();
						BeanUtils.copyProperties(systemUser, systemUserH);
						//3. get id
						id = commonService.generateId("com.mit.pawin.entity.SystemUserH", "SystemUserH", "sysUsrHId", "User Login");
						systemUserH.setSysUsrHId(id);
						systemUserH.setCreatedBy(systemUser.getUsername());
						//4. save in history table
						objectSysUsrH = commonService.addObject(systemUserH, "User Login");

						systemUser.setIsActive(false);
						systemUser.setUpdatedAt(new Date());
						systemUser.setUpdatedBy(systemUser.getUsername());
						objectSysUsr = commonService.updateObject(systemUser, "User Login");

						//write mail
						writeFileWithAppend(sysUsrCodeFolder + simpleDateFormat.format(new Date()) + ".txt", CredentialEmailStatus.USERNAME_STOLE.ordinal() +
								"," + systemUser.getSysUsrCode());
					}
				}

				throw new Exception("INVALID_CREDENTIALS", e);
			}

			UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			String token = jwtUtil.generateToken(userdetails);

			LinkedHashMap<Object, Object> output = new LinkedHashMap<>();

			output.put("token", token);

			final Claims myClaims = Jwts.parser().setSigningKey(signingKey)
					.parseClaimsJws(token).getBody();

			request.setAttribute("claims", myClaims);

			// From the HttpRequest get the claims
			DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");

			Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
			String refreshToken = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());

			Object systemUserObject = commonService.getObjectByColumnName("SystemUser", "username", authenticationRequest.getUsername(), "User Login");
			systemUser = (SystemUser) systemUserObject;

			Object userRoleObject = commonService.getObjectByColumnName("UserRole", "userRoleCode", systemUser.getUserRoleCode(), "User Login");
			UserRole userRole = (UserRole) userRoleObject;

			ArrayList<String> colList = new ArrayList<String>();
			ArrayList<Object> valueList = new ArrayList<Object>();

			colList.add("userRoleCode");
			colList.add("isAdded");

			valueList.add(userRole.getUserRoleCode());
			valueList.add(true);

			//List<Object> usrPrivObjectList = commonService.getObjectListByColumnName("UserWithPrivilege", "userRoleCode", userRole.getUserRoleCode());
			List<Object> usrPrivObjectList = commonService.searchRecords("UserWithPrivilege", colList, valueList, "User Login");

			List privList = new ArrayList();

			for (Object object : usrPrivObjectList) {
				UserWithPrivilege userWithPrivilege = (UserWithPrivilege) object;
				Object usrPrivObject = commonService.getObjectByColumnName("UserPrivilege", "userPrivilegeCode", userWithPrivilege.getUserPrivilegeCode(), "User Login");
				UserPrivilege userPrivilege = (UserPrivilege) usrPrivObject;
				LinkedHashMap linkedHashMap = new LinkedHashMap();
				linkedHashMap.put("userPrivilegeCode", userPrivilege.getUserPrivilegeCode());
				linkedHashMap.put("userPrivName", userPrivilege.getUserPrivName());
				//linkedHashMap.put("hierarchyStatus", userPrivilege.getHierarchyStatus());
				linkedHashMap.put("isActive", userPrivilege.getIsActive());
				privList.add(linkedHashMap);
			}

			/*_________________________________________________________transaction log_________________________________________________________________*/
			transactionLog.write(username + " | " + "User Login"  + " | " + "getByEmpCode" + "( " + systemUser.getEmpCode() + " )" +
					" | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
			/*__________________________________________________________________________________________________________________________________________*/

			//Employee employee = employeeRepository.getByEmpCode(systemUser.getEmpCode());
			Customer customer = null;
			if(systemUser.getUserRoleCode().toUpperCase().equals("USROLE-3")){
				customer = (Customer) commonService.getObjectByColumnName("Customer", "custCode", systemUser.getEmpCode(), "User Authentication");
			}
			//Employee employee = (Employee) commonService.getObjectByColumnName("Employee", "empCode", systemUser.getEmpCode(), "User Authentication");

			/*_________________________________________________________transaction log_________________________________________________________________*/
			transactionLog.write(username + " | " + "User Login" + " | " + "return data => " + customer + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
			/*__________________________________________________________________________________________________________________________________________*/

			res.put("response", responseDto.getOk());
			res.put("message", "Successfully logged");
			res.put("empCode", systemUser.getEmpCode());
			res.put("sysUsrCode", systemUser.getSysUsrCode());
			res.put("username", systemUser.getUsername());
			res.put("token", token);
			res.put("refreshToken", refreshToken);
			res.put("role", userRole.getUserRoleName());
			res.put("userRole", systemUser.getUserRoleCode());
			//res.put("profImg", baseUrl + profileImgDir + customer.getProfileImgName());
			res.put("privileges", privList);

			//create file
			String tokenFile = webTokenPath + authenticationRequest.getUsername() + ".txt";
			createFileUsingPath(tokenFile);
			writeMyStringToFile(token, tokenFile);

			String refreshTokenFile = refreshTokenPath + authenticationRequest.getUsername() + ".txt";
			createFileUsingPath(refreshTokenFile);
			writeMyStringToFile(refreshToken, refreshTokenFile);

			String accessTimeFile = accessTimePath + authenticationRequest.getUsername() + ".txt";
			createFileUsingPath(accessTimeFile);
			writeMyStringToFile(simpleDateFormatWithTime.format(new Date()), accessTimeFile);

			//return ResponseEntity.ok(new AuthenticationResponse(token));
			return ResponseEntity.ok(res);
		}catch (Exception e){

			log.info("Exception {}", e.getMessage());

			res.put("response", responseDto.getError());
			res.put("message", "Error Occurred");
			return ResponseEntity.ok(res);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody @Valid UserDTO user) throws Exception {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()" + " {username={}, password={}}", user.getUsername(), user.getPassword());

		return ResponseEntity.ok(userDetailsService.save(user));
	}

	@RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
	public String refreshtoken(HttpServletRequest request) throws Exception {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()");

		final String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			throw new ServletException("Missing or invalid Authorization header.");
		}

		final String token = authHeader.substring(7);

		final Claims myClaims = Jwts.parser().setSigningKey(signingKey)
				.parseClaimsJws(token).getBody();

		request.setAttribute("claims", myClaims);

		// From the HttpRequest get the claims
		DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");

		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String refreshToken = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		//return ResponseEntity.ok(new AuthenticationResponse(refreshToken));
		return refreshToken;
	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()");

		Map<String, Object> expectedMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}

	private String extractMachineIpFromRequest(HttpServletRequest request) {

		String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
		log.info("Calling  " + methodNameUsingClassInstance + "()");

		String machineIp = request.getHeader("Client-Ip");
		return machineIp;
	}

}
