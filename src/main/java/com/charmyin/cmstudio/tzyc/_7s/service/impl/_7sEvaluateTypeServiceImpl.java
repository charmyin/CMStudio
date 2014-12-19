package com.charmyin.cmstudio.tzyc._7s.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.tzyc._7s.persistence._7sEvaluateTypeMapper;
import com.charmyin.cmstudio.tzyc._7s.service._7sEvaluateTypeService;
import com.charmyin.cmstudio.tzyc._7s.vo._7sEvaluateType;
import com.charmyin.cmstudio.tzyc._7s.vo._7sEvaluateTypeExample;
@Service
public class _7sEvaluateTypeServiceImpl implements _7sEvaluateTypeService {

	@Resource
	_7sEvaluateTypeMapper _7sEvaluateTypeMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return _7sEvaluateTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(_7sEvaluateType record) {
		// TODO Auto-generated method stub
		return _7sEvaluateTypeMapper.insert(record);
	}

	@Override
	public int insertSelective(_7sEvaluateType record) {
		// TODO Auto-generated method stub
		return _7sEvaluateTypeMapper.insertSelective(record);
	}

	@Override
	public List<_7sEvaluateType> selectByExample(_7sEvaluateTypeExample example) {
		// TODO Auto-generated method stub
		return _7sEvaluateTypeMapper.selectByExample(example);
	}

	@Override
	public _7sEvaluateType selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return _7sEvaluateTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(_7sEvaluateType record) {
		// TODO Auto-generated method stub
		return _7sEvaluateTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(_7sEvaluateType record) {
		// TODO Auto-generated method stub
		return _7sEvaluateTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<_7sEvaluateType> findAll_7sEvaluateTypes(_7sEvaluateType _7sEvaluateType) {
		// TODO Auto-generated method stub
		return _7sEvaluateTypeMapper.findAll_7sEvaluateTypes(_7sEvaluateType);
	}

	@Override
	public List<_7sEvaluateType> getlist_7sEvaluateTypes() {
		// TODO Auto-generated method stub
		return _7sEvaluateTypeMapper.getList_7sEvaluateTypes();
	}

	@Override
	public int deleteItem(String typeId) {
		// TODO Auto-generated method stub
		return _7sEvaluateTypeMapper.deleteItem(typeId);
	}

}
