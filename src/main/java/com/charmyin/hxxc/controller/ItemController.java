package com.charmyin.hxxc.controller;

import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.pagination.page.Pagination;
import com.charmyin.cmstudio.basic.pagination.page.PaginationResultVO;
import com.charmyin.cmstudio.common.utils.UUIDGenerator;
import com.charmyin.cmstudio.web.utils.ResponseUtil;
import com.charmyin.hxxc.service.ItemService;
import com.charmyin.hxxc.vo.Item;
import com.charmyin.hxxc.vo.ItemExample;
import com.charmyin.hxxc.vo.ItemExample.Criteria;


@Controller
@RequestMapping("/hxxc/item")
public class ItemController {
	
 
	@Resource
	ItemService itemService;
	

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
	public PaginationResultVO findAll(Pagination page, HttpServletRequest request){
		//Map<String, String[]> map = request.getParameterNames()
		Enumeration<String> enumera = request.getParameterNames();
		
		PaginationResultVO prv = new PaginationResultVO();
		 
		ItemExample ie = new ItemExample();
		ie.setPageVO(page);
		Criteria crit = ie.createCriteria();
		//crit.
		
		try {
			//Criteria.class.getMethod("andNameEqualTo", String.class).invoke(crit, "asdf");
			while(enumera.hasMoreElements()){
				String name = enumera.nextElement();
				if(name!=null&&name.startsWith("search_")){
					System.out.println(name);
					String expr = name.split("_")[1];
					Criteria.class.getMethod(expr, String.class).invoke(crit, request.getParameter(name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			prv.setSuccess("false");
			prv.setMsg(e.getMessage());
			return prv;
		} 
		//crit.andCodeEqualTo("66");
		List<Item> list = itemService.findAllItem(ie);
		prv.setTotal(String.valueOf(ie.getPageVO().getTotalRows()));
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
		return itemService.deleteByPrimaryKey(id);
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
			itemService.insertSelective(record);
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
			itemService.updateByPrimaryKeySelective(record);	
			return ResponseUtil.getSuccessResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
	}
	
}
