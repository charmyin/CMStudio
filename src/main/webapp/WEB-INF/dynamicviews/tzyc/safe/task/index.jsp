<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %>
<!DOCTYPE html>
<html>
<head>
    <title> ${application_name_cn} </title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<cmstudio:htmlBase/>
	<link rel="shortcut icon" type="image/x-icon" href="resources/${icon_name}"/>
	
	<!--Start importing the jquery files -->
	<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
	<!--End import the jquery files -->
	<!--Start importing the jeasyui files -->
	<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
	<!--End importing the jeasyui files -->
</head>
<body>
	 
        <div data-options="region:'west',split:true" >
        
        	<table id="dg" title="安全检查任务管理" class="easyui-datagrid" 
        	data-options="method:'GET'"
            url="/tzyc/safe/task/findAllSafeTasksWithPager"
            toolbar="#toolbar" pagination="true" 
            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
		                <th data-options="field:'id', hidden:true">id</th>
		                <th data-options="field:'creatorId', hidden:true">创建者编号</th>
		              <!--   <th field="deviceId" width="50" hidden="true">设备ID</th> -->
		                <th field="deviceName" width="50">设备名称</th>
		                <th field="taskType" width="50" data-options='formatter:function(value,row,index){
                				switch(row.taskType){
                					case 0 : 
                						return "单次";
                					case 1 :
                						return "每日";
                					case 2 :
                						return "每周";
                					case 3 :
                						return "每月";
                					case 4 :
                						return "每季度";
                					case 5 :
                						return "每年";
                					default :
                						break;
                				};
                				
        		  			}'>任务类型</th>
		               <!--  <th field="detailId" width="50">检查任务详细ID</th> -->
		                <th field="startTime" width="50">任务起始时间</th>
		                <th field="endTime" width="50">任务截止时间</th>
		                <th field="creatorUi" width="50">创建者</th>
		                <th field="createTime" width="50">创建时间</th>
		            </tr>
		        </thead>
		    </table>
		    <!-- <div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject()">新建</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyObject()">删除</a>
		    </div> -->
		    
		    <div id="dlg" class="easyui-dialog" style="width:380px;height:300px;padding:10px 20px"
		            closed="true" buttons="#dlg-buttons" >
		      <!--   <div class="ftitle">用户信息</div> -->
		        <form id="fm" method="post" novalidate>
		            <div class="fitem">
		                <label>任务类型:</label>
		                <input name="taskType">
		                <input name="id" type="hidden">
		            </div>
		            <div class="fitem">
		                <label>设备ID:</label>
		                <input name="deviceId">
		            </div>
		            <div class="fitem">
		                <label>检查任务详细ID:</label>
		                <input name="detailId">
		            </div>
		            <div class="fitem">
		                <label>任务起始时间:</label>
		                <input name="startTime" class="easyui-datetimebox" editable="false">
		            </div>
		            <div class="fitem">
		                <label>任务截止时间:</label>
		                <input name="endTime"  class="easyui-datetimebox" editable="false">
		            </div>
		        </form>
		    </div>
		    <div id="dlg-buttons">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveObject()">保存</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
		    </div>
        
       
    </div>

	<div id="toolbar">		
			开始时间: <input id="startDate" class="easyui-datebox" style="width: 150px" data-options="editable:false"> 
			结束时间: <input id="endDate" class="easyui-datebox" style="width: 150px" data-options="editable:false"> 			
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="length()">导出</a>
	</div>
    
    <script type="text/javascript">
    
        var url;
        function newObject(){
            $('#dlg').dialog('open').dialog('setTitle','新建任务');
            $('#fm').form('clear');
            url = '/tzyc/safe/task/save';
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
			    	$.post('/tzyc/safe/task/exportExcel',{
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
        
      /*   function editObject(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改设备');
                $('#fm').form('load',row);
                url = '/tzyc/safe/task/update';
            }
        } */
        function saveObject(){
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
                        $('#dg').datagrid('reload');    // reload the Object data
                    }
                }
            });
        }
        function destroyObject(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('警告','确定删除该条记录?',function(r){
                    if (r){
                        $.post('/tzyc/safe/device/deleteByIds',{ids:row.id}, function(result){
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
            width:100px;
        }
    </style>
</body>
</html>