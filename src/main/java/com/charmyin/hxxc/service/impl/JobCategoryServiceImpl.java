package com.charmyin.hxxc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.hxxc.persistence.JobCategoryMapper;
import com.charmyin.hxxc.service.JobCategoryService;
import com.charmyin.hxxc.vo.JobCategory;
import com.charmyin.hxxc.vo.JobCategoryExample;
@Service
public class JobCategoryServiceImpl implements JobCategoryService {

	@Resource
	JobCategoryMapper jobCategoryMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return jobCategoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(JobCategory record) {
		return jobCategoryMapper.insert(record);
	}

	@Override
	public int insertSelective(JobCategory record) {
		return jobCategoryMapper.insertSelective(record);
	}

	@Override
	public JobCategory selectByPrimaryKey(String id) {
		return jobCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(JobCategory record) {
		return jobCategoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(JobCategory record) {
		return jobCategoryMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<JobCategory> findAllJobCategoryByExample(JobCategoryExample example) {
		return jobCategoryMapper.findAllByExample(example);
	}

}
