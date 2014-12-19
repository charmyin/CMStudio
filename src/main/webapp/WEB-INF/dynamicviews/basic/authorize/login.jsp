<%
/**
 * This is the login page~ Authorize you the power to go into this application~
 * @author charmyin
 * @since 2013-7-19
 * @serial 1.0
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>泰州烟草现场综合管理系统</title>
<!-- 指向http://xxx.xxx.xxx:xxxx/cmstudio/ -->
<cmstudio:htmlBase/>

<link rel="shortcut icon" type="image/x-icon" href="resources/${icon_name}"/>
<link rel="stylesheet" type="text/css" href="resources/css/basic/authorize/login.css" />

<!--Start importing the jquery files -->
<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
<!--End import the jquery files -->
<!--Start importing the jeasyui files -->
<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
<!--End importing the jeasyui files -->
<script type="text/javascript" src="resources/js/basic/authorize/login.js"></script>

<script type="text/javascript">

if (window.top !== window.self){
	try {
	    if (window.top.location.host) { // this is illegal to access unless you share a non-spoofable document domain
	    	window.top.location.reload();	
	    } else {
	 
	    	window.top.location.reload();	 // chrome executes this
	    }
	  } catch (ex) {
		 
		  window.top.location.reload();	 // everyone executes this
	  }
}
	
</script>
</head>
<body>
<form id="authForm" name="authForm" action="#" method="post">

<div class="loginArea">
	<div class="loginTopDiv">
		<img src="resources/css/basic/authorize/images/logo_03.png" style="position: relative; left: 20px; top: 10px;">	
		<span class="titleSpan">泰州烟草现场综合管理系统</span>
	</div>
	<div class="loginLeftDiv">
		<img class="img0" src="resources/css/basic/authorize/images/compute.png"></img>	
		<img class="img1" src="resources/css/basic/authorize/images/1_07.png"></img>	
		<img class="img2" src="resources/css/basic/authorize/images/2_07.png"></img>	
		<img class="img3" src="resources/css/basic/authorize/images/3_07.png"></img>	
		<img class="img4" src="resources/css/basic/authorize/images/4_07.png"></img>	
		<img class="img5" src="resources/css/basic/authorize/images/5_16.png"></img>	
		<img class="img6" src="resources/css/basic/authorize/images/6_26.png"></img>	
		<img class="img7" src="resources/css/basic/authorize/images/yun.png"></img>	
	</div>
	<div class="loginRightDiv">
		<label class="loginLabel">登录</label>
		<ul class="loginUl">
			<li>用户名:
				<input type="text" style="width: 200px; height: 32px; margin-left: 9px;" name="username" id="inputUsername" class="easyui-validatebox" data-options="required:true, validType:'length[1,15]'">
			</li>
			<li>密&nbsp&nbsp&nbsp码:
				<input type="password" style="height: 32px;margin-left: 10px;width: 200px;" name="password" id="inputPassword" class="easyui-validatebox" data-options="required:true, validType:'length[6,50]'">
			</li>
			<li>验证码:
				<input type="text" style="width:90px;height:24px;margin-left:9px;" name="validateCode"  id="inputValiCode" class="easyui-validatebox"  data-options="tipPosition:'left', required:true, validType:'length[4,5]'">
				<a href="javascript:changeValiImg()">
					<img style="height:30px;width:100px;position:relative;top:10px;" src="captcha/valiCode.jpg" id="imgValiCode"/>
				</a>
			</li>
			<li>	
				<a id="aSubmitForm" style="cursor:pointer;" >
					<img class="confirm" src="resources/css/basic/authorize/images/login_29.png" style="margin-right:10px">
				</a>
				<a id="aResetForm" style="cursor:pointer;">
					<img class="cancel" src="resources/css/basic/authorize/images/quxiao_31.png" style="margin-left:10px">
				</a>
			</li>
		</ul>	
	</div>
</div>
	
</form>




<div class = "copyRightDiv">
	<div class="crContentDiv">
		Copyright @ 2013 东软集团股份有限公司	
	</div>
</div>
</body>
</html>