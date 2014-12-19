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
   
    
    <table id="dg" title="7S检查评价分类" class="easyui-datagrid"
            url="7sEvaluateType/all"
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
               	<th data-options="field:'id', hidden:true">id</th>
                <th field="code" width="50">评价分类编号</th>
                <th field="name" width="50">评价分类名称</th>
                <th field="remark" width="50">评价分类描述</th>                
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:400px;height:310px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <form id="fm" method="post" novalidate>
        	
            <div class="fitem">
                <label>评价分类编号:</label>
                
                <input name="code" class="easyui-validatebox" data-options="required:true,validType:'length[1,30]'">
            </div>
            
            <div class="fitem">
                <label>评价分类名称:</label>
                <input name="name" class="easyui-validatebox" data-options="required:true,validType:'length[1,30]'">
            </div>
            
      
          	
            <div class="fitem">
                <label>评价分类描述</label>
                <textarea name="remark"></textarea>
            </div>
        </form>
    </div>
    
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">

    	
	    function length(){
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
	    	$.post('7sEvaluateType/getlist',{exportfield:exportfield,exportheaders:exportheaders,title:title},function(result){
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
   
