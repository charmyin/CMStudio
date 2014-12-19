package com.charmyin.cmstudio.basic.authorize.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.vo.Organization;

/**
 * Organization operation service
 * @author YinCM
 * @since  2013-9-3 10:12:00
 */
@Service
public interface OrganizationService {
	
	/**
	 * Get all the organization items.
	 * Only the developer has this operation. 
	 * @return Organization List
	 */
	public List<Organization> getAllOrganization();
	
	/**
	 * Get the children organization of one parent node, only include the second generation.
	 * @param parentId
	 * @return Organization List
	 */
	public List<Organization> getChildrenOrganizations(int parentId);
	
	/**
	 * Update one organization by id
	 * @param organization
	 */
	public void updateOrganization(Organization organization);
	
	/**
	 * Insert Organization 
	 * @param organization
	 */
	public void insertOrganization(Organization organization);
	
	/**
	 * Delete organization by id array (int)
	 * @param int array Organization Ids
	 */
	public void deleteOrganization(int[] ids);
	
	/**
	 * Get organization item by its id
	 * @param id
	 * @return Organization Object
	 */
	public Organization getOrganizationById(int id);
	
	/**
	 * 获取当前部门所属的公司Id
	 * @param orgId
	 * @return
	 */
	public int getCooporationId(int orgId);
	
}
