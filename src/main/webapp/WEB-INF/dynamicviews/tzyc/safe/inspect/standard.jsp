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
    <table id="dg" title="安全管理检查标准" class="easyui-datagrid" 
            url="safe/inspect/standard/all"
            toolbar="#toolbar" pagination="true" 
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th data-options="field:'id', hidden:true">id</th>
                <th field="typeName" width="50">安全管理评价分类</th>
                <th field="itemName" width="50">安全管理评价项目</th>
                <th field="decription" width="50">标准描述</th>
                <th field="createTime" width="50">创建时间</th>
                <th field="remark" width="50">备注</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editObject()">更新</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyObject()">删除</a>
        
        &nbsp;&nbsp;
        评价分类: <input class="easyui-combobox" 
			data-options="valueField:'id', textField:'name', panelHeight:'auto'" 
			style="width:180px" id="evaluateType">
       	评价内容: <input class="easyui-combobox"
       		data-options="valueField:'id', textField:'name', panelHeight:'auto'"
       		style="width:180px" id="evaluateItem">
        	<a href="javascript:searchResult();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:400px;height:290px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons" >
      <!--   <div class="ftitle">用户信息</div> -->
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>安全管理评价分类</label>
                	<input class="easyui-combobox" name="typeId"  id="typeId">
            </div> 
            <div class="fitem">
                <label>安全管理评价项目:</label>
                <input class="easyui-combobox" name="itemId" id="itemId" data-options="
					method: 'post',
					valueField:'id',
					textField:'name',
					panelHeight:'auto'
					">
            </div>
            <div class="fitem">
                <label>标准描述:</label>
               <textarea name="decription" class="easyui-validatebox"></textarea>
            </div>
            <div class="fitem">
                <label>备注:</label>
               <textarea name="remark" class="easyui-validatebox"></textarea>
            </div>                                 
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveObject()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function newObject(){
            $('#dlg').dialog('open').dialog('setTitle','新建');
            $('#fm').form('clear');
            url = 'safe/inspect/standard/save';
        }
        
        
        function searchResult(){
			$("#dg").datagrid("load", {
				typeId: $('#evaluateType').datebox('getValue'),	
				itemId: $('#evaluateItem').datebox('getValue')
			});
			
		}
        
        
        function editObject(){
            var row = $('#dg').datagrid('getSelected');
            
            
            $('#itemId').combobox('reload', 'safe/evaluate/item/allWithoutPagerInfo?typeId='+row.typeId);
            
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改');
                $('#fm').form('load',row);
                url = 'safe/inspect/standard/update?id='+row.id+'&RECORD_STATUS=0';
            }
        }
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
                        $.post('safe/inspect/standard/update?id='+row.id+'&RECORD_STATUS=1', function(result){
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
        
        $(function(){
        	$('#typeId').combobox({
        		url: 'safe/evaluate/type/allTypesNoPagerInfo',
				method: 'get',
				valueField:'id',
				textField:'name',
				panelHeight:'auto',
				onSelect:function(record){
					$('#itemId').combobox('reload', 'safe/evaluate/item/allWithoutPagerInfo?typeId='+record.id);
					$('#itemId').combobox('setValue', '');
				}
        	});
        	
        	
        	$('#evaluateType').combobox({
        		url: 'safe/evaluate/type/allTypesNoPagerInfo',
				method: 'get',
				valueField:'id',
				textField:'name',
				panelHeight:'auto',
				onSelect:function(record){
					$('#evaluateItem').combobox('reload', 'safe/evaluate/item/allWithoutPagerInfo?typeId='+record.id);
					$('#evaluateItem').combobox('setValue', '');
				}
        	});
        	
        	
        	
        	
        	//url: 'safe/evaluate/item/allWithoutPagerInfo?typeId=',
        });
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
            width:110px;
        }
    </style>
</body>
</html>