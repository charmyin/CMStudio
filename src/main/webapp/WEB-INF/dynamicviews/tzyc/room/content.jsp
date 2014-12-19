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
	<table id="dg" title="机房检查內容管理" class="easyui-datagrid"
		 url="room/contentAllWidthPagination"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
			<th field="itemName" width="100">检查对象</th>
			<th field="content" width="100">检查内容</th>
			<th field="remark" width="200">备注</th>
			</tr>
		</thead>
	</table>

	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newRoomInspectContent()">新建</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editRoomInspectContent()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyRoomInspectContent()">删除</a>
					
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="length()">导出</a> -->
	
	</div>

	<div id="dlg" class="easyui-dialog" style="width:380px; height:250px; padding: 10px 20px"
	 closed="true" buttons="#dlg-buttons">
	
		<form id="fm" method="post" novalidate>
		  <div class="fitem">
                <label>检查对象</label>
                	<input class="easyui-combobox" name="itemId"  data-options="
					url: 'room/itemListcom',
					panelHeight:'auto',
					method: 'post',
					valueField:'id',
						editable:false,
					textField:'name'
					">
            </div>
			<div class="fitem">
				<label>检查内容:</label> <input name="content" class="easyui-validatebox" data-options="required:true,validType:'length[1,30]'">
			</div>
			<div class="fitem">
				<label>备注:</label>
				<textarea name="remark"></textarea>
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveRoomInspectContent()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	<script type="text/javascript">
	/* var exportfield = [];
	var exportheaders = [];
	function length() {
		var length = $("#dg").datagrid('options').columns[0].length;
		for (var j = 0; j < length; j++) {
			var obj = $("#dg").datagrid('options').columns[0][j];
			if (!obj.hidden) {
				exportfield.push(obj.field);
				exportheaders.push(obj.title);
			}
		}
		var title = $("#dg").datagrid('options').title;
		$.post('/tzyc/room/exportExcel', {
			exportfield : exportfield,
			exportheaders : exportheaders,
			startDate : "2014-01-01",
			endDate : "2014-09-01",
			title : title
		}, function(result) {
			var result = eval('(' + result + ')');
			if (result.suc) {
				location.href = $("base").attr("href")
						+ "resources/download/report.xls";
			} else {
				$.messager.show({ // show error message
					title : '错误',
					msg : result.errorMsg
				});
			}
		});
	} */
	
		var url;
		function newRoomInspectContent() {
			$('#dlg').dialog('open').dialog('setTitle', '新建');
			$('#fm').form('clear');
			url = '/tzyc/room/contentSave';
		}
		function editRoomInspectContent() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '修改');
				$('#fm').form('load', row);
				url = '/tzyc/room/contentUpdate?id=' + row.id;
			}
		}
		function saveRoomInspectContent() {
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
		 function destroyRoomInspectContent(){
	            var row = $('#dg').datagrid('getSelected');
	            if (row){
	                $.messager.confirm('删除','确定删除该条记录?',function(r){
	                    if (r){
	                        $.post('/tzyc/room/contentDelete',{id:row.id}, function(result){
	                        	var resultJson =  eval('(' + result + ')');;
	                            if (resultJson.suc){
	                                $('#dg').datagrid('reload');    // reload the Object data
	                            } else {
	                                $.messager.show({    // show error message
	                                    title: 'Error',
	                                    msg: resultJson.errorMsg
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