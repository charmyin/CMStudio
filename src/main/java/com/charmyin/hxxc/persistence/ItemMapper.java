package com.charmyin.hxxc.persistence;

import java.util.List;

import com.charmyin.cmstudio.basic.initial.SQLMapper;
import com.charmyin.hxxc.vo.Item;
import com.charmyin.hxxc.vo.ItemExample;

@SQLMapper
public interface ItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(Item record);

    int insertSelective(Item record);

    List<Item> selectByExample(ItemExample example);

    Item selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
}