package com.charmyin.hxxc.persistence;

import com.charmyin.cmstudio.basic.initial.SQLMapper;
import com.charmyin.hxxc.vo.EmployeeResumeInfo;
import com.charmyin.hxxc.vo.EmployeeResumeInfoExample;

import java.util.List;

@SQLMapper
public interface EmployeeResumeInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(EmployeeResumeInfo record);

    int insertSelective(EmployeeResumeInfo record);

    List<EmployeeResumeInfo> findAllByExample(EmployeeResumeInfoExample example);

    EmployeeResumeInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EmployeeResumeInfo record);

    int updateByPrimaryKey(EmployeeResumeInfo record);
}