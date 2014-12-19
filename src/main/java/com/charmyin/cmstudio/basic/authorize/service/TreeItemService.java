package com.charmyin.cmstudio.basic.authorize.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.vo.TreeItem;


@Service
public interface TreeItemService {
	
	List<TreeItem> getOrganizationWithUsers(int organizationId);
	
}
