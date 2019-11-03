package com.bookmanagement.dao;

import com.bookmanagement.entity.UserInfo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * This is UserInfoDao interface
 * 
 * @author Aung Ba
 *
 */
@Mapper
public interface UserInfoDao {

	/**
	 * This method is used to find the userInfo by username
	 * 
	 * @param username
	 * @return
	 */
	UserInfo findByUsername(String username);

	/**
	 * This method is used to register the userInfo
	 * 
	 * @param user user
	 * @return userInfo
	 */
	int registerUser(UserInfo user);

	List<UserInfo> getUserList();
}
