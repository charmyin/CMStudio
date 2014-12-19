<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${application_name_cn}</title>


<cmstudio:htmlBase />

<!--Start importing the jquery files -->
<cmstudio:importJsCss name="jquery" version="${jquery_version}" />
<!--End import the jquery files -->
<!--Start importing the jeasyui files -->
<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}" />

</head>
<body>


	<table id="dg" title="7S检查标准" class="easyui-datagrid"
		url="7sInspectStandard/all" toolbar="#toolbar" pagination="true"
		rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th data-options="field:'id', hidden:true">id</th>
				
				<th field="typeName" width="50">评价分类</th>
				<th field="itemName" width="50">评价内容</th>
				<th data-options="field:'typeId', hidden:true">id</th>
				<th field="standardDesc" width="50">标准描述</th>
				<th field="remark" width="50">备注</th>
				<th field="creatorName" width="50">创建者</th>
				<th field="createTime" width="50">创建时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newUser()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editUser()">修改</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
			
			&nbsp;&nbsp;
			评价分类: <input class="easyui-combobox" 
			data-options="valueField:'id', textField:'name', panelHeight:'auto'" 
			style="width:180px" id="evaluateType">
       		评价内容: <input class="easyui-combobox"
       		data-options="valueField:'id', textField:'name', panelHeight:'auto'"
       		style="width:180px" id="evaluateItem">
        	<a href="javascript:searchResult();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 310px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post" novalidate>

			<div class="fitem">
				<label>评价分类:</label> <input id="typeId" class="easyui-combobox"
					name="typeId"
					data-options="valueField:'id', textField:'name', panelHeight:'auto'">
			</div>

			<div class="fitem">
				<label>评价内容:</label> <input id="itemId" class="easyui-combobox"
					name="itemId"
					data-options="valueField:'id', textField:'name', panelHeight:'auto'">
			</div>

			<div class="fitem">
				<label>标准描述:</label> <textarea name="standardDesc"
					class="easyui-validatebox" data-options="required:true,validType:'length[1,1024]'"></textarea>
			</div>


			<div class="fitem">
				<label>备注:</label>
				<textarea name="remark"></textarea>
			</div>

		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveUser()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>


	<script type="text/javascript">
	
 		$(function() {
			var _mkid = $('#typeId').combobox({
				url : '7sEvaluateType/getCombobox',
				editable : false,
				valueField : 'id',
				textField : 'name',
				onSelect : function(record) {
					_zhbid.combobox({
						disabled : false,
						url : '7sEvaluateItem/getOne?typeId=' + record.id,
						valueField : 'id',
						textField : 'name'
					}).combobox('clear');
				}
			});
			var _zhbid = $('#itemId').combobox({
				disabled : true,
				valueField : 'id',
				textField : 'name'
			});
		});
 		
 		
 		//工具栏 加载
 		$(function() {
			var _mkid = $('#evaluateType').combobox({
				url : '7sEvaluateType/getCombobox',
				editable : false,
				valueField : 'id',
				textField : 'name',
				onSelect : function(record) {
					_zhbid.combobox({
						disabled : false,
						url : '7sEvaluateItem/getOne?typeId=' + record.id,
						valueField : 'id',
						textField : 'name'
					}).combobox('clear');
				}
			});
			var _zhbid = $('#evaluateItem').combobox({
				disabled : true,
				valueField : 'id',
				textField : 'name'
			});
		});

 		//工具栏查询
 		function searchResult(){
			$("#dg").datagrid("load", {
				typeId: $('#evaluateType').datebox('getValue'),	
				itemId: $('#evaluateItem').datebox('getValue')
			});
			
		}
 		
 		
		var url;
		function newUser() {
			var _zhbid = $('#typeId').combobox({
				url : '7sEvaluateType/getCombobox'
			});

			var _zhbid = $('#itemId').combobox({
				disabled : true
			});

			$('#dlg').dialog('open').dialog('setTitle', '新建');
			$('#fm').form('clear');
			url = '7sInspectStandard/save';
		}
		function editUser() {

			var _zhbid = $('#typeId').combobox({
				url : '7sEvaluateType/getCombobox'
			});
			var _zhbid = $('#itemId').combobox({
				disabled : false
			});

			var row = $('#dg').datagrid('getSelected');
			var _zhbid = $('#itemId').combobox({
				url : '7sEvaluateItem/getOne?typeId=' + row.typeId,
				disabled : true,
				valueField : 'id',
				textField : 'name'
			});

			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '修改');
				$('#fm').form('load', row);
				url = '7sInspectStandard/update?id=' + row.id
						+ '&RECORD_STATUS=0';
			}
		}
		function saveUser() {

			$('#fm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.errorMsg) {
						$.messager.show({
							title : 'Error',
							msg : result.errorMsg
						});
					} else {
						$('#dlg').dialog('close'); // close the dialog
						$('#dg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('提示', '你确定删除吗?', function(r) {
					if (r) {
						//$('#dlg').dialog('open').dialog('setTitle','修改猫咪');
						$('#fm').form('load', row);
						url = '7sInspectStandard/update?id=' + row.id
								+ '&RECORD_STATUS=1';
						saveUser();
					}
				})

			}
		}
	</script>
	<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 90px;
}

.fitem textarea {
	width: 150px;
}
</style>
</body>
</html>

