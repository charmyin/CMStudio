package com.charmyin.cmstudio.basic.authorize.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.charmyin.cmstudio.basic.authorize.vo.Role;
import com.charmyin.cmstudio.basic.authorize.vo.User;
import com.charmyin.cmstudio.basic.initial.SQLMapper;

/**
 * Mybatis Mapper Interface used for role operation
 * @author charmyin
 *
 */
@SQLMapper
public interface RoleMapper {
	
	/**
	 * Get all roles from role role 
	 * @return
	 */
	public List<Role> getAllRole();
	
	/**
	 * Get role by the conditions contained by params "role", it use "=" in where condition
	 * @param role Role instance which contains the question conditions.
	 * @return
	 */
	public List<Role> getRoleEqual(Role role);
	
	/**
	 * Get role by the conditions contained by params "role", it use "like" in where condition
	 * @param role Role instance which contains the conditions.
	 * @return
	 */
	public List<Role> getRoleLike(Role role);
	
	/**
	 * Get role by role id
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM SHIRO_ROLE WHERE name=#{name, jdbcType=VARCHAR}")
	public Role getRoleByName(String name);
	
	
	public List<String> getRolesByUserId(User user);
	
	/**
	 * Insert a piece of role to table
	 * @param role
	 * @return
	 */
	public void insertRole(Role role);
	
	/**
	 * Update role by role object which must contain id
	 * @param role
	 */
	public void updateRole(Role role);
	
	/**
	 * Delete roles by id string array
	 * @param ids
	 */
	public void deleteRole(String name);
}
