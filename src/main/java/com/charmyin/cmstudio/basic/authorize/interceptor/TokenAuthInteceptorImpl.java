package com.charmyin.cmstudio.basic.authorize.interceptor;

import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.charmyin.cmstudio.basic.authorize.vo.Token;

public class TokenAuthInteceptorImpl implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		//对比token，如果已经认证，继续，否则返回false并返回验证失败数据
		
		boolean isExisted=false;
		//如果token不为空
		String tokenStr = req.getParameter("token");
		if(tokenStr!=null && !tokenStr.trim().equals("") ){
			Object obj = req.getServletContext().getAttribute("userTokenMap");
			if(obj!=null){
				Hashtable<String, Token> map = (Hashtable<String, Token>)obj;
				Object tokenObj = map.get(tokenStr);
				if(tokenObj!=null){
					//更新时间
					Token token = (Token)tokenObj;
					token.setLastActiveTime(new Date());
					
					isExisted = true;
					//计算时间差
					 
				/*	long diff = new Date().getTime() - token.getLastActiveTime().getTime();
				    long minutes = diff / (1000 * 60 * 60 );*/
					
				};
			}
		}
		
		//req.getServletContext().getAttribute("")
		if(!isExisted){
			res.setContentType("text/json; charset=UTF-8");  
			res.getWriter().write("{\"status\":\"0\", \"msg\":\"token失效，请重新登录\"}");
		}
		
		return isExisted;
	}

}
