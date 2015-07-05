package com.charmyin.hxxc.controller;

import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.hxxc.service.EmployerInfoService;
import com.charmyin.hxxc.vo.EmployerInfo;

import com.charmyin.cmstudio.web.utils.ResponseUtil;
import com.charmyin.cmstudio.basic.pagination.page.Pagination;
import com.charmyin.cmstudio.basic.pagination.page.PaginationResultVO;
import com.charmyin.cmstudio.common.utils.UUIDGenerator;
import com.charmyin.hxxc.vo.EmployerInfo;
import com.charmyin.hxxc.vo.EmployerInfoExample;
import com.charmyin.hxxc.vo.EmployerInfoExample.Criteria;


@Controller
@RequestMapping("/hxxc/employerInfo")
public class EmployerInfoController {
	
 
	@Resource
	EmployerInfoService employerInfoService;
	

	@RequestMapping(value = "/index")
	public String index(){
		return "/hxxc/employerInfo/index";
	}
	
	/**
	 * 查找所有
	 * @param page
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/findAll")
	@ResponseBody
	public PaginationResultVO findAll(Pagination page, HttpServletRequest request){
		PaginationResultVO prv = new PaginationResultVO();
		EmployerInfoExample ie = new EmployerInfoExample();
		try {
			Criteria crit = ie.createCriteria();
			Enumeration<String> enumera = request.getParameterNames();
			while(enumera.hasMoreElements()){
				String name = enumera.nextElement();
				if(name!=null&&name.startsWith("search_")){
					String expr = name.split("_")[1];
					Criteria.class.getMethod(expr, String.class).invoke(crit, request.getParameter(name));
				}
			}
			ie.setPageVO(page);
			List<EmployerInfo> list = employerInfoService.findAllEmployerInfoByExample(ie);
			prv.setTotal(String.valueOf(ie.getPageVO().getTotalRows()));
			prv.setSuccess("true");
			prv.setRows(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			prv.setSuccess("false");
			prv.setMsg(e.getMessage());
		}
		return prv;
		
	}

	/**
	 * 按ID删除
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/deleteById")
	@ResponseBody
	public String deleteByPrimaryKey(String id) {
		try {
			employerInfoService.deleteByPrimaryKey(id);
			return ResponseUtil.getSuccessResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
	}
	
	/**
	 * 保存
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String insertSelective(EmployerInfo record) {
		try {
			//生成主键：UUID
			String uuid = UUIDGenerator.generate();
			record.setId(uuid);
			employerInfoService.insertSelective(record);
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
	public String updateByPrimaryKeySelective(EmployerInfo record) {
		try {
			employerInfoService.updateByPrimaryKeySelective(record);	
			return ResponseUtil.getSuccessResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
	}
	
}
