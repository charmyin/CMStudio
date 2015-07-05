package com.charmyin.hxxc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.hxxc.persistence.JobCategoryTypeMapper;
import com.charmyin.hxxc.service.JobCategoryTypeService;
import com.charmyin.hxxc.vo.JobCategoryType;
import com.charmyin.hxxc.vo.JobCategoryTypeExample;
@Service
public class JobCategoryTypeServiceImpl implements JobCategoryTypeService {

	@Resource
	JobCategoryTypeMapper jobCategoryTypeMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return jobCategoryTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(JobCategoryType record) {
		return jobCategoryTypeMapper.insert(record);
	}

	@Override
	public int insertSelective(JobCategoryType record) {
		return jobCategoryTypeMapper.insertSelective(record);
	}

	@Override
	public JobCategoryType selectByPrimaryKey(String id) {
		return jobCategoryTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(JobCategoryType record) {
		return jobCategoryTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(JobCategoryType record) {
		return jobCategoryTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<JobCategoryType> findAllJobCategoryTypeByExample(JobCategoryTypeExample example) {
		return jobCategoryTypeMapper.findAllByExample(example);
	}

}
