package com.charmyin.cmstudio.basic.authorize.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.persistence.MenuMapper;
import com.charmyin.cmstudio.basic.authorize.service.MenuService;
import com.charmyin.cmstudio.basic.authorize.vo.Menu;

@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuMapper menuMapper ;
	
	@Override
	/*@RequiresPermissions("menu:getallxx")*/
	public List<Menu> getAllMenu() {
		List<Menu> menuList = menuMapper.getAllMenu();
		return menuList;
	}

	@Override
	public List<Menu> getChildrenMenus(int parentId) {
		Menu menu = new Menu();
		menu.setParentId(parentId);
		List<Menu> menuList = menuMapper.getMenuEqual(menu);
		return menuList;
	}
	
	@Override
	public void insertMenu(Menu menu){
		menuMapper.insertMenu(menu);
	}
	
	@Override
	public void updateMenu(Menu menu) {
		menuMapper.updateMenu(menu);
	}

	@Override
	public void deleteMenu(int[] ids) {
		for(int id : ids){
			menuMapper.deleteMenu(id);
		}
	}

	@Override
	public List<Menu> searchMenu(Menu menu) {
		List<Menu> menuList = menuMapper.getMenuEqual(menu);
		return menuList;
	}

	@Override
	public Menu getMenuById(int id) {
		Menu menu = menuMapper.getMenuById(id);
		return menu;
	}

	@Override
	public List<Menu> getMenuByRole() {
		
		return null;
	}

	@Override
	public List<Menu> getMenuByUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public MenuMapper getMenuMapper() {
		return menuMapper;
	}

	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}
   
}
