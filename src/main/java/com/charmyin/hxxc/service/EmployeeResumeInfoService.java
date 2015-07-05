package com.charmyin.hxxc.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.charmyin.hxxc.vo.EmployeeResumeInfo;
import com.charmyin.hxxc.vo.EmployeeResumeInfoExample;

@Service
public interface EmployeeResumeInfoService {
    
    int deleteByPrimaryKey(String id);

    int insert(EmployeeResumeInfo record);

    int insertSelective(EmployeeResumeInfo record);

    EmployeeResumeInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EmployeeResumeInfo record);

    int updateByPrimaryKey(EmployeeResumeInfo record);
    
    List<EmployeeResumeInfo> findAllEmployeeResumeInfoByExample(EmployeeResumeInfoExample record);
}