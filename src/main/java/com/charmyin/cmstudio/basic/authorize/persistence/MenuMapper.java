package com.charmyin.cmstudio.basic.authorize.persistence;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.charmyin.cmstudio.basic.authorize.vo.Menu;
import com.charmyin.cmstudio.basic.initial.SQLMapper;

/**
 * Mybatis Mapper Interface used for menu operation
 * @author charmyin
 *
 */
@SQLMapper
public interface MenuMapper {
	
	/**
	 * Get all menus from menu role 
	 * @return
	 */
	/*@RequiresPermissions("menu:getallxx")*/
	public List<Menu> getAllMenu();
	
	/**
	 * Get menu by the conditions contained by params "menu", it use "=" in where condition
	 * @param menu Menu instance which contains the question conditions.
	 * @return
	 */
	public List<Menu> getMenuEqual(Menu menu);
	
	/**
	 * Get menu by the conditions contained by params "menu", it use "like" in where condition
	 * @param menu Menu instance which contains the conditions.
	 * @return
	 */
	public List<Menu> getMenuLike(Menu menu);
	
	/**
	 * Get menu by menu id
	 * @param id
	 * @return
	 */
	public Menu getMenuById(int id);
	
	/**
	 * Insert a piece of menu to table
	 * @param menu
	 * @return
	 */
	public void insertMenu(Menu menu);
	
	/**
	 * Update menu by menu object which must contain id
	 * @param menu
	 */
	public void updateMenu(Menu menu);
	
	/**
	 * Delete menus by id string array
	 * @param ids
	 */
	public void deleteMenu(int id);
}
