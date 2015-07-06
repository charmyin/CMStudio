package com.charmyin.cmstudio.basic.authorize.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.authorize.service.MenuService;
import com.charmyin.cmstudio.basic.authorize.vo.Menu;
import com.charmyin.cmstudio.common.utils.ArrayUtil;
import com.charmyin.cmstudio.common.utils.JSRErrorUtil;
import com.charmyin.cmstudio.web.utils.ResponseUtil;

/**
 * Users' menu operation.
 * @author charmyin
 * @since 2013-8-12
 */
@Controller
public class MenuController {
	
	private static Logger logger = Logger.getLogger(MenuController.class);
	
	@Resource
	MenuService menuService;

	/**
	 * Direct to the menuManage jsp file
	 * @return jsp file path
	 */
	@RequestMapping(value="/menu/manage")
	public String manageMenu(){
		return "basic/menu/menuManage";
	}	
	
	/**
	 * Get all menu items in database
	 * @return all menu items JSON type data
	 */
	@RequestMapping("/menu/all")
	@ResponseBody
	public List<Menu> getAllMenu(){
		List<Menu> menuList = menuService.getAllMenu();
		return menuList;
	}
	
	/**
	 * Get user menus by their shiro session subject
	 * @return Menu list in json
	 */
	 @SuppressWarnings("unchecked")
	 @RequestMapping("/menu/user")
	 @ResponseBody
	 public List<Menu> getMenusByUserSessionSubject(){
		 Subject currentUser = SecurityUtils.getSubject();
		 Object menuListObj =  currentUser.getSession().getAttribute("menuList");
		// Object menuListObj = userInitService.getMenusByLoginId(loginForm.getUsername());
		 List<Menu> menuList=null;
		 if(menuListObj!=null){
			 menuList = (List<Menu>)menuListObj;
		 }
		 return menuList;
	 }
	
	//TODO The parentId parse exception has not been handled~
	/**
	 * Get child menu under one parent menu (Not include all the children and grand children, just the next level children)
	 * @param parentId 
	 * @return Menu items' JSON type data under the parent;  
	 */
	@RequestMapping(value="/menuparent/{parentId}/menu", method=RequestMethod.GET)
	@ResponseBody
	public List<Menu> getMenuByParent(@PathVariable int parentId){
		List<Menu> menuList = menuService.getChildrenMenus(parentId);
		return menuList;
	}
	
	/**
	 * Insert the menu committed from client
	 * @return 
	 */
	@RequestMapping(value="/menu/save", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveMenu(HttpServletRequest request,HttpServletResponse response, @Valid Menu menu, BindingResult result){
		
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		
		try{
			menuService.insertMenu(menu);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
		return ResponseUtil.getSuccessResultString();
	}
	
	/**
	 * Update menu
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/menu/update", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String updateMenu(@Valid Menu menu, BindingResult result){
		
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		
		try{
			menuService.updateMenu(menu);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("更新过程中出错！");
		}
		return ResponseUtil.getSuccessResultString();
	}
	
	/**
	 * Delete by ids string split by ',' ; Example:"1,2,3,4,5" 
	 * @param ids eg."1,2,3,4,5"
	 * @return
	 */
	@RequestMapping(value="/menu/deleteByIds", method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	public Map<String, Object> deleteMenuByIds(@RequestParam("ids") String ids){
		//ids can not be null
		if(ids==null || ids.isEmpty()){
			Map<String, Object> map = ResponseUtil.getFailResultMap();
			map.put("errorMsg", "删除数据，id不允许为空！");
			return map;
		}
		
		String[] idsArrayNotEmpty = ArrayUtil.removeEmptyString(ids.split(","));
		int[] idsIntArray = new int[idsArrayNotEmpty.length];
		try{
			for(int i=0; i<idsArrayNotEmpty.length;i++){
				int idInt = Integer.parseInt(idsArrayNotEmpty[i]);
				idsIntArray[i] = idInt;
			}
			menuService.deleteMenu(idsIntArray);
		}catch(NumberFormatException ne){
			logger.error("提交id值错误!"+ne.getMessage());
			Map<String, Object> map = ResponseUtil.getFailResultMap();
			map.put("errorMsg", "提交id值错误!");
			return map;
		}catch(Exception e){
			logger.error(e.getMessage());
			Map<String, Object> map = ResponseUtil.getFailResultMap();
			map.put("errorMsg", "删除过程中出错！");
			return map;
		}
		
		return ResponseUtil.getSuccessResultMap();
	}
	
	
	public MenuService getMenuService() {
		return menuService;
	}
	
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
}
