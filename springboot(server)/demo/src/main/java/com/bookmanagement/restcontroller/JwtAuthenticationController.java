package com.bookmanagement.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.config.JwtTokenUtil;
import com.bookmanagement.entity.UserInfo;
import com.bookmanagement.entity.UserInfoDto;
import com.bookmanagement.service.UserInfoService;

/**
 * This is JwtAuthenticationController class
 * 
 * @author Aung Ba
 *
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {

	/**
	 * declare authenticationManager
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * declare jwtTokenUtil
	 */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * declare userInfoService
	 */
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * This method is used to authenticate and return token if valid
	 * 
	 * @param userInfo userInfo
	 * @return token
	 */
	@PostMapping("/authenticate")
	public Map<String, String> createToken(@RequestBody UserInfo userInfo) {
		System.out.println(userInfo.getUsername());
		authenticate(userInfo.getUsername(), userInfo.getPassword());
		final UserDetails userDetails = userInfoService.loadUserByUsername(userInfo.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		//return ResponseEntity.ok(token);
//		return new ResponseEntity<>(token, HttpStatus.OK);
		Map<String, String> response = new HashMap<String, String>();
		response.put("token", token);
		return response;
	}

	/**
	 * This method is used to authenticate with username and password
	 * 
	 * @param username username
	 * @param password password
	 */
	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}

	/**
	 * This method is used to register the user into db
	 * 
	 * @param user user
	 * @return ResponseEntity
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserInfo user) {
		int result = userInfoService.registerUser(user);
		System.out.println(result);
		return ResponseEntity.ok("OK");
	}
	
	@GetMapping("/users")
	public List<UserInfoDto> getUserList() {
		return this.userInfoService.getUserList();
	}
	
	@GetMapping("/authenticated")
	public Map<String, String> getCurrentAuthenticatedUser() {
		return this.userInfoService.getCurrentAuthenticatedUser();
	}

}
