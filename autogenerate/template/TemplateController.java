package {{ config.packagePath }}.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import {{ config.packagePath }}.service.{{ config.firstNameCapital }}Service;
import {{ config.packagePath }}.vo.{{ config.firstNameCapital }};

import com.charmyin.cmstudio.basic.pagination.page.Pagination;
import com.charmyin.cmstudio.basic.pagination.page.PaginationResultVO;
import com.charmyin.cmstudio.common.utils.UUIDGenerator;
import com.charmyin.cmstudio.web.utils.ResponseUtil;


@Controller
@RequestMapping("/{{ config.path }}/{{ config.name }}")
public class {{ config.firstNameCapital }}Controller {
	
 
	@Resource
	{{ config.firstNameCapital }}Service {{ config.firstNameCapital }}Service;
	

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
	public PaginationResultVO findAll(Pagination page){
		{{ config.firstNameCapital }} {{ config.firstNameCapital }} = new {{ config.firstNameCapital }}();
		{{ config.firstNameCapital }}.setPageVO(page);
		List<{{ config.firstNameCapital }}> list = {{ config.firstNameCapital }}Service.findAll{{ config.firstNameCapital }}({{ config.firstNameCapital }});
		PaginationResultVO prv = new PaginationResultVO();
		prv.setTotal(String.valueOf({{ config.firstNameCapital }}.getPageVO().getTotalRows()));
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
		return {{ config.firstNameCapital }}Service.deleteByPrimaryKey(id);
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
			record.setCreateTimestamp(System.currentTimeMillis());
			record.setRecordStatus(0);
			{{ config.firstNameCapital }}Service.insertSelective(record);
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
			record.setCreateTimestamp(System.currentTimeMillis());
			{{ config.firstNameCapital }}Service.updateByPrimaryKeySelective(record);	
			return ResponseUtil.getSuccessResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
	}
	
}
