package com.charmyin.hxxc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.hxxc.persistence.NewsMapper;
import com.charmyin.hxxc.service.NewsService;
import com.charmyin.hxxc.vo.News;
import com.charmyin.hxxc.vo.NewsExample;
@Service
public class NewsServiceImpl implements NewsService {

	@Resource
	NewsMapper newsMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return newsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(News record) {
		return newsMapper.insert(record);
	}

	@Override
	public int insertSelective(News record) {
		return newsMapper.insertSelective(record);
	}

	@Override
	public News selectByPrimaryKey(String id) {
		return newsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(News record) {
		return newsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(News record) {
		return newsMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<News> findAllNewsByExample(NewsExample example) {
		return newsMapper.findAllByExample(example);
	}

}
