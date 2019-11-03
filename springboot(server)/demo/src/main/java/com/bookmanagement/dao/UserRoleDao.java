package com.bookmanagement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bookmanagement.entity.UserRole;

/**
 * This is UserRoleDao interface
 * 
 * @author Aung Ba
 *
 */
@Mapper
public interface UserRoleDao {

	/**
	 * This method is used to register user role
	 * 
	 * @param userRole user role
	 */
	void insertRole(UserRole userRole);

	/**
	 * This method is used to retrieve all user role
	 * 
	 * @return userRoleList
	 */
	List<UserRole> getAllRole();

	/**
	 * This method is used to retrieve userRole by id
	 * 
	 * @param id user role id
	 * @return userRole
	 */
	UserRole getRoleById(int id);

	/**
	 * This method is used to update userRole by id
	 * 
	 * @param userRole userRole
	 * @param id       user role id
	 * @return 0 or 1
	 */
	int updateRoleById(UserRole userRole, int id);

	/**
	 * This method is used to delete user role by id
	 * 
	 * @param id user role id
	 * @return 0 or 1
	 */
	int deleteRoleById(int id);

}
