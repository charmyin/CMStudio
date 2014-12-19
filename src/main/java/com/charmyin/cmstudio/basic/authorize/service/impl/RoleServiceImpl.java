package com.charmyin.cmstudio.basic.authorize.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.persistence.RoleMapper;
import com.charmyin.cmstudio.basic.authorize.service.RoleService;
import com.charmyin.cmstudio.basic.authorize.vo.Role;
import com.charmyin.cmstudio.basic.authorize.vo.User;

/**
 * Implementation of Role operation service
 * @author YinCM
 * @since 2013-9-3 14:08:16
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper roleMapper ;
	
	@Override
	public List<Role> getAllRole() {
		List<Role> roleList = roleMapper.getAllRole();
		return roleList;
	}
	
	@Override
	public void insertRole(Role role) throws SQLException{
		roleMapper.insertRole(role);
	}
	
	@Override
	public void updateRole(Role role) {
		roleMapper.updateRole(role);
	}

	@Override
	public void deleteRole(String[] names) {
		for(String name : names){
			roleMapper.deleteRole(name);
		}
	}

	@Override
	public List<Role> searchRole(Role role) {
		List<Role> roleList = roleMapper.getRoleEqual(role);
		return roleList;
	}

	@Override
	public Role getRoleByName(String name) {
		Role role = roleMapper.getRoleByName(name);
		return role;
	}	
	
	@Override
	public String getRolesByUserId(User user) {
		
		List<String> roleStringList = roleMapper.getRolesByUserId(user);
		StringBuilder  sb = new StringBuilder();
		for(String str : roleStringList){
			sb.append(str).append(",");
		}
		String resultStr="";
		if(sb.length()>0){
			resultStr = sb.substring(0, sb.length()-1);
		}
		return resultStr;
	}

	@Override
	public List<Role> getRoleByOrganizationId(Integer orgId) {
		Role role = new Role();
		role.setOrganizationId(orgId);
		List<Role> roleList = roleMapper.getRoleEqual(role);
		return roleList;
	}
	
	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}
   
}
