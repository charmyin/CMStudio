package com.charmyin.hxxc.persistence;

import com.charmyin.cmstudio.basic.initial.SQLMapper;
import com.charmyin.hxxc.vo.JobPublish;
import com.charmyin.hxxc.vo.JobPublishExample;

import java.util.List;

@SQLMapper
public interface JobPublishMapper {
    int deleteByPrimaryKey(String id);

    int insert(JobPublish record);

    int insertSelective(JobPublish record);

    List<JobPublish> findAllByExample(JobPublishExample example);

    JobPublish selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JobPublish record);

    int updateByPrimaryKey(JobPublish record);
}