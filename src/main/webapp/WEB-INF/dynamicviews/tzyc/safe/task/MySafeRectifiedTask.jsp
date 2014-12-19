<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %>    
    
<!DOCTYPE html>
<html>
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
  	<title>${application_name_cn}</title>
   
    
    <cmstudio:htmlBase/>
	
	<!--Start importing the jquery files -->
	<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
	<!--End import the jquery files -->
	<!--Start importing the jeasyui files -->
	<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
	
</head>
<body>
   
    
    <table id="dg" title="我的7S整改列表" class="easyui-datagrid"
            url="SafeProblemRectify/findAll?checker=111"
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
               	<th data-options="field:'id', hidden:true">id</th>
                <th field="checkerName" width="50">负责人</th>
                <th field="deadline" width="50">完成期限</th>
                <th field="checkerOrgName" width="50">所属部门</th>                
                <th field="createtime" width="50">创建时间</th>   
                <th field="creatorName" width="50">创建人</th>   
                <th data-options="field:'cmcm', formatter: function(value,row,index){
				return taskDetailLink(row);
			}" width="50">整改情况</th>   
				
				<th data-options="field:'exportExcel', formatter: function(value,row,index){
					return taskexportExcel(row);
				}" width="50">导出</th> 
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:600px;height:510px;padding:10px"
            closed="true" buttons="#dlg-buttons">
        <div>
        	<p><span style="text-decoration:none; font-weight:bold;font-size:14px; line-height:16px;" id="checkerOrgSpan"></span>处室（中心）：</p>
        	<p>在<span style="font-weight:bold;" id="inspectTimeSpan"></span>进行的7S检查中，发现你部门存在的不合格问题<span id="problemCountSpan" style="font-weight:bold;"></span>条，现将整改事项通知如下：</p>
        	
        	<table id="rectifyDivDg"></table>
        	<div>
        		<div></div>
	        	<p style="text-align:right;">创建人：<span id="creatorName"></span></p>
        	</div>
        </div>
    </div>
    
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:$('#dlg').dialog('close')">确定</a>
    </div>
    <script type="text/javascript">
	   function showRectifyProgress(id){
		       $('#dlg').dialog('open').dialog('setTitle','整改任务查看');
			   $('#rectifyDivDg').datagrid({
	   			singleSelect:true,
	   			onClickCell: function(index,field,value){
	   				if(field=='delete'){
	   					$("#rectifyDivDg").datagrid("deleteRow",index);
	   				}
	   			},
	   			rownumbers:true,
	   		    columns:[[
						{field:'id',title:'编号',hidden:true},
						{field:'relatedDeviceName',title:'检查设备',width:100},
		   		        {field:'decription',title:'需整改事项',width:300},
		   		        {field:'deadline',title:'整改期限',width:100}    
	   		    ]]
	   		});
			   $.post("SafeProblemRectify/findRectifyResultById",{"rectifyId":id}, function(result){
				   $("#checkerOrgSpan").html(result.RectifyInfo.checkerOrgName);
				   $("#inspectTimeSpan").html(result.RectifyInfo.createtime.substring(5,7)+"月份");
				   $("#problemCountSpan").html(result.problemStandards?result.problemStandards.length:0);
				   $("#creatorName").html(result.RectifyInfo.creatorName);
				   for(var i=0; i<result.problemStandards.length; i++){
					   result.problemStandards[i].deadline=result.RectifyInfo.deadline;
				   }
				   $('#rectifyDivDg').datagrid({
						data:result.problemStandards
					});
				   
			   });
	   }	
    
    
		function taskDetailLink(row){
			return '<a href="javascript:showRectifyProgress(\''+row.id+'\')">任务进度</a>';
		}
		
		
		function taskexportExcel(row){
			return '<a href="javascript:length(\''+row.id+'\')">导出</a>';
		}
    	
	    function length(id){
	    	$.post('SafeProblemRectify/exportExcel',{
	    		title:"安全管理整改通知书",
	    		rectifyId:id
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
	    
	    

        var url;
        function newUser(){
            $('#dlg').dialog('open').dialog('setTitle','新建');
            $('#fm').form('clear');
            url = '7sEvaluateType/save';
        }
        function editUser(){
        	var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改');
                $('#fm').form('load',row);
                url = '7sEvaluateType/update?id='+row.id+'&RECORD_STATUS=0';
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyUser(){
        	var row = $('#dg').datagrid('getSelected');
            if (row){
            	$.messager.confirm('提示','你确定删除吗?',function(r){
            		if (r){
            			  //$('#dlg').dialog('open').dialog('setTitle','修改猫咪');
                          $('#fm').form('load',row);
                          url = '7sEvaluateType/update?id='+row.id+'&RECORD_STATUS=1';
                          saveUser();
            		}
            	})
            
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
        
        .fitem textarea{
            width:150px;
        }
    </style>
</body>
</html>
   
