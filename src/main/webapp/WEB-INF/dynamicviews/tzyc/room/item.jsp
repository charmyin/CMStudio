<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>机房</title>
<cmstudio:htmlBase />
<!--Start importing the jquery files -->
<cmstudio:importJsCss name="jquery" version="${jquery_version}" />
<!--End import the jquery files -->
<!--Start importing the jeasyui files -->
<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}" />
<!--End importing the jeasyui files -->
<!--Start importing the ztree files -->
<cmstudio:importJsCss name="ztree" version="${ztree_version}" />
<!--End importing the ztree files -->

</head>
<body>
	<table id="dg" title="机房检查对象管理" class="easyui-datagrid"
		data-options="method:'GET'" url="room//itemAllWidthPagination"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="code" width="50">检查对象编码</th>
				<th field="name" width="50">检查对象名称</th>
				<th field="remark" width="50">备注</th>
</tr>
		</thead>
	</table>

	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newRoomInspectItem()">新建</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editRoomInspectItem()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyRoomInspectItem()">删除</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		data-options="closed:'true',buttons:'#dlg-buttons', modal:true">
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>检查对象编码:</label> <input name="code" class="easyui-validatebox" data-options="required:true,validType:'length[1,30]'">
			</div>
			<div class="fitem">
				<label>检查对象名称:</label> <input name="name" class="easyui-validatebox" data-options="required:true,validType:'length[1,30]'">
			</div>
			<div class="fitem">
				<label>备注:</label>
				<textarea name="remark"></textarea>
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveRoomInspectItem()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	<script type="text/javascript">
		var url;
		function newRoomInspectItem() {
			$('#dlg').dialog('open').dialog('setTitle', '新建 ');
			$('#fm').form('clear');
			url = '/tzyc/room/itemSave';
		}
		function editRoomInspectItem() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '修改');
				$('#fm').form('load', row);
				url = '/tzyc/room/itemUpdate?id=' + row.id;
			}
		}
		function saveRoomInspectItem() {
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
		function destroyRoomInspectItem() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('删除', '确定删除该条记录?', function(r) {
					if (r) {
						$.post('/tzyc/room/itemDelete', {
							id : row.id
						}, function(result) {
							var resultJson = eval('(' + result + ')');
							;
							if (resultJson.suc) {
								$('#dg').datagrid('reload'); // reload the Object data
							} else {
								$.messager.show({ // show error message
									title : 'Error',
									msg : resultJson.errorMsg
								});
							}
						});
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