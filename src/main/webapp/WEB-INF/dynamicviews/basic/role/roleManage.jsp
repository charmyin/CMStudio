<%
/**
 * This is the Role Manage page~ 
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
		<link rel="stylesheet" type="text/css" href="resources/css/basic/role/role.css" />
		<!--Start importing the jquery files -->
		<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
		<!--End import the jquery files -->
		<!--Start importing the jeasyui files -->
		<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
		<!--End importing the jeasyui files -->
		<!--Start importing the ztree files -->
		<cmstudio:importJsCss name="ztree" version="${ztree_version}"/>
		<!--End importing the ztree files -->
		<script type="text/javascript" src="resources/js/basic/role/role.js"></script>
		<!-- <script type="text/javascript" src="resources/js/basic/role/rolePermission.js"></script> -->
	</head>
	<body>
		<div class="easyui-layout" fit="true" style="overflow:hidden;">
			 
			<div data-options="region:'center', split:true">
		 	    <table id="roleGrid">
			    </table>
			    <div id="toolbar">
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newForm()">新建</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editForm()">修改</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroySelectedItems()">删除</a>
			    </div>
			    <div id="dlg" class="easyui-dialog" data-options="closed:'true',buttons:'#dlg-buttons', modal:true">
			        <div class="ftitle">角色信息</div>
			        <form id="fm" method="post" >
			            <div class="fitem">
			                <label>名称：</label>
			                <input name="name" id="input_name" class="easyui-validatebox" required="true">
			            </div>
			           <!--  <div class="fitem">
			                <label>所属组织：</label>
			                <input type="hidden" name="organizationId" id="hidden_organizationId" class="easyui-validatebox" style="display:hidden;" >
			                <input id="input_organizationId" class="easyui-validatebox" readonly>
			            </div> -->
			            <div class="fitem">
			                <label>描述:</label>
			                <input name="description" class="easyui-validatebox">
			            </div>
			            <div class="fitem">
			                <label>菜单:</label>
			                <a id="btn_menus" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">选择</a>
			                <input id="input_menu" name="menu" class="easyui-validatebox" type="hidden">
			            </div>
			            <!-- <div class="fitem">
			                <input type="hidden" name="permission" id="hidden_permission" class="easyui-validatebox" style="display:hidden;" >
			            	<label>所需权限：</label>
			            	<div style="height:150px;overflow:auto;">
				                 <textarea name="fullPermission" class="easyui-validatebox" placeholder="权限需用逗号','分开..."></textarea>
				                <table id="permissionGrid"></table>
				                <div id="permissionGridTB" style="height:auto">
							        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>
							        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
							        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
							        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤销修改</a>
							    </div>
			            	</div>
			            </div> -->
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
				
				<!-- 菜单选择 -->
				<div id="menu-dlg" class="easyui-dialog" data-options="closed:'true',buttons:'#menu-dlg-buttons', modal:true">
			        <div style="height:400px;width:300px;" class="ztree" id="div_menu_tree"></div>
			    </div>
			    <div id="menu-dlg-buttons">
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveMenu()">保存</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#menu-dlg').dialog('close')">取消</a>
				</div>
			</div>
		</div>
	</body>
</html>
