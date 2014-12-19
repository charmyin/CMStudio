package com.charmyin.cmstudio.tzyc._7s.persistence;

import com.charmyin.cmstudio.basic.initial.SQLMapper;
import com.charmyin.cmstudio.tzyc._7s.vo._7sEvaluateType;
import com.charmyin.cmstudio.tzyc._7s.vo._7sEvaluateTypeExample;

import java.util.List;

@SQLMapper
public interface _7sEvaluateTypeMapper {
	int deleteByPrimaryKey(String id);

	int insert(_7sEvaluateType record);

	int insertSelective(_7sEvaluateType record);

	List<_7sEvaluateType> selectByExample(_7sEvaluateTypeExample example);

	_7sEvaluateType selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(_7sEvaluateType record);

	int updateByPrimaryKey(_7sEvaluateType record);

	List<_7sEvaluateType> findAll_7sEvaluateTypes(
			_7sEvaluateType _7sEvaluateType);

	List<_7sEvaluateType> getList_7sEvaluateTypes();
	
	int deleteItem(String typeId);

}