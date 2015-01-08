package com.charmyin.cmstudio.basic.authorize.controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.charmyin.cmstudio.basic.authorize.service.IdentityService;
import com.charmyin.cmstudio.basic.authorize.service.UserService;
import com.charmyin.cmstudio.basic.authorize.vo.User;
import com.charmyin.cmstudio.basic.pagination.page.Page;
import com.charmyin.cmstudio.basic.pagination.page.Pagination;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	  @Autowired(required = true)
	  IdentityService identityService;

	  @Resource
	  private UserService userService;
	  
	  /**
	   * Does some simple work to find the current shiro subject gets a list of
	   * services, and the date.
	   */
	  @RequestMapping(method = RequestMethod.GET, value = { "/index" })
	  public String home(Locale locale, Model model, HttpServletRequest request) {
	    logger.info("Welcome home! the client locale is " + locale.toString());
	    
	   /* User find = new User();
    	//find.setName("i");
        Page paginationSupport = new Pagination();
        paginationSupport.setCurrentPage(2);
        paginationSupport.setPageSize(2);
        find.setPageVO(paginationSupport);
        PageContext page = PageContext.getPageContext();
        page.setCurrentPage(1);
        page.setPageSize(2);
        List<User> list = userService.findAllUser(find);
        System.out.println(list.size());
        System.out.println(paginationSupport.getTotalPages());
        System.out.println(paginationSupport.getTotalRows());
        for(User user : list)
        System.out.println(user.getName());*/
	    
	    return "basic/index";
	  }
	
}
