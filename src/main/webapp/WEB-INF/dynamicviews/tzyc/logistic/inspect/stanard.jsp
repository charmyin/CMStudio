<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>设备检查标准</title>
<cmstudio:htmlBase />
<!--Start importing the jquery files -->
<cmstudio:importJsCss name="jquery" version="${jquery_version}" />
<!--End import the jquery files -->
<!--Start importing the jeasyui files -->
<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}" />
<!--End importing the jeasyui files -->
</head>
<body>
	<table id="dg" title="物流巡检检查标准" class="easyui-datagrid"
		url="logistic/inspect/all" toolbar="#toolbar" pagination="true"
		rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="typeName" width="50">种类</th>
				<th field="inspectPlace" width="50">检查部位</th>
				<th field="inspectMethod" width="50">检查方法</th>
				<th field="inspectContent" width="50">检查内容</th>
				<th field="remark" width="50">备注</th>
				<th field="userName" width="50">创建人</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newObject()">新建</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editObject()">修改</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyObject()">删除</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 480px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post" novalidate>

			<div class="fitem">
				<label>检查对象种类</label> <input class="easyui-combobox" name="typeId"
					data-options="
					url: 'logistic/device/typeListcom',
					panelHeight:'auto',
					method: 'post',
					valueField:'id',
					editable:false,
					textField:'name'
					">
			</div>
			<div class="fitem">
				<label>检查部位</label> <input name="inspectPlace"
					class="easyui-validatebox"
					data-options="required:true,validType:'length[0,64]'">
			</div>
			<div class="fitem">
				<label>检查方法</label> <input name="inspectMethod"
					class="easyui-validatebox"
					data-options="required:true,validType:'length[0,64]'">
			</div>
			<div class="fitem">
				<label>检查内容</label> <input name="inspectContent"
					class="easyui-validatebox"
					data-options="required:true,validType:'length[0,1024]'">
			</div>
			<div class="fitem">
				<label>备注</label>
				<textarea rows="10" cols="15" name="remark"
					></textarea>
			</div>
		</form>
	</div>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveObject()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
	</div>
	<script type="text/javascript">
		var url;
		function newObject() {
			$('#dlg').dialog('open').dialog('setTitle', '新建');
			$('#fm').form('clear');
			url = 'logistic/inspect/save';
		}
		function editObject() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '修改');
				$('#fm').form('load', row);
				url = 'logistic/inspect/update?id=' + row.id;
			}
		}
		function saveObject() {
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
		function destroyObject() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('删除', '确定删除吗', function(r) {
					if (r) {
						$.post('logistic/inspect/delete', {
							id : row.id
						}, function(result) {
							if (result.suc) {
								$('#dg').datagrid('reload'); // reload the user data
							} else {
								$.messager.show({ // show error message
									title : 'Error',
									msg : result.errorMsg
								});
							}
						}, 'json');
					}
				});
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
	width: 80px;
}
</style>
</body>
</html>