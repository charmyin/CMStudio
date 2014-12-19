package com.charmyin.cmstudio.basic.authorize.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.authorize.service.RoleService;
import com.charmyin.cmstudio.basic.authorize.vo.Role;
import com.charmyin.cmstudio.basic.authorize.vo.User;
import com.charmyin.cmstudio.common.utils.ArrayUtil;
import com.charmyin.cmstudio.common.utils.JSRErrorUtil;
import com.charmyin.cmstudio.web.utils.ResponseUtil;

/**
 * Role Web Controller
 * @author YinCM
 * @since 2013-9-2 16:47:35
 * 
 */
@Controller
public class RoleController {
	private static Logger logger = Logger.getLogger(RoleController.class);
	
	@Resource
	private RoleService roleService;
	
	/**
	 * Direct to the roleManage jsp file
	 * @return jsp file path
	 */
	@RequestMapping(value="/role/manage")
	public String manageRole(){
		return "basic/role/roleManage";
	}	
	
	/**
	 * Get all role items in database
	 * @return all role items JSON type data
	 */
	@RequestMapping("/role/all")
	@ResponseBody
	public List<Role> getAllRole(){
		List<Role> roleList = roleService.getAllRole();
		return roleList;
	}
	
	/**
	 * Update role
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/role/update", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String updateRole(@Valid Role role, BindingResult result){
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		
		try{
			roleService.updateRole(role);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("更新过程中出错！");
		}
		return ResponseUtil.getSuccessResultString();
	}
	
	/**
	 * Insert the role committed from client
	 * @return 
	 */
	@RequestMapping(value="/role/save", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	@Transactional
	public String saveRole(@Valid Role role, BindingResult result){
		
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		
		try{
			roleService.insertRole(role);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
		return ResponseUtil.getSuccessResultString();
	}
	
	/**
	 * Delete by ids string split by ',' ; Example:"1,2,3,4,5" 
	 * @param ids eg."1,2,3,4,5"
	 * @return
	 */
	@RequestMapping(value="/role/deleteByNames", method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	//TODO 长度异常，这里是否要去考虑,测试的时候考虑
	public Map<String, Object> deleteRoleByNames(@RequestParam("names") String names){
		//ids can not be null
		if(names==null || names.isEmpty()){
			Map<String, Object> map = ResponseUtil.getFailResultMap();
			map.put("errorMsg", "删除数据，name不允许为空！");
			return map;
		}
		
		String[] namesArrayNotEmpty = ArrayUtil.removeEmptyString(names.split(","));
		try{
			roleService.deleteRole(namesArrayNotEmpty);
		}catch(Exception e){
			logger.error(e.getMessage());
			Map<String, Object> map = ResponseUtil.getFailResultMap();
			map.put("errorMsg", "删除过程中出错！");
			return map;
		}
		
		return ResponseUtil.getSuccessResultMap();
	}
	
	@RequestMapping("/role/orgId/{organizationId}/all")
	@ResponseBody
	public List<Role> getRoleByOrganizationId(@PathVariable("organizationId") Integer organizationId){
		List<Role> roleList = roleService.getRoleByOrganizationId(organizationId);
		return roleList;
	}
	
	@RequestMapping("/role/getRolesByUserId/{id}")
	@ResponseBody
	public String getRolesByUserId(User user){
		return roleService.getRolesByUserId(user);
	}
	
	
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	
}
