package com.charmyin.hxxc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.hxxc.persistence.EmployerInfoMapper;
import com.charmyin.hxxc.service.EmployerInfoService;
import com.charmyin.hxxc.vo.EmployerInfo;
import com.charmyin.hxxc.vo.EmployerInfoExample;
@Service
public class EmployerInfoServiceImpl implements EmployerInfoService {

	@Resource
	EmployerInfoMapper employerInfoMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return employerInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EmployerInfo record) {
		return employerInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(EmployerInfo record) {
		return employerInfoMapper.insertSelective(record);
	}

	@Override
	public EmployerInfo selectByPrimaryKey(String id) {
		return employerInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EmployerInfo record) {
		return employerInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EmployerInfo record) {
		return employerInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<EmployerInfo> findAllEmployerInfoByExample(EmployerInfoExample example) {
		return employerInfoMapper.findAllByExample(example);
	}

}
