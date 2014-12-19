<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %> 

<!DOCTYPE html>
<html>
  <head>
    <title>${html_title}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="shortcut icon" type="image/x-icon" href="resources/${icon_name}"/>
    <cmstudio:htmlBase/>
	<link rel="stylesheet" type="text/css" href="resources/css/basic/index.css" />
	<!--Start importing the jquery files -->
	<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
	<!--End import the jquery files -->
	<!--Start importing the jeasyui files -->
	<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
	<!--End importing the jeasyui files -->
	<!--Start importing the ztree files -->
	<cmstudio:importJsCss name="ztree" version="${ztree_version}"/>
	<!--End importing the ztree files -->
	<script type="text/javascript" src="resources/js/basic/index.js"></script>
  </head>
  
  <body style="overflow:hidden;" id="bodyIndexMain" >
  	  <div style="position:absolute;width:100%; text-align:right;margin:0 0 0 0;">
     		<span style="height:20px;width:100%;position:relative;top:30px; right:200px;z-index:2">欢迎您:&nbsp;${userInfo.name}(${userInfo.loginId})</span>
     		<span style="height:20px;width:100%;position:relative;top:30px;"></span>
      </div>
      <div class="easyui-layout" id="divLayout_Main" data-options="fit:true" style="overflow:hidden;">
      	<!-- <div region='north' title="Zebone 前端集成开发平台(EasyUI)" style="width:100%; height:100px;background:blue;"> -->
      	<div region='north' style="width:100%; height:75px;" id="div_index_head_inner">
      		<div id="div_index_head">   		 
      			
	      			<div style="float:right; padding:29px 48px 0 0; font-size:16px; font-weight:bold; ">
	      				<!-- <a href="" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-sum'">桌面版</a> -->
	      				 <a href="javascript:void(0)" onclick="modifyPassword()" style="color:black;">修改密码</a>
	      				 <a href="javascript:void(0)" onclick="logOut()"  style="color:black;">退出</a>
	      				<!--  <a href="" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-sum'">退出</a> -->
	      				<!-- <a id="aMenubutton_Main" href="#" class="easyui-menubutton" data-options="menu:'#mm1', iconCls:'icon-tip'" >切换主题</a> -->
	      				<!-- <a href="#" class="easyui-menubutton" data-options="menu:'#mm2', iconCls:'icon-help'">用户管理</a>
	      				<a href="#" class="easyui-linkbutton" id="logout" data-options="plain:true, iconCls:'icon-no'">退出</a> -->
	      			</div>
	      			<!-- <div id="mm1" style="width:150px;">
	      				<div data-options="iconCls: 'icon-undo'" class="divOnChangeTheme" value="default">Default</div>
	      				<div data-options="iconCls: 'icon-redo'" class="divOnChangeTheme" value="bootstrap">Bootstrap</div>
	      			</div> -->
	      			<!-- <div id="mm2">
	      				<div data-options="iconCls: 'icon-edit'" onclick="modifyPassword();">修改密码</div>
	      				<div data-options="iconCls: 'icon-save'" onclick="alert($(this).html())">资料管理</div>
	      			</div> -->
      		 
      		</div>
		</div>
      	<div region="west" split="true" id="divRegionWest_Main" title="导航栏" style='width:280px; height:auto;'>
      		<div class="easyui-accordion" id="divAccordion_main" data-options="fit:true">
      			<div title="系统功能" id="divSystemManage_main" data-option="iconCls:'icon-ok'" style="width:100%; overflow-x:hidden; overflow-y:auto;">
						<div id="divSystemManage_main_tree" class="ztree"></div>
				</div>
				<div id="div_developTool" title="开发人员工具"  data-option="iconCls:'icon-help'">
					 <div id="divDevelopTool_tree" class="ztree"></div>
				</div>
				<div id="div_moduleSystem" title="模版子系统" data-option="iconCls:'icon-search'" >
					<div id="moduleSystem_tree" class="ztree"></div>
				</div>
      		</div>
      	</div>
      	<div region="center" split="true" style="width:auto;">
      		<div id="divTab_Main" class="easyui-tabs"  data-options="fit:true" style="">
      			<div title="欢迎使用本平台" style="margin-top:150px;">
      				<h2 style="color:#0099FF; text-align:center;font-size:50px;">欢迎使用泰州烟草现场综合管理平台!</h2>
					
      			</div>
      		</div>
      	</div>
      	<div region="south" style="width:100%; text-align: center; padding: 0; margin:0; line-height:23px; overflow:hidden;">
      		Powered by  ${developer_name}© ${company_poweredyear}  <%-- &nbsp;<a href="http://www.zebone.cn" style="text-decoration: none;" target="_blank">${developer_website}</a> --%>
      	</div>
      	
      	<!--等待界面-->
		  <div id='divLoading_Main'><span>登录成功~</span></div>
		  
		  <!-- Modify user password -->
		  <div id="modifyPassword" style="display:hidden;">
		  		<form id="mp_form" method="post">
				    <div>
				        <label for="name">原密码:</label>
				        <input class="easyui-validatebox" type="password" id="oldPW" name="oldPW" data-options="required:true, validType:'length[6,50]'" />
				    </div>
				    <div>
				        <label for="email">新密码:</label>
				        <input class="easyui-validatebox" type="password" id="newPW" name="newPW" data-options="required:true, validType:'length[6,50]'" />
				    </div>
				    <div>
				        <label for="email">确认新密码:</label>
				        <input class="easyui-validatebox" type="password" id="newPW1" name="newPW1" data-options="required:true, validType:'length[6,50]'" />
				    </div>
				</form>
		  </div>
		  <div id="mp_form_btns" style="display:hidden;">
		  	 <a href="javascript:savePassword();" data-options="iconCls: 'icon-save'" class="easyui-linkbutton">保存</a>
			 <a href="javascript:$('#modifyPassword').dialog('close');" data-options="iconCls: 'icon-cancel'" class="easyui-linkbutton">关闭</a>
		  </div>
      </div>
      
  </body>
</html>

