package com.charmyin.cmstudio.basic.authorize.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.vo.User;

/**
 * 用户服务层接口
 * @author YinCM
 * @since 2013-9-14 10:59:52
 */
@Service
public interface UserService {
	
	/**
	 * Get all users from user role 
	 * @return
	 */
	public List<User> getAllUser();
	
	/**
	 * Get all users from user role 
	 * @return
	 */
	public List<User> findAllUser(User user);
 
	/**
	 * Get user by user id
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
	
	/**
	 * Get user by user name
	 * @param userName
	 * @return
	 */
	public User getUserByName(String userName);
	
	/**
	 * Get user by orgnization id
	 * @return
	 */
	public List<User> getUserByOrgnizationId(int organizationId);
	
	/**
	 * Insert a piece of user to table
	 * @param user
	 * @return
	 */
	public void insertUser(User user);
	
	/**
	 * Update user by user object which must contain id
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * Delete users by id string array, at the same time delete all the rows in role_user related to the ids 
	 * @param ids
	 */
	public void deleteUser(int[] ids);

	/**
	 * Update the roles of an user
	 * @param id the user id
	 * @param roles the role names owned by a user ,eg: admin,player,fiddler
	 */
	public void updateRoles(Integer id, String roles);
	
	/**
	 * Get roleName list  by user id
	 * @userId user id
	 * @return roleName list
	 */
	public List<String> getRoleNamesByUserId(int userId);
	
	/**
	 * Delete user_role by user id
	 * @param userId
	 */
	public void deleteUserRoleByUserId(int userId);
	
	/**
	 * insert user role 
	 * @param map
	 */
	public void insertUserRole(int userId, String roleName);
}
