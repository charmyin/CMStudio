package com.charmyin.pagination;

import java.util.List;

import javax.annotation.Resource;

import com.charmyin.cmstudio.basic.authorize.service.UserService;
import com.charmyin.cmstudio.basic.authorize.vo.User;
import com.charmyin.cmstudio.basic.pagination.page.Page;
import com.charmyin.cmstudio.basic.pagination.page.Pagination;


public class PaginationT {

	//@Resource
	private UserService userService;
	
	public void testPagination(){
		User find = new User();
    	//find.setName("i");
        Page paginationSupport = new Pagination();
        paginationSupport.setCurrentPage(2);
        paginationSupport.setPageSize(2);
        find.setPageVO(paginationSupport);
      /*  PageContext page = PageContext.getPageContext();
        page.setCurrentPage(1);
        page.setPageSize(2);*/
        List<User> list = userService.findAllUser(find);
        System.out.println(list.size());
        System.out.println(paginationSupport.getTotalPages());
        System.out.println(paginationSupport.getTotalRows());
        for(User user : list)
        System.out.println(user.getName());
	}
	
}
