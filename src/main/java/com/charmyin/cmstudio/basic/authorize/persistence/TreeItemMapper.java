package com.charmyin.cmstudio.basic.authorize.persistence;

import java.util.List;

import com.charmyin.cmstudio.basic.authorize.vo.Organization;
import com.charmyin.cmstudio.basic.authorize.vo.TreeItem;
import com.charmyin.cmstudio.basic.authorize.vo.User;
import com.charmyin.cmstudio.basic.initial.SQLMapper;

/**
 * Mybatis Mapper Interface used for user operation
 * @author charmyin
 *
 */
@SQLMapper
public interface TreeItemMapper {
	
	List<TreeItem> getOrganizationTreeItemEqual(Organization organization);
	
	List<TreeItem> getUserTreeItemEqual(User user);
	
}
