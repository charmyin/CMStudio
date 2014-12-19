<%
/**
 * This is the Organization Manage page~ 
 * @author charmyin
 * @since 2013-8-19
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
		<cmstudio:htmlBase/>
		<link rel="shortcut icon" type="image/x-icon" href="resources/${icon_name}"/>
		<link rel="stylesheet" type="text/css" href="resources/css/basic/user/user.css" />
		<!--Start importing the jquery files -->
		<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
		<!--End import the jquery files -->
		<!--Start importing the jeasyui files -->
		<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
		<!--End importing the jeasyui files -->
		<!--Start importing the ztree files -->
		<cmstudio:importJsCss name="ztree" version="${ztree_version}"/>
		<!--End importing the ztree files -->
		<script type="text/javascript" src="resources/js/basic/user/user.js"></script>
	</head>
	<body>
		<div class="easyui-layout" fit="true" style="overflow:hidden;">
			<div data-options="region:'west', split:true" style="width:200px;">
				 <div class="ztree" id="div_allOrganization_tree"></div>
			</div>
			<div data-options="region:'center', split:true">
		 	    <table id="userGrid">
			    </table>
			    <div id="toolbar">
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newForm()">新建</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editForm()">修改</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroySelectedItems()">删除</a>
			    </div>
			    <div id="dlg" class="easyui-dialog" data-options="closed:'true',buttons:'#dlg-buttons', modal:true">
			      
			        <form id="fm" method="post" >
			            <div class="fitem">
			                <label>用户账号：</label>
			                <input name="loginId" id="input_loginId" class="easyui-validatebox" required="true">
			            </div>
			            <div class="fitem">
			                <label>用户昵称：</label>
			                <input name="name" class="easyui-validatebox" required="true">
			            </div>
			            <div class="fitem">
			                <label>所属群组：</label>
			                <input id="input_organizationName" class="easyui-validatebox" readonly>
			                <input type="hidden" name="organizationId" id="hidden_organizationId" class="easyui-validatebox" style="display:hidden;" >
			            </div>
			            <div class="fitem">
			                <label>角色：</label> 
			                <a id="btn_roles" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">选择</a>
			                <br/>
			                <input id="input_role" name="roles" type="hidden" class="easyui-validatebox" readonly>
			            </div>
			            <div class="fitem">
			                <label>性别：</label>
			                <select class="easyui-combobox" name="sex" id="sexCombox" data-options="panelHeight:'auto'" style="width:50px;">
							    <option value="0">男</option>
							    <option value="1">女</option>
							</select>
			            </div>
			            <div class="fitem">
			                <label>手机号码：</label>
			                <input name="cellPhone" class="easyui-validatebox">
			            </div>
			           
			            <div class="fitem" id="div_initPassphrase">
			                <label>重置密码:</label>
			                <input type="checkbox" name="initPassphrase">(111111)
			                <hr/>
			                <input type="hidden" name="passphrase" id="hidden_passphrase" class="easyui-validatebox" style="display:hidden;" >
			                <input type="hidden" name="email" id="hidden_email" class="easyui-validatebox" style="display:hidden;" >
			            </div>
			            <div class="fitem">
			                <label>备注：</label>
			                <textarea name="remark" placeholder="请输入备注..."></textarea>
			            </div>
			        </form>
			    </div>
			    <div id="dlg-buttons">
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveForm()">保存</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
				</div>
				<!-- 角色选择 -->
				<div id="role-dlg" class="easyui-dialog" data-options="closed:'true',buttons:'#role-dlg-buttons', modal:true">
			        <div style="height:400px;width:300px;" id="innerRoleChoose"></div>
			    </div>
			    <div id="role-dlg-buttons">
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRole()">保存</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#role-dlg').dialog('close')">取消</a>
				</div>
				
			</div>
		</div>
	</body>
</html>
