package com.charmyin.cmstudio.basic.tags;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * This tag used to generate the base tag of html file , 
 * for the purpose of managing the relative file path easily. 
 * i.e <base href='http://localhost:8080/cmstudio/'/>
 * @author charmyin
 * @since 2013-7-19
 * @serial 1.0
 */
public class HtmlBaseTag extends TagSupport {

	Properties props = new Properties();
	
	/*
	 * Wtrite the <base href=""/> tag to jsp
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		//Get full scheme serverName port and contextPath
		HttpServletRequest httpServletRequest = (HttpServletRequest)this.pageContext.getRequest();
		String scheme = httpServletRequest.getScheme();
		String serverName = httpServletRequest.getServerName();
		int serverPort = httpServletRequest.getServerPort();
		//int localPort = httpServletRequest.getLocalPort();
		String contextPath = httpServletRequest.getContextPath();
		
		//String ipAndPort =  httpServletRequest.getServletContext().getAttribute("IpAndPort").toString();
		
		// props.load(new FileInputStream(new TestProperties().getClassDirectory()+"test.properties"));
		
		try {
			this.pageContext.getOut().print("<base href='"+scheme+"://" + serverName + ":" + serverPort + contextPath + "/'/>");
			//this.pageContext.getOut().print("<base href='"+scheme+"://"+ipAndPort + contextPath + "/'/>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	@Override
	public int doEndTag() throws JspException {
		
		return super.doEndTag();
	}
	
}
