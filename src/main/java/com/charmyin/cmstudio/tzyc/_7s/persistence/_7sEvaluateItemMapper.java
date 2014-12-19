package com.charmyin.cmstudio.tzyc._7s.persistence;

import com.charmyin.cmstudio.basic.initial.SQLMapper;
import com.charmyin.cmstudio.tzyc._7s.vo._7sEvaluateItem;
import com.charmyin.cmstudio.tzyc._7s.vo._7sEvaluateItemExample;

import java.util.List;
@SQLMapper
public interface _7sEvaluateItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(_7sEvaluateItem record);

    int insertSelective(_7sEvaluateItem record);

    List<_7sEvaluateItem> selectByExample(_7sEvaluateItemExample example);

    _7sEvaluateItem selectByPrimaryKey(String id);
    
    List<_7sEvaluateItem> selectByTypeId(String typeId);

    int updateByPrimaryKeySelective(_7sEvaluateItem record);

    int updateByPrimaryKey(_7sEvaluateItem record);
    
    List<_7sEvaluateItem> getList_7sEvaluateItem();
    
    List<_7sEvaluateItem> findAll_7sEvaluateItem(_7sEvaluateItem record);
}