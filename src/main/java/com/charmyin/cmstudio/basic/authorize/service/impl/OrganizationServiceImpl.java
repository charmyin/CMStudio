package com.charmyin.cmstudio.basic.authorize.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.persistence.OrganizationMapper;
import com.charmyin.cmstudio.basic.authorize.service.OrganizationService;
import com.charmyin.cmstudio.basic.authorize.vo.Organization;

/**
 * Implementation of Organization operation service
 * @author YinCM
 * @since 2013-9-3 14:08:16
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Resource
	private OrganizationMapper organizationMapper ;
	
	@Override
	public List<Organization> getAllOrganization() {
		List<Organization> organizationList = organizationMapper.getAllOrganization();
		return organizationList;
	}

	@Override
	public int getCooporationId(int orgId) {
		while(true){
			Organization org = getOrganizationById(orgId);
			//公司 或者顶级则返回
			if(org.getOrganizationType()==1){
				return org.getId();
			}
		}	
	}

	@Override
	public List<Organization> getChildrenOrganizations(int parentId) {
		Organization organization = new Organization();
		organization.setParentId(parentId);
		List<Organization> organizationList = organizationMapper.getOrganizationEqual(organization);
		return organizationList;
	}
	
	@Override
	public void insertOrganization(Organization organization){
		organizationMapper.insertOrganization(organization);
	}
	
	@Override
	public void updateOrganization(Organization organization) {
		organizationMapper.updateOrganization(organization);
	}

	@Override
	public void deleteOrganization(int[] ids) {
		for(int id : ids){
			organizationMapper.deleteOrganization(id);
		}
	}

	@Override
	public Organization getOrganizationById(int id) {
		Organization organization = organizationMapper.getOrganizationById(id);
		return organization;
	}

	public OrganizationMapper getOrganizationMapper() {
		return organizationMapper;
	}

	public void setOrganizationMapper(OrganizationMapper organizationMapper) {
		this.organizationMapper = organizationMapper;
	}
   
}
