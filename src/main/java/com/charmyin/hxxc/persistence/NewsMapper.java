package com.charmyin.hxxc.persistence;

import com.charmyin.cmstudio.basic.initial.SQLMapper;
import com.charmyin.hxxc.vo.News;
import com.charmyin.hxxc.vo.NewsExample;

import java.util.List;

@SQLMapper
public interface NewsMapper {
    int deleteByPrimaryKey(String id);

    int insert(News record);

    int insertSelective(News record);

    List<News> findAllByExample(NewsExample example);

    News selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
}