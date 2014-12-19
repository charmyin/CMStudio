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
import com.charmyin.cmstudio.tzyc._7s.service._7sEvaluateTypeService;
import com.charmyin.cmstudio.tzyc._7s.vo._7sEvaluateType;
import com.charmyin.cmstudio.tzyc._7s.vo._7sEvaluateTypeExample;
import com.charmyin.cmstudio.web.utils.ResponseUtil;
import com.charmyin.cmstudio.web.utils.UserSessionUtil;

@Controller()
@RequestMapping("/7sEvaluateType")
public class _7sEvaluateTypeController {

 
	@Resource
	_7sEvaluateTypeService _7sEvaluateTypeService;
	
	@RequestMapping(value = "/index")
	public String index(){
		return "/tzyc/_7s/7sEvaluateType";
	}
	

	@RequestMapping(method=RequestMethod.POST, value="/all")
	@ResponseBody
	public PaginationResultVO findAll(Pagination page){
		_7sEvaluateType _7sEvaluateType = new _7sEvaluateType();
		_7sEvaluateType.setPageVO(page);
		
		//COID
		User userInfo = UserSessionUtil.getUserInSession();
		_7sEvaluateType.setCoId(userInfo.getCoId());
		
	 
		
		List<_7sEvaluateType> list = _7sEvaluateTypeService.findAll_7sEvaluateTypes(_7sEvaluateType);
		
		PaginationResultVO prv = new PaginationResultVO();
		prv.setTotal(String.valueOf(_7sEvaluateType.getPageVO().getTotalRows()));
		prv.setRows(list);
		return prv;
	}
	

	
	@RequestMapping(method=RequestMethod.POST, value="/getCombobox")
	@ResponseBody
	public List<_7sEvaluateType> getCombobox(){
		List<_7sEvaluateType> list = _7sEvaluateTypeService.getlist_7sEvaluateTypes();
		
		return list;
	}
	
	public int deleteByPrimaryKey(String id) {

		return _7sEvaluateTypeService.deleteByPrimaryKey(id);
	}
	
	
	
/*	@RequestMapping(method=RequestMethod.POST, value="/getlist")
    @ResponseBody
    public String getList(@RequestParam("exportfield[]") String[] exportfield , @RequestParam("exportheaders[]") String[] exportheaders , @RequestParam("title") String title , HttpServletRequest request){
        try {
            List<_7sEvaluateType> dataset = _7sEvaluateTypeService.getlist_7sEvaluateTypes();
            String filePathString = request.getSession().getServletContext().getRealPath("");
            OutputStream out = new FileOutputStream(filePathString+"\\resources\\download\\report.xls");
            ExportExcel4Lift<_7sEvaluateType> ex = new ExportExcel4Lift<_7sEvaluateType>();
            ex.exportExcel(title, exportheaders, dataset, out, "yyyy-MM-dd HH:mm:ss",exportfield);
            return ResponseUtil.getSuccessResultString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseUtil.getFailResultString("保存过程中出错！");
        }
    }*/
	
	
	
	public String insert(_7sEvaluateType record) {
		return "";
	}

	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String insertSelective(_7sEvaluateType record) {
		try{
			//生成主键：UUID
			String uuid = UUIDGenerator.generate();
			record.setCreateTimestamp(System.currentTimeMillis());
			record.setId(uuid);
			record.setRecordStatus(0);
			
			//COID
			User userInfo = UserSessionUtil.getUserInSession();
			record.setCoId(userInfo.getCoId());
			
		 
			
			//插入数据库
			_7sEvaluateTypeService.insertSelective(record);
	
			return ResponseUtil.getSuccessResultString();
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
	}

	public List<_7sEvaluateType> selectByExample(_7sEvaluateTypeExample example) {
		// TODO Auto-generated method stub
		return _7sEvaluateTypeService.selectByExample(example);
	}

	public _7sEvaluateType selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return _7sEvaluateTypeService.selectByPrimaryKey(id);
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String updateByPrimaryKeySelective(_7sEvaluateType record,@RequestParam("RECORD_STATUS") int RECORD_STATUS) {
		try{
			// TODO Auto-generated method stub
			
			record.setRecordStatus(RECORD_STATUS);
			User userInfo = UserSessionUtil.getUserInSession();
			record.setCreateTimestamp(System.currentTimeMillis());
		 
			_7sEvaluateTypeService.updateByPrimaryKeySelective(record);
			
			return ResponseUtil.getSuccessResultString();
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
	}
	
	public String updateByPrimaryKey(_7sEvaluateType record,@RequestParam("RECORD_STATUS") int RECORD_STATUS) {
		return "";
	}

}
