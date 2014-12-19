package com.charmyin.cmstudio.basic.authorize.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.charmyin.cmstudio.basic.authorize.vo.Organization;
import com.charmyin.cmstudio.basic.initial.SQLMapper;

/**
 * Mybatis Mapper Interface used for organization operation
 * @author charmyin
 *
 */
@SQLMapper
public interface OrganizationMapper {
	
	/**
	 * Get all organizations from organization role 
	 * @return
	 */
	public List<Organization> getAllOrganization();
	
	/**
	 * Get organization by the conditions contained by params "organization", it use "=" in where condition
	 * @param organization Organization instance which contains the question conditions.
	 * @return
	 */
	public List<Organization> getOrganizationEqual(Organization organization);
	
	/**
	 * Get organization by the conditions contained by params "organization", it use "like" in where condition
	 * @param organization Organization instance which contains the conditions.
	 * @return
	 */
	public List<Organization> getOrganizationLike(Organization organization);
	
	/**
	 * Get organization by organization id
	 * @param id
	 * @return
	 */
	public Organization getOrganizationById(int id);
	
	/**
	 * Insert a piece of organization to table
	 * @param organization
	 * @return
	 */
	public void insertOrganization(Organization organization);
	
	/**
	 * Update organization by organization object which must contain id
	 * @param organization
	 */
	public void updateOrganization(Organization organization);
	
	/**
	 * Delete organizations by id string array
	 * @param ids
	 */
	public void deleteOrganization(int id);
}
