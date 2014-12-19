package com.charmyin.cmstudio.basic.authorize.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.authorize.service.TreeItemService;
import com.charmyin.cmstudio.basic.authorize.vo.TreeItem;
import com.charmyin.cmstudio.basic.authorize.vo.User;
import com.charmyin.cmstudio.web.utils.UserSessionUtil;

/**
 * Organization Web Controller
 * @author YinCM
 * @since 2013-9-2 16:47:35
 * 
 */
@Controller
public class TreeItemController {

	@Resource
	TreeItemService treeItemService;
	
	@RequestMapping(value="/organizationWithUser/treeItems", method=RequestMethod.GET)
	@ResponseBody
	public List<TreeItem> getTreeItemByParentWithUser(int organizationId){
		//COID
		User userInfo = UserSessionUtil.getUserInSession();
		organizationId = userInfo.getCoId()==null?1:userInfo.getCoId();
		List<TreeItem> list = treeItemService.getOrganizationWithUsers(organizationId);
		return list;
	}

}
