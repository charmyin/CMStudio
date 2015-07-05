package com.charmyin.hxxc.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.charmyin.hxxc.vo.JobPublish;
import com.charmyin.hxxc.vo.JobPublishExample;

@Service
public interface JobPublishService {
    
    int deleteByPrimaryKey(String id);

    int insert(JobPublish record);

    int insertSelective(JobPublish record);

    JobPublish selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JobPublish record);

    int updateByPrimaryKey(JobPublish record);
    
    List<JobPublish> findAllJobPublishByExample(JobPublishExample record);
}