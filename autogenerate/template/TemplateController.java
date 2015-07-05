package {{ config.packagePath }}.controller;

import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import {{ config.packagePath }}.service.{{ config.firstNameCapital }}Service;
import {{ config.packagePath }}.vo.{{ config.firstNameCapital }};

import com.charmyin.cmstudio.web.utils.ResponseUtil;
import com.charmyin.cmstudio.basic.pagination.page.Pagination;
import com.charmyin.cmstudio.basic.pagination.page.PaginationResultVO;
import com.charmyin.cmstudio.common.utils.UUIDGenerator;
import com.charmyin.hxxc.vo.{{ config.firstNameCapital }};
import com.charmyin.hxxc.vo.{{ config.firstNameCapital }}Example;
import com.charmyin.hxxc.vo.{{ config.firstNameCapital }}Example.Criteria;


@Controller
@RequestMapping("/{{ config.path }}/{{ config.name }}")
public class {{ config.firstNameCapital }}Controller {
	
 
	@Resource
	{{ config.firstNameCapital }}Service {{ config.name }}Service;
	

	@RequestMapping(value = "/index")
	public String index(){
		return "/{{ config.path }}/{{ config.name }}/index";
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
		{{ config.firstNameCapital }}Example ie = new {{ config.firstNameCapital }}Example();
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
			List<{{ config.firstNameCapital }}> list = {{ config.name }}Service.findAll{{ config.firstNameCapital }}ByExample(ie);
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
			{{ config.name }}Service.deleteByPrimaryKey(id);
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
	public String insertSelective({{ config.firstNameCapital }} record) {
		try {
			//生成主键：UUID
			String uuid = UUIDGenerator.generate();
			record.setId(uuid);
			{{ config.name }}Service.insertSelective(record);
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
	public String updateByPrimaryKeySelective({{ config.firstNameCapital }} record) {
		try {
			{{ config.name }}Service.updateByPrimaryKeySelective(record);	
			return ResponseUtil.getSuccessResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
	}
	
}
