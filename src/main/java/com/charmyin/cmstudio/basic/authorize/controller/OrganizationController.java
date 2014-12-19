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

import com.charmyin.cmstudio.basic.authorize.service.OrganizationService;
import com.charmyin.cmstudio.basic.authorize.vo.Organization;
import com.charmyin.cmstudio.common.utils.ArrayUtil;
import com.charmyin.cmstudio.common.utils.JSRErrorUtil;
import com.charmyin.cmstudio.web.utils.ResponseUtil;

/**
 * Organization Web Controller
 * @author YinCM
 * @since 2013-9-2 16:47:35
 * 
 */
@Controller
public class OrganizationController {
	private static Logger logger = Logger.getLogger(OrganizationController.class);
	
	@Resource
	private OrganizationService organizationService;


	/**
	 * Direct to the orgManage jsp file
	 * @return jsp file path
	 */
	@RequestMapping(value="/organization/manage")
	public String manageOrganization(){
		return "basic/organization/orgManage";
	}	
	
	/**
	 * Get all organization items in database
	 * @return all organization items JSON type data
	 */
	@RequestMapping("/organization/all")
	@ResponseBody
	public List<Organization> getAllOrganization(){
		List<Organization> organizationList = organizationService.getAllOrganization();
		return organizationList;
	}
	
	//TODO The parentId parse exception has not been handled~
		/**
		 * Get child organization under one parent organization (Not include all the children and grand children, just the next level children)
		 * @param parentId 
		 * @return Organization items' JSON type data under the parent;  
		 */
	@RequestMapping(value="/organizationparent/{parentId}/all", method=RequestMethod.GET)
	@ResponseBody
	public List<Organization> getOrganizationByParent(@PathVariable int parentId){
		List<Organization> organizationList = organizationService.getChildrenOrganizations(parentId);
		return organizationList;
	}


	/**
	 * Update organization
	 * @param organization
	 * @return
	 */
	@RequestMapping(value="/organization/update", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String updateOrganization(@Valid Organization organization, BindingResult result){
		
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		
		try{
			organizationService.updateOrganization(organization);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("更新过程中出错！");
		}
		
		return ResponseUtil.getSuccessResultString();
	}
	
	/**
	 * Insert the organization committed from client
	 * @return 
	 */
	@RequestMapping(value="/organization/save", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveOrganization( @Valid Organization organization, BindingResult result){
		
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		
		try{
			organizationService.insertOrganization(organization);
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
	@RequestMapping(value="/organization/deleteByIds", method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	public Map<String, Object> deleteOrganizationByIds(@RequestParam("ids") String ids){
		//ids can not be null
		if(ids==null || ids.isEmpty()){
			Map<String, Object> map = ResponseUtil.getFailResultMap();
			map.put("errorMsg", "删除数据，id不允许为空！");
			return map;
		}
		
		String[] idsArrayNotEmpty = ArrayUtil.removeEmptyString(ids.split(","));
		
		int[] idsIntArray = new int[idsArrayNotEmpty.length];
		try{
			for(int i=0; i<idsArrayNotEmpty.length;i++){
				int idInt = Integer.parseInt(idsArrayNotEmpty[i]);
				idsIntArray[i] = idInt;
			}
			organizationService.deleteOrganization(idsIntArray);
		}catch(NumberFormatException ne){
			logger.error("提交id值错误!"+ne.getMessage());
			Map<String, Object> map = ResponseUtil.getFailResultMap();
			map.put("errorMsg", "提交id值错误!");
			return map;
		}catch(Exception e){
			logger.error(e.getMessage());
			Map<String, Object> map = ResponseUtil.getFailResultMap();
			map.put("errorMsg", "删除过程中出错！");
			return map;
		}
		
		return ResponseUtil.getSuccessResultMap();
	}
	
	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	
}
