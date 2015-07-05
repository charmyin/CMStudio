package com.charmyin.hxxc.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.charmyin.hxxc.vo.ResumePost;
import com.charmyin.hxxc.vo.ResumePostExample;

@Service
public interface ResumePostService {
    
    int deleteByPrimaryKey(String id);

    int insert(ResumePost record);

    int insertSelective(ResumePost record);

    ResumePost selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResumePost record);

    int updateByPrimaryKey(ResumePost record);
    
    List<ResumePost> findAllResumePostByExample(ResumePostExample record);
}