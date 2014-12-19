<%
/**
 * This is the Menu Manage page~ 
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
		<link rel="stylesheet" type="text/css" href="resources/css/basic/menu/menu.css" />
		<!--Start importing the jquery files -->
		<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
		<!--End import the jquery files -->
		<!--Start importing the jeasyui files -->
		<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
		<!--End importing the jeasyui files -->
		<!--Start importing the ztree files -->
		<cmstudio:importJsCss name="ztree" version="${ztree_version}"/>
		<!--End importing the ztree files -->
		<script type="text/javascript" src="resources/js/basic/menu/menu.js"></script>
		<!-- <script type="text/javascript" src="resources/js/basic/menu/menuPermission.js"></script> -->
	</head>
	<body>
		<div class="easyui-layout" fit="true" style="overflow:hidden;">
			<div data-options="region:'west', split:true" style="width:200px;">
				 <div class="ztree" id="div_allMenu_tree"></div>
			</div>
			<div data-options="region:'center', split:true">
		 	    <table id="menuGrid">
			    </table>
			    <div id="toolbar">
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newMenu()">新建</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editMenu()">修改</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyMenu()">删除</a>
			    </div>
			    <div id="dlg" class="easyui-dialog" data-options="closed:'true', buttons:'#dlg-buttons', modal:true">
			        <div class="ftitle">菜单信息</div>
			        <form id="fm" method="post" >
			            <div class="fitem">
			                <label>名称：</label>
			                <input name="name" class="easyui-validatebox" required="true">
			            </div>
			            <div class="fitem">
			                <label>父级菜单：</label>
			                <input type="hidden" name="parentId" id="hidden_parentId" class="easyui-validatebox" style="display:hidden;" >
			                <input id="input_parentId" class="easyui-validatebox" style="width:86px;" readonly>
			                <a id="btn_parentId" href="javascript:void(0);" class="easyui-linkbutton">选择</a>
			            </div>
			            <div class="fitem">
			                <label>链接地址：</label>
			                <input name="linkUrl" class="easyui-validatebox">
			            </div>
			           <!--  <div class="fitem">
			            	<input type="hidden" name="fullPermission" id="hidden_FullPermission" class="easyui-validatebox" style="display:hidden;" >
			            	<label>所需权限：</label>
			            	<div style="height:200px;overflow:auto;">
				                 <textarea name="fullPermission" class="easyui-validatebox" placeholder="权限需用逗号','分开..."></textarea>
				                <table id="menuPermissionGrid"></table>
				                <div id="menuPermissionGridTB" style="height:auto">
							        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>
							        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
							        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
							        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤销修改</a>
							    </div>
			            	</div>
			            </div> -->
			            <div class="fitem">
			                <label>排序号</label>
			                <input name="orderNumber" class="easyui-numberbox">
			            </div>
			            <div class="fitem">
			                <label>备注：</label>
			                <textarea name="remark" required="true" placeholder="请输入备注..."></textarea>
			            </div>
			        </form>
			    </div>
			    <div id="dlg-buttons">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveMenu()">保存</a>
				    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
				</div>
			</div>
		</div>
		<!-- 选择树 -->
		<div id="div_winSelectParentMenu" class="easyui-dialog" style="width:300px;height:400px" data-options="iconCls:'icon-ok',buttons:'#div_winSelectParentMenu_btn',closed:true,minimizable:false, modal:true">
		    <div class="ztree" id="div_SelectSingleMenu_tree"></div>
		</div>
		<div id="div_winSelectParentMenu_btn">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick='selectTreeNodeSure();'>确定</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="$('#div_winSelectParentMenu').dialog('close');">取消</a>
		</div>
	</body>
</html>
