package com.charmyin.cmstudio.tzyc._7s.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.tzyc._7s.persistence._7sEvaluateItemMapper;
import com.charmyin.cmstudio.tzyc._7s.service._7sEvaluateItemService;
import com.charmyin.cmstudio.tzyc._7s.vo._7sEvaluateItem;
import com.charmyin.cmstudio.tzyc._7s.vo._7sEvaluateItemExample;
@Service
public class _7sEvaluateItemServiceImpl implements _7sEvaluateItemService {

	@Resource
	_7sEvaluateItemMapper _7sEvaluateItemMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		
		return _7sEvaluateItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(_7sEvaluateItem record) {
		// TODO Auto-generated method stub
		return _7sEvaluateItemMapper.insert(record);
	}

	@Override
	public int insertSelective(_7sEvaluateItem record) {
		// TODO Auto-generated method stub
		return _7sEvaluateItemMapper.insertSelective(record);
	}

	@Override
	public List<_7sEvaluateItem> selectByExample(_7sEvaluateItemExample example) {
		// TODO Auto-generated method stub
		return _7sEvaluateItemMapper.selectByExample(example);
	}

	@Override
	public _7sEvaluateItem selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return _7sEvaluateItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(_7sEvaluateItem record) {
		// TODO Auto-generated method stub
		return _7sEvaluateItemMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(_7sEvaluateItem record) {
		// TODO Auto-generated method stub
		return _7sEvaluateItemMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<_7sEvaluateItem> getList_7sEvaluateItem() {
		// TODO Auto-generated method stub
		return _7sEvaluateItemMapper.getList_7sEvaluateItem();
	}

	@Override
	public List<_7sEvaluateItem> selectByTypeId(String typeId) {
		// TODO Auto-generated method stub
		return _7sEvaluateItemMapper.selectByTypeId(typeId);
	}

	@Override
	public List<_7sEvaluateItem> findAll_7sEvaluateItem(_7sEvaluateItem record) {
		// TODO Auto-generated method stub
		return _7sEvaluateItemMapper.findAll_7sEvaluateItem(record);
	}

	

}
