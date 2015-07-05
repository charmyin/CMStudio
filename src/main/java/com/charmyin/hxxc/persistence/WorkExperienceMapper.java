package com.charmyin.hxxc.persistence;

import com.charmyin.cmstudio.basic.initial.SQLMapper;
import com.charmyin.hxxc.vo.WorkExperience;
import com.charmyin.hxxc.vo.WorkExperienceExample;

import java.util.List;

@SQLMapper
public interface WorkExperienceMapper {
    int deleteByPrimaryKey(String id);

    int insert(WorkExperience record);

    int insertSelective(WorkExperience record);

    List<WorkExperience> findAllByExample(WorkExperienceExample example);

    WorkExperience selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkExperience record);

    int updateByPrimaryKey(WorkExperience record);
}