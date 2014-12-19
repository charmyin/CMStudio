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
    <table id="dg" title="7S检查任务" class="easyui-datagrid"
            url="7sInspectTask/all" 
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th data-options="field:'id', hidden:true">id</th>
                <th field="pointName" width="50">检查点名称</th>
                <th field="taskType" width="50"data-options='formatter:function(value,row,index){
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
                <th field="startTime" width="50">开始时间</th>   
                <th field="endTime" width="50">结束时间</th>
                <th field="creatorName" width="50">创建者</th>
                <th field="createTime" width="50">创建时间</th>
            </tr>
        </thead>
    </table>
<!--     <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
    </div> -->
    <!-- <div id="toolbar">    	
			开始时间: <input id="startDate" class="easyui-datetimebox" style="width: 150px" data-options="editable:false"> 
			结束时间: <input id="endDate" class="easyui-datetimebox" style="width: 150px" data-options="editable:false"> 			   
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="length()">导出</a>
    </div> -->
    
    <div id="dlg" class="easyui-dialog" style="width:400px;height:310px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <form id="fm" method="post" novalidate>
        
            <div class="fitem">
                <label>任务类型:</label>
                <input name="taskType" class="easyui-numberbox" data-options="min:0,max:20,required:true">
            </div>
            
            <div class="fitem">
                <label>检查点名称:</label>
                <input name="startTime" class="easyui-validatebox" data-options="required:true,validType:'length[1,30]'">
            </div>
            
      		<div class="fitem">
                <label>位置:</label>
                <input name="endTime" class="easyui-validatebox" data-options="required:true,validType:'length[1,30]'">
            </div>
            
        </form>
    </div>
    
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    
    
    <script type="text/javascript">
    	
   /*  function length(){
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
			}else{
			 var exportfield = [];
		    	var exportheaders = [];
		    	var length = $("#dg").datagrid('options').columns[0].length;
		    	for(var j = 0 ; j < length ; j++){
		    		var obj = $("#dg").datagrid('options').columns[0][j];
		    		if(!obj.hidden){
		    			exportfield.push(obj.field);
		    			exportheaders.push(obj.title);
		    		}
		    	} 
		    	var title = $("#dg").datagrid('options').title;	
		    	$.post('7sInspectTask/exportExcel',{
		    		 exportfield:exportfield,
		    		exportheaders:exportheaders, 
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
    	
    	
    } */
    
        var url;
        function newUser(){
            $('#dlg').dialog('open').dialog('setTitle','新建');
            $('#fm').form('clear');
            url = '7sInspectTask/save';
        }
        function editUser(){
        	var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改');
                $('#fm').form('load',row);
                url = '7sInspectTask/update?id='+row.id+'&RECORD_STATUS=0';
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
                          url = '7sInspectPoint/update?id='+row.id+'&RECORD_STATUS=1';
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
            width:70px;
        }
        
        .fitem textarea{
            width:150px;
        }
    </style>
</body>
</html>
   
