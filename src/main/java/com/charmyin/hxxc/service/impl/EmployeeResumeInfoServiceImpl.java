package com.charmyin.hxxc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.hxxc.persistence.EmployeeResumeInfoMapper;
import com.charmyin.hxxc.service.EmployeeResumeInfoService;
import com.charmyin.hxxc.vo.EmployeeResumeInfo;
import com.charmyin.hxxc.vo.EmployeeResumeInfoExample;
@Service
public class EmployeeResumeInfoServiceImpl implements EmployeeResumeInfoService {

	@Resource
	EmployeeResumeInfoMapper employeeResumeInfoMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return employeeResumeInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EmployeeResumeInfo record) {
		return employeeResumeInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(EmployeeResumeInfo record) {
		return employeeResumeInfoMapper.insertSelective(record);
	}

	@Override
	public EmployeeResumeInfo selectByPrimaryKey(String id) {
		return employeeResumeInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EmployeeResumeInfo record) {
		return employeeResumeInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EmployeeResumeInfo record) {
		return employeeResumeInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<EmployeeResumeInfo> findAllEmployeeResumeInfoByExample(EmployeeResumeInfoExample example) {
		return employeeResumeInfoMapper.findAllByExample(example);
	}

}
