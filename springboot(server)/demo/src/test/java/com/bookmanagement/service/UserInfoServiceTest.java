package com.bookmanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bookmanagement.dao.UserInfoDao;
import com.bookmanagement.entity.UserAddress;
import com.bookmanagement.entity.UserInfo;
import com.bookmanagement.entity.UserRole;

@RunWith(MockitoJUnitRunner.class)
public class UserInfoServiceTest {
	
	@Mock
	private UserInfoDao userInfoDao;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private UserInfoService userInfoService;
	
	@Test
	public void TestLoadByUsername_normal() {
		UserAddress userAddress = new UserAddress(1, "No. 33 testing", "YGN", "Myanmar", "11101");
		UserRole userRole = new UserRole(1, "ADMIN", "ADMIN role");
		UserInfo userInfo = new UserInfo(1, "aungba", "$dfed23", userRole, true, userAddress, "aungba@gmail.com");
		String username = "aungba";
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userInfo.getRole().getRole_name()));
		User user = new User(username, userInfo.getPassword(), grantedAuthorities);
		
		doReturn(userInfo).when(userInfoDao).findByUsername(username);
		
		UserDetails actual_user = userInfoService.loadUserByUsername("aungba");
		assertThat(actual_user).isEqualTo(user);
		
	}
	
	@Test
	public void TestLoadByUsername_notFound_normal() {
		String username = "aungba";
		UsernameNotFoundException uNotFoundExce = new UsernameNotFoundException("User not found with username : " + username);
		UserInfo userInfo = null;
		
		doReturn(userInfo).when(userInfoDao).findByUsername(username);
		
		UsernameNotFoundException actual_result = (UsernameNotFoundException) userInfoService.loadUserByUsername(username);
		assertThat(actual_result).isEqualTo(uNotFoundExce);
	}
	
	@Test
	public void TestRegisterUser_normal() {
		UserAddress userAddress = new UserAddress(1, "No. 33 testing", "YGN", "Myanmar", "11101");
		UserRole userRole = new UserRole(1, "ADMIN", "ADMIN role");
		UserInfo userInfo = new UserInfo(1, "aungba", "password", userRole, true, userAddress, "aungba@gmail.com");
		int expected_result = 1;	
		
		doReturn(expected_result).when(userInfoDao).registerUser(userInfo);
		
		int actual_result = userInfoService.registerUser(userInfo);
		assertThat(actual_result).isEqualTo(expected_result);
	}

}
