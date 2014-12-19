<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %>
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>机房巡检任务</title>
    <cmstudio:htmlBase/>
       <!--Start importing the jquery files -->
	<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
	<!--End import the jquery files -->
	<!--Start importing the jeasyui files -->
	<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
	<!--End importing the jeasyui files -->
  </head>
<body>
    <table id="dg" title="机房巡检任务" class="easyui-datagrid" 
            url="room/all"
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
            	<th field="createorName" width="50">创建者</th> 
            	<th field="inspectorName" width="50">检查员</th> 
            	<th field="departName" width="50">部门</th> 
            	<th field="createTime" width="50">创建时间</th> 
            	<th field="reportTime" width="50">提报时间</th>
                <th field="airTemperature" width="50">空调温度</th> 
                <th field="airHumidity" width="50">空调湿度</th> 
                <th field="filePath" width="50" data-options="align:'center',
					formatter: function(value,row,index){
						 
						if(!row.filePath || row.filePath.length<1){
			    			return '无图片';
			    		}
						return formatHTML3+row.filePath+formatHTML4;
						 
					}
				" >图片记录</th>
                <th field="id"
					data-options="align:'center',
					formatter: function(value,row,index){
						if (row.taskStatus==1){
							return '一切正常';
						} else if(row.taskStatus==0){
							return formatHTML+row.id+formatHTML1;
						}
					}
				"
					width="50">任务结果</th>
                 <th field="remark" width="50">备注</th>

             </tr>
        </thead>
    </table>
	<div id="dialog-issueResult" closed="true" class="easyui-dialog"
		title="检查问题列表" style="width: 800px; height: 372px;"
		data-options="iconCls:'icon-save',resizable:true,modal:true">
		<table id="standardErrorResultGrid"></table>
	</div>
	<div id="dialog-images" data-options="maximizable:true" closed="true" class="easyui-dialog" title="检查问题图片" style="padding:10px 0 0 0 ;text-align:center;width:800px;height:600px;"
	        data-options="iconCls:'icon-save',resizable:true,modal:true,buttons:'#images-dlg-buttons'">
	        <div id="inner-dialog-images"></div>
	        <div id="images-dlg-buttons">
			       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dialog-images').dialog('close')">关闭</a>
			</div>
	</div>
	
	<div id="toolbar">		
	开始时间: <input id="startDate" class="easyui-datebox" style="width: 150px" data-options="editable:false"> 
			结束时间: <input id="endDate" class="easyui-datebox" style="width: 150px" data-options="editable:false"> 	
		<a href="javascript:searchResult();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>				
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="length()">导出</a>	
	</div>
	
	<script type="text/javascript">
	function searchResult(){
		$("#dg").datagrid("load", {
			startDate: $('#startDate').datebox('getValue'),
			endDate: $('#endDate').datebox('getValue')
		});
	}function showIssueResults(id) {

		$("#dialog-issueResult").dialog("open");

		//初始化commonDevice
		var standardResultDataGrid = $('#standardErrorResultGrid')
				.datagrid({
					url : 'room/findAllErrorStandardWithOptions',
					method : 'GET',
					queryParams : {
						id : id
					},
					title : "机房检查标准问题列表",
					closed : false,
					pagination : true,
					loadFilter : pagerFilter,
					rownumbers : true,
					fitColumns : true,
					singleSelect : true,
					columns : [[ {field:'itemName',title:'检查项目',width:200},
			    			        {field:'content',title:'检测内容',width:100},
			    			        {field:'status',title:'状态',width:100,formatter: function(value,row,index){
			    			        	if (row.taskResult==1){
			    							return '正常';
			    						} else if(row.taskResult==0){
			    							return '异常';
			    						}
			    					}},			    			     
					]]
				});

	} 
	
	function showResultsImages(filePaths){
		//alert(taskResult);
		$("#dialog-images").dialog("open");
		$("#inner-dialog-images").html('');
		var arr = filePaths.split(";");
		for(var i=0; i<arr.length-1; i++){
			var imgstr = '<div><img src="resources/download/'+arr[i]+'" style="height:350px;width:650px;margin:5px 0 5px 0;"/></div>';
			$("#inner-dialog-images").append($(imgstr));
			
		}
		//$("#dialog-images").text(filePaths);
	}
	
	
	//检查问题详细查看
	var formatHTML = "<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-search' onclick='showIssueResults(\"";
	var formatHTML1 = "\")'>检查问题详细</a>";
	//检查图片详细查看
	var formatHTML3="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-search' onclick='showResultsImages(\"";
	var formatHTML4 = "\")'>查看</a>";
	function pagerFilter(data) {
		if (typeof data.length == 'number'
				&& typeof data.splice == 'function') { // is array
			data = {
				total : data.length,
				rows : data
			}
		}
		var dg = $(this);
		var opts = dg.datagrid('options');
		var pager = dg.datagrid('getPager');
		pager.pagination({
			onSelectPage : function(pageNum, pageSize) {
				opts.pageNumber = pageNum;
				opts.pageSize = pageSize;
				pager.pagination('refresh', {
					pageNumber : pageNum,
					pageSize : pageSize
				});
				dg.datagrid('loadData', data);
			}
		});
		if (!data.originalRows) {
			data.originalRows = (data.rows);
		}
		var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
		var end = start + parseInt(opts.pageSize);
		data.rows = (data.originalRows.slice(start, end));
		return data;
	}
	function length() {
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
			else{
				/* var exportfield = [];
		    	var exportheaders = [];
		    	var length = $("#dg").datagrid('options').columns[0].length;
		    	for(var j = 0 ; j < length ; j++){
		    		var obj = $("#dg").datagrid('options').columns[0][j];
		    		if(!obj.hidden){
		    			exportfield.push(obj.field);
		    			exportheaders.push(obj.title);
		    		}
		    	} */
		    	var title = $("#dg").datagrid('options').title;	
		    	$.post('/tzyc/room/exportExcel',{
		    		/* exportfield:exportfield,
		    		exportheaders:exportheaders, */
		    		startDate : startDate,
					endDate : endDate,
					
		    		title:title
		    		},
		    		function(result){
		    		var result = eval('('+result+')');
		    		if (result.suc){
		    			location.href = $("base").attr("href")+"resources/download/report.xls";
		            } else {
		                $.messager.show({    // show error message
		                    title: '错误',
		                    msg: result.errorMsg
		                });
		            }
		        });
			}	
		}else{
			alert("请输入日期");			
		}
			
		
	}
	
	</script>

    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
    </style>
</body>
</html>