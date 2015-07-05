package com.charmyin.hxxc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.hxxc.persistence.ResumePostMapper;
import com.charmyin.hxxc.service.ResumePostService;
import com.charmyin.hxxc.vo.ResumePost;
import com.charmyin.hxxc.vo.ResumePostExample;
@Service
public class ResumePostServiceImpl implements ResumePostService {

	@Resource
	ResumePostMapper resumePostMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return resumePostMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ResumePost record) {
		return resumePostMapper.insert(record);
	}

	@Override
	public int insertSelective(ResumePost record) {
		return resumePostMapper.insertSelective(record);
	}

	@Override
	public ResumePost selectByPrimaryKey(String id) {
		return resumePostMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ResumePost record) {
		return resumePostMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ResumePost record) {
		return resumePostMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ResumePost> findAllResumePostByExample(ResumePostExample example) {
		return resumePostMapper.findAllByExample(example);
	}

}
