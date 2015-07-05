package com.charmyin.hxxc.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.charmyin.hxxc.vo.EmployerInfo;
import com.charmyin.hxxc.vo.EmployerInfoExample;

@Service
public interface EmployerInfoService {
    
    int deleteByPrimaryKey(String id);

    int insert(EmployerInfo record);

    int insertSelective(EmployerInfo record);

    EmployerInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EmployerInfo record);

    int updateByPrimaryKey(EmployerInfo record);
    
    List<EmployerInfo> findAllEmployerInfoByExample(EmployerInfoExample record);
}