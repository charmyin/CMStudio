package com.charmyin.cmstudio.basic.authorize.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.persistence.UserMapper;
import com.charmyin.cmstudio.basic.authorize.service.UserService;
import com.charmyin.cmstudio.basic.authorize.vo.User;

/**
 * 用户信息数据服务实现
 * @author YinCM
 * @since 2013-9-14 11:05:19
 */
@Service
public class UserServiceImpl implements UserService{
	
	Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Resource
	UserMapper userMapper;

	@Override
	public List<User> getAllUser() {
		List<User> list = userMapper.getAllUser();
		return list;
	}
	
	@Override
	public List<User> findAllUser(User user) {
		List<User> list = userMapper.findAllUser(user);
		return list;
	}
	

	@Override
	public User getUserById(int id) {
		User queryUser = new User();
		queryUser.setId(id);
		List<User> userList = userMapper.getUserEqual(queryUser);
		if(userList==null || userList.size()<1){
			return null;
		}
		return userList.get(0);
	}

	@Override
	public List<User> getUserByOrgnizationId(int organizationId) {
		User user = new User();
		user.setOrganizationId(organizationId);
		List<User> list = userMapper.getUserEqual(user);
		return list;
	}

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}
	

	@Override
	public User getUserByName(String userName) {
		User queryUser = new User();
		queryUser.setLoginId(userName);
		List<User> userList = userMapper.getUserEqual(queryUser);
		if(userList==null || userList.size()<1){
			return null;
		}
		return userList.get(0);
	}

	@Override
	public void deleteUser(int[] ids) {
		for(int id : ids){
			userMapper.deleteUserRoleByUserId(id);
			userMapper.deleteUser(id);
		}
	}
	
	@Override
	public void updateRoles(Integer userId, String roles) {
		
		//First delete all the user_role
		userMapper.deleteUserRoleByUserId(userId);
		
		if(roles==null || roles.trim().equals("")){
			return;
		}
		String[] updateRoleNames = roles.split(",");
		
		//Then insert all the user_roles
		//Use the hashset to remove the duplicated names
		Set<String> nameHashSet = new HashSet<String>();
		for(String roleName : updateRoleNames){
			nameHashSet.add(roleName);
		}
		Iterator<String> iterator = nameHashSet.iterator();
		while(iterator.hasNext()){
			String roleNameDistinct = iterator.next();
			insertUserRole(userId, roleNameDistinct);
		}
	}
	
	@Override
	public void deleteUserRoleByUserId(int userId) {
		userMapper.deleteUserRoleByUserId(userId);
	}

	@Override
	public void insertUserRole(int userId, String roleName){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("roleName", roleName);
		userMapper.insertUserRole(map);
	}
	
	@Override
	public List<String> getRoleNamesByUserId(int userId) {
		List<String> roleNamesList = userMapper.getRoleNamesByUserId(userId);
		return roleNamesList;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
}
