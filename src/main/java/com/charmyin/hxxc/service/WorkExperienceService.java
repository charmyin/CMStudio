package com.charmyin.hxxc.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.charmyin.hxxc.vo.WorkExperience;
import com.charmyin.hxxc.vo.WorkExperienceExample;

@Service
public interface WorkExperienceService {
    
    int deleteByPrimaryKey(String id);

    int insert(WorkExperience record);

    int insertSelective(WorkExperience record);

    WorkExperience selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkExperience record);

    int updateByPrimaryKey(WorkExperience record);
    
    List<WorkExperience> findAllWorkExperienceByExample(WorkExperienceExample record);
}