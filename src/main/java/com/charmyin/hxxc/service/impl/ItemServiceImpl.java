package com.charmyin.hxxc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.hxxc.persistence.ItemMapper;
import com.charmyin.hxxc.service.ItemService;
import com.charmyin.hxxc.vo.Item;
import com.charmyin.hxxc.vo.ItemExample;
@Service
public class ItemServiceImpl implements ItemService {

	@Resource
	ItemMapper itemMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return itemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Item record) {
		return itemMapper.insert(record);
	}

	@Override
	public int insertSelective(Item record) {
		return itemMapper.insertSelective(record);
	}

	@Override
	public Item selectByPrimaryKey(String id) {
		return itemMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Item record) {
		return itemMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Item record) {
		return itemMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Item> getListItem() {
		ItemExample example = new ItemExample();
		return itemMapper.afindAllByExample(example);
	}

	@Override
	public List<Item> findAllItem(ItemExample record) {
		return itemMapper.afindAllByExample(record);
	}

}
