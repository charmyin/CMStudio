package com.charmyin.hxxc.persistence;

import com.charmyin.cmstudio.basic.initial.SQLMapper;
import com.charmyin.hxxc.vo.JobCategoryType;
import com.charmyin.hxxc.vo.JobCategoryTypeExample;

import java.util.List;

@SQLMapper
public interface JobCategoryTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(JobCategoryType record);

    int insertSelective(JobCategoryType record);

    List<JobCategoryType> findAllByExample(JobCategoryTypeExample example);

    JobCategoryType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JobCategoryType record);

    int updateByPrimaryKey(JobCategoryType record);
}