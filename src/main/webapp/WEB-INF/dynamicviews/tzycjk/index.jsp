<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index</title>


<cmstudio:htmlBase />

<!--Start importing the jquery files -->
<cmstudio:importJsCss name="jquery" version="${jquery_version}" />
<!--End import the jquery files -->
<!--Start importing the jeasyui files -->
<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}" />

</head>
<body>
	<div style="margin: 20px 0;"></div>
	
	<!-- 请注意toolbar属性   该属性是指定表格工具栏的属性 -->
	
	<table id="dg" title="7s检查评价内容" class="easyui-datagrid"
		url="7sEvaluateItem/all" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true" toolbar="#tb">
		<thead>
			<tr>
				<th data-options="field:'id', hidden:true">id</th>
				<th field="code" width="50">评价项目编号</th>
				<th field="name" width="50">评价项目名称</th>
				<th field="typeName" width="50">7S检查评价分类</th>
				<th field="remark" width="50">描述</th>
			</tr>
		</thead>
	</table>
	
	<!-- 此处是工具栏的div -->
	<div id="tb" style="padding: 5px; height: auto">
		<div>
			开始时间: <input id="startDate" class="easyui-datetimebox" style="width: 150px" data-options="editable:false"> 
			结束时间: <input id="endDate" class="easyui-datetimebox" style="width: 150px" data-options="editable:false"> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="select()">查询</a>
		</div>
	</div>


	<script type="text/javascript">
		function select() {
			//取到开始,结束时间
			var startDate = $("#startDate").datebox("getValue");  
			var endDate = $("#endDate").datebox("getValue");
			
			//格式化开始,结束时间
			var start=new Date(startDate.replace("-", "/").replace("-", "/"));  
			var end=new Date(endDate.replace("-", "/").replace("-", "/"));  
			
			//判断日期非空
			if((startDate!="" && endDate != "")||(startDate == "" && endDate == "")){
				//结束时间应晚于开始时间
				if(end<start){
					alert("结束时间应晚于开始时间");
				}	
			}else{
				alert("请输入日期");			
			}
		}
	</script>
</body>
</html>