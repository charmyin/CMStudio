package com.charmyin.hxxc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.hxxc.persistence.WorkExperienceMapper;
import com.charmyin.hxxc.service.WorkExperienceService;
import com.charmyin.hxxc.vo.WorkExperience;
import com.charmyin.hxxc.vo.WorkExperienceExample;
@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

	@Resource
	WorkExperienceMapper workExperienceMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return workExperienceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WorkExperience record) {
		return workExperienceMapper.insert(record);
	}

	@Override
	public int insertSelective(WorkExperience record) {
		return workExperienceMapper.insertSelective(record);
	}

	@Override
	public WorkExperience selectByPrimaryKey(String id) {
		return workExperienceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkExperience record) {
		return workExperienceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WorkExperience record) {
		return workExperienceMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<WorkExperience> findAllWorkExperienceByExample(WorkExperienceExample example) {
		return workExperienceMapper.findAllByExample(example);
	}

}
