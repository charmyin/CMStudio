package com.charmyin.cmstudio.tzyc._7s.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.authorize.vo.User;
import com.charmyin.cmstudio.basic.pagination.page.Pagination;
import com.charmyin.cmstudio.basic.pagination.page.PaginationResultVO;
import com.charmyin.cmstudio.common.utils.UUIDGenerator;
import com.charmyin.cmstudio.tzyc._7s.service._7sEvaluateItemService;
import com.charmyin.cmstudio.tzyc._7s.vo._7sEvaluateItem;
import com.charmyin.cmstudio.tzyc._7s.vo._7sEvaluateItemExample;
import com.charmyin.cmstudio.web.utils.ResponseUtil;
import com.charmyin.cmstudio.web.utils.UserSessionUtil;

@Controller
@RequestMapping("/7sEvaluateItem")
public class _7sEvaluateItemController {
	
 
	@Resource
	_7sEvaluateItemService _7sEvaluateItemService;
	

	@RequestMapping(value = "/index")
	public String index(){
		return "/tzyc/_7s/7sEvaluateItem";
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/all")
	@ResponseBody
	public PaginationResultVO findAll(Pagination page){
		_7sEvaluateItem _7sEvaluateItem = new _7sEvaluateItem();
		_7sEvaluateItem.setPageVO(page);
		
		//COID
		User userInfo = UserSessionUtil.getUserInSession();
		_7sEvaluateItem.setCoId(userInfo.getCoId());
	 
		
		List<_7sEvaluateItem> list = _7sEvaluateItemService.findAll_7sEvaluateItem(_7sEvaluateItem);
		
		PaginationResultVO prv = new PaginationResultVO();
		prv.setTotal(String.valueOf(_7sEvaluateItem.getPageVO().getTotalRows()));
		prv.setRows(list);
		return prv;
	}
	
	
	
	
	
	public int deleteByPrimaryKey(String id) {

		return _7sEvaluateItemService.deleteByPrimaryKey(id);
	}
	

	public String insert(_7sEvaluateItem record) {
		return "";
		
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String insertSelective(_7sEvaluateItem record) {
		try {
			//生成主键：UUID
			String uuid = UUIDGenerator.generate();
			
			record.setId(uuid);
			record.setCreateTimestamp(System.currentTimeMillis());
			record.setRecordStatus(0);
			//COID
			User userInfo = UserSessionUtil.getUserInSession();
			
			record.setCoId(userInfo.getCoId());
			
			_7sEvaluateItemService.insertSelective(record);
			return ResponseUtil.getSuccessResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错");
		}
	}

	public List<_7sEvaluateItem> selectByExample(_7sEvaluateItemExample example) {
		// TODO Auto-generated method stub
		return _7sEvaluateItemService.selectByExample(example);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/getOne")
	@ResponseBody
	public List<_7sEvaluateItem>  selectByPrimaryKey(@RequestParam("typeId")String typeId) {
		try {
			return _7sEvaluateItemService.selectByTypeId(typeId);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String updateByPrimaryKeySelective(_7sEvaluateItem record,@RequestParam("RECORD_STATUS") int RECORD_STATUS) {
		// TODO Auto-generated method stub
				try {
					record.setRecordStatus(RECORD_STATUS);
					record.setCreateTimestamp(System.currentTimeMillis());
					_7sEvaluateItemService.updateByPrimaryKeySelective(record);	
					return ResponseUtil.getSuccessResultString();
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseUtil.getFailResultString("保存过程中出错！");
				}
	}
	
	public String updateByPrimaryKey(_7sEvaluateItem record) {
		
		return "";
	}

}
