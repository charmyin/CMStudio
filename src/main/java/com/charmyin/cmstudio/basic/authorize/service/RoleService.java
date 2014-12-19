package com.charmyin.cmstudio.basic.authorize.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.vo.Role;
import com.charmyin.cmstudio.basic.authorize.vo.User;

/**
 * Role operation service
 * @author YinCM
 * @since  2013-9-3 10:12:00
 */
@Service
public interface RoleService {
	
	/**
	 * Get all the role items.
	 * Only the developer has this operation. 
	 * @return Role List
	 */
	public List<Role> getAllRole();
	
	/**
	 * Update one role by id
	 * @param role
	 */
	public void updateRole(Role role);
	
	/**
	 * Insert Role 
	 * @param role
	 * @throws SQLException 
	 */
	public void insertRole(Role role) throws SQLException;
	
	/**
	 * Delete role by name array (name)
	 * @param name array Role Names
	 */
	public void deleteRole(String[] names);
	
	/**
	 * SearchRole by role fields
	 * @param role
	 * @return Role List
	 */
	public List<Role> searchRole(Role role);
	
	
	public String getRolesByUserId(User userIdStr);
	
	/**
	 * Get roles by organization Id
	 * @param orgId
	 * @return
	 */
	public List<Role> getRoleByOrganizationId(Integer orgId);
	
	/**
	 * Get role item by its name
	 * @param name
	 * @return Role Object
	 */
	public Role getRoleByName(String name);
	
 
}
