package com.charmyin.hxxc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.hxxc.service.ItemService;
import com.charmyin.hxxc.vo.Item;

import com.charmyin.cmstudio.basic.authorize.vo.User;
import com.charmyin.cmstudio.basic.pagination.page.Pagination;
import com.charmyin.cmstudio.basic.pagination.page.PaginationResultVO;
import com.charmyin.cmstudio.common.utils.UUIDGenerator;
import com.charmyin.cmstudio.web.utils.ResponseUtil;
import com.charmyin.cmstudio.web.utils.UserSessionUtil;

@Controller
@RequestMapping("/hxxc/item")
public class ItemController {
	
 
	@Resource
	ItemService ItemService;
	

	@RequestMapping(value = "/index")
	public String index(){
		return "/hxxc/item/index";
	}
	
	/**
	 * 查找所有
	 * @param page
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/findAll")
	@ResponseBody
	public PaginationResultVO findAll(Pagination page){
		Item Item = new Item();
		Item.setPageVO(page);
		List<Item> list = ItemService.findAllItem(Item);
		PaginationResultVO prv = new PaginationResultVO();
		prv.setTotal(String.valueOf(Item.getPageVO().getTotalRows()));
		prv.setRows(list);
		return prv;
	}

	/**
	 * 按ID删除
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/deleteById")
	@ResponseBody
	public int deleteByPrimaryKey(String id) {
		return ItemService.deleteByPrimaryKey(id);
	}
	
	/**
	 * 保存
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String insertSelective(Item record) {
		try {
			//生成主键：UUID
			String uuid = UUIDGenerator.generate();
			record.setId(uuid);
			record.setCreateTimestamp(System.currentTimeMillis());
			record.setRecordStatus(0);
			ItemService.insertSelective(record);
			return ResponseUtil.getSuccessResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错");
		}
	}
	
	/**
	 * 更新
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String updateByPrimaryKeySelective(Item record) {
		try {
			record.setCreateTimestamp(System.currentTimeMillis());
			ItemService.updateByPrimaryKeySelective(record);	
			return ResponseUtil.getSuccessResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
	}
	
}
