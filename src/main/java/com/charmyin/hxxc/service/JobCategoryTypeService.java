package com.charmyin.hxxc.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.charmyin.hxxc.vo.JobCategoryType;
import com.charmyin.hxxc.vo.JobCategoryTypeExample;

@Service
public interface JobCategoryTypeService {
    
    int deleteByPrimaryKey(String id);

    int insert(JobCategoryType record);

    int insertSelective(JobCategoryType record);

    JobCategoryType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JobCategoryType record);

    int updateByPrimaryKey(JobCategoryType record);
    
    List<JobCategoryType> findAllJobCategoryTypeByExample(JobCategoryTypeExample record);
}