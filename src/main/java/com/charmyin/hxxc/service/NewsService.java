package com.charmyin.hxxc.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.charmyin.hxxc.vo.News;
import com.charmyin.hxxc.vo.NewsExample;

@Service
public interface NewsService {
    
    int deleteByPrimaryKey(String id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
    
    List<News> findAllNewsByExample(NewsExample record);
}