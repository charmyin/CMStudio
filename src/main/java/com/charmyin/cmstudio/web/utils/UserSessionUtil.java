package com.charmyin.cmstudio.web.utils;

import com.charmyin.cmstudio.basic.authorize.vo.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.charmyin.cmstudio.basic.authorize.vo.User;

public class UserSessionUtil {
	public static User getUserInSession(){
		//获取session中的用户名称
				Subject currentUser = SecurityUtils.getSubject();
				Object userInfoObj = currentUser.getSession().getAttribute("userInfo");
				if(userInfoObj==null){
					return null;
				}
				//结合原有密码，验证用户有效性
				User userInfo = (User)userInfoObj;
				return userInfo;
	}
}
