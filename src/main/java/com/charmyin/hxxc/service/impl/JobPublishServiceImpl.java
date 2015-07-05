package com.charmyin.hxxc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.hxxc.persistence.JobPublishMapper;
import com.charmyin.hxxc.service.JobPublishService;
import com.charmyin.hxxc.vo.JobPublish;
import com.charmyin.hxxc.vo.JobPublishExample;
@Service
public class JobPublishServiceImpl implements JobPublishService {

	@Resource
	JobPublishMapper jobPublishMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return jobPublishMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(JobPublish record) {
		return jobPublishMapper.insert(record);
	}

	@Override
	public int insertSelective(JobPublish record) {
		return jobPublishMapper.insertSelective(record);
	}

	@Override
	public JobPublish selectByPrimaryKey(String id) {
		return jobPublishMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(JobPublish record) {
		return jobPublishMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(JobPublish record) {
		return jobPublishMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<JobPublish> findAllJobPublishByExample(JobPublishExample example) {
		return jobPublishMapper.findAllByExample(example);
	}

}
