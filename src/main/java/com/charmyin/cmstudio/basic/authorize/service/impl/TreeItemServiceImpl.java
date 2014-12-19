package com.charmyin.cmstudio.basic.authorize.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Sha512Hash;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.persistence.TreeItemMapper;
import com.charmyin.cmstudio.basic.authorize.service.TreeItemService;
import com.charmyin.cmstudio.basic.authorize.vo.Organization;
import com.charmyin.cmstudio.basic.authorize.vo.TreeItem;
import com.charmyin.cmstudio.basic.authorize.vo.User;

@Service
public class TreeItemServiceImpl implements TreeItemService {
	
	private void getChildTreeNodes(List<TreeItem> allNodesBelow, TreeItem parentItem, List<TreeItem> allNodesItem){
		for(TreeItem ti : allNodesItem){
			if(ti.getParentId() == parentItem.getId()){
				allNodesBelow.add(ti);
				getChildTreeNodes(allNodesBelow, ti, allNodesItem);
			}
		}
	}

	@Resource
	TreeItemMapper treeItemMapper;
	@Override
	public List<TreeItem> getOrganizationWithUsers(int organizationId) {
		Organization organization = new Organization();
		
		//所有的TreeItem
		List<TreeItem> listAll = treeItemMapper.getOrganizationTreeItemEqual(organization);
	
		organization.setId(organizationId);
		List<TreeItem> list = treeItemMapper.getOrganizationTreeItemEqual(organization);
		
		List<TreeItem> allNodesBelow = new ArrayList<TreeItem>();
		getChildTreeNodes(allNodesBelow, list.get(0), listAll);
		
		
		List<TreeItem> itemList = new ArrayList<TreeItem>();
		
		for(TreeItem tmpUser : allNodesBelow){
			tmpUser.setChkDisabled(true);
		}
		
		User user = new User();
		for(TreeItem treeItem : allNodesBelow){
			user.setOrganizationId(treeItem.getId());
			
			List<TreeItem> userList = treeItemMapper.getUserTreeItemEqual(user);
			for(TreeItem tmpUser : userList){
				
				tmpUser.setId(100000+tmpUser.getId());
			}
			itemList.addAll(userList);
		}
		itemList.addAll(allNodesBelow);
		return itemList;
	}
	
}
