package com.bookmanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookmanagement.dao.UserInfoDao;
import com.bookmanagement.entity.UserInfo;
import com.bookmanagement.entity.UserInfoDto;

/**
 * This is UserInfoService class
 * 
 * @author Aung Ba
 *
 */
@Service
public class UserInfoService implements UserDetailsService {

	/**
	 * declare userInfoDao
	 */
	@Autowired
	private UserInfoDao userInfoDao;

	/**
	 * declare passwordEncoder
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * This method is used to retrieve userInfo by username from db
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = userInfoDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username : " + username);
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRole_name()));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}

	/**
	 * This method is used to register user into db
	 * 
	 * @param user user
	 * @return 0 or 1
	 */
	public int registerUser(UserInfo user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userInfoDao.registerUser(user);
	}

	public List<UserInfoDto> getUserList() {
		List<UserInfoDto> userInfoDtoList = new ArrayList<UserInfoDto>();
		List<UserInfo> userInfoList = this.userInfoDao.getUserList();
		userInfoList.forEach((userInfo) -> {
			userInfoDtoList.add(new UserInfoDto(userInfo.getId(), userInfo.getUsername(), userInfo.getRole().getRole_name(), 
					userInfo.getAddress().getAddress() + userInfo.getAddress().getCity() + userInfo.getAddress().getCountry(), 
					userInfo.isGender(), userInfo.getEmail()));
		});
		return userInfoDtoList;
	}

	public Map<String, String> getCurrentAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		userDetails.getAuthorities().toString();
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("username", userDetails.getUsername());
		userMap.put("role", userDetails.getAuthorities().toString());
		return userMap;
	}

}
