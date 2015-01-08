package com.charmyin.cmstudio.basic.authorize.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

import com.charmyin.cmstudio.basic.authorize.vo.Menu;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-context.xml"})
public class IdentityServiceT extends AbstractJUnit4SpringContextTests  {
	
	@Resource
	MenuService menuService;
	
	@Test
	public void testregisterIdentity(){
		List<Menu> menuList = menuService.getAllMenu();
		for(Menu menu : menuList){
			System.out.println(menu.getName());
		}
		assertEquals("hello", "hello");	 

		System.out.println("Successs!!!");
	}
 
}
