package com.charmyin.hxxc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.charmyin.hxxc.vo.Item;
import com.charmyin.hxxc.vo.ItemExample;

@Service
public interface ItemService {
    
    int deleteByPrimaryKey(String id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
    
    List<Item> getListItem();
    
    List<Item> findAllItem(ItemExample record);
}