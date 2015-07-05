package com.charmyin.hxxc.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.charmyin.hxxc.vo.JobCategory;
import com.charmyin.hxxc.vo.JobCategoryExample;

@Service
public interface JobCategoryService {
    
    int deleteByPrimaryKey(String id);

    int insert(JobCategory record);

    int insertSelective(JobCategory record);

    JobCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JobCategory record);

    int updateByPrimaryKey(JobCategory record);
    
    List<JobCategory> findAllJobCategoryByExample(JobCategoryExample record);
}