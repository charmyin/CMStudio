package com.charmyin.hxxc.persistence;

import com.charmyin.cmstudio.basic.initial.SQLMapper;
import com.charmyin.hxxc.vo.ResumePost;
import com.charmyin.hxxc.vo.ResumePostExample;

import java.util.List;

@SQLMapper
public interface ResumePostMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResumePost record);

    int insertSelective(ResumePost record);

    List<ResumePost> findAllByExample(ResumePostExample example);

    ResumePost selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResumePost record);

    int updateByPrimaryKey(ResumePost record);
}