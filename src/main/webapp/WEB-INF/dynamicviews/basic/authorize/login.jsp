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
<!DOCTYPE html>
<html>
	<head>
		<title> ${application_name_cn} </title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
		<script src="resources/vendor/cookiejs/cookie.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="resources/vendor/md5/md5.min.js"></script>		 
	</head>
	<body>
		<div id="loginwindow" class="easyui-window" title="${application_name_cn}平台入口" data-options="iconCls:'icon-tip',closable:false, minimizable:false, maximizable:false, resizable:false, shadow:true" >
			<div class="easyui-layout" fit="true" style="overflow:hidden;">
				<div region="west">
				</div>
				<div region="center">
					<div class="easyui-tabs" fit="true">
					 	<div title="身份认证" iconCls="icon-save">
					 			<div id="userconfirmDiv">
				 					<form id="authForm" name="authForm" action="#" method="post">
				 						<ul id="formUl">
				 							<li class="inputText">
				 								<label for="inputUsername">用户名:</label>
					 							<input class="easyui-validatebox" type="text" id="inputUsername" name="username" placeholder="用户名" data-options="required:true, validType:'length[1,15]'" autofocus/>
				 							</li>
				 							<li class="inputText">
				 								<label style="" for="inputPassword">密&nbsp;&nbsp;码:</label>
					 							<input class="easyui-validatebox" type="password" id="inputPassword" name="password" placeholder="密码" data-options="required:true, validType:'length[6,50]'" />
				 							</li>
				 							<li id="liValiCode">
				 								<label style="" for="inputValiCode">验证码:</label>
				 								<a href="javascript:changeValiImg()"><img src="captcha/valiCode.jpg" id="imgValiCode"/></a>
					 							<input class="easyui-validatebox" type="text" id="inputValiCode" name="validateCode" placeholder="验证码" data-options="required:true, validType:'length[4,5]'" />
				 							</li>
				 							<li class="liConfig">
				 								<div id="checkboxConfig">
											      <label>
											        <input type="checkbox" id="inputRememberMe" class="bootstrap"/> <strong>记住密码</strong>
											      </label>
											      <label>
											        <input type="checkbox" id="inputDesktop_login" class="bootstrap"/> <strong>桌面系统</strong>
											      </label>
											    </div>
				 							</li>
				 						</ul>
				 					</form>
					 				<div id="operationBtnsDiv" >
					 					<div style="height:6px;"></div>
					 					<a href="#" id="aSubmitForm" class="easyui-linkbutton" iconCls="icon-ok">提交</a>
					 					&nbsp;
					 					<a href="#" id="aResetForm" class="easyui-linkbutton" iconCls="icon-cancel">重置</a>
					 				</div>
					 			</div>
					 	</div>
					 	<div title="帮助提示" iconCls="icon-help">
					 		<div style="margin:70px 0 0 70px;" id="weather-temp">版权所有，翻版不究^_^</div>
					 	</div>
					</div>
				</div>
				<div region="south" style="height:30px;line-height:28px;text-align:center;">
					Powered by  ${company_name}© ${company_poweredyear}  &nbsp;<a href="http://www.charmyin.com" style="text-decoration: none;" target="_blank">${company_website}</a>
				</div>
			</div>
		</div>
		<!--等待界面-->
	<!-- <div id='divLoading'><span>首页载入中~</span></div> -->
	</body>
</html>
