package com.charmyin.hxxc.persistence;

import com.charmyin.cmstudio.basic.initial.SQLMapper;
import com.charmyin.hxxc.vo.EmployerInfo;
import com.charmyin.hxxc.vo.EmployerInfoExample;

import java.util.List;

@SQLMapper
public interface EmployerInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(EmployerInfo record);

    int insertSelective(EmployerInfo record);

    List<EmployerInfo> findAllByExample(EmployerInfoExample example);

    EmployerInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EmployerInfo record);

    int updateByPrimaryKey(EmployerInfo record);
}