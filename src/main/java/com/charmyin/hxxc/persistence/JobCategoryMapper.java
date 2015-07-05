package com.charmyin.hxxc.persistence;

import com.charmyin.cmstudio.basic.initial.SQLMapper;
import com.charmyin.hxxc.vo.JobCategory;
import com.charmyin.hxxc.vo.JobCategoryExample;

import java.util.List;
@SQLMapper
public interface JobCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(JobCategory record);

    int insertSelective(JobCategory record);

    List<JobCategory> findAllByExample(JobCategoryExample example);

    JobCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JobCategory record);

    int updateByPrimaryKey(JobCategory record);
}