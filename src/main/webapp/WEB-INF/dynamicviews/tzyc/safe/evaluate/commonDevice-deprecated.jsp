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
    <table id="dg" title="通用设备信息管理" class="easyui-datagrid" 
    		data-options="method:'GET'"
            url="/tzyc/safe/evaluate/device/allWidthPagination"
            toolbar="#toolbar" pagination="true" 
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th data-options="field:'id', hidden:true">id</th>
                <th field="code">设备编码</th>
                <th field="typeId" width="50">设备种类</th>
                <th field="name" width="50">设备名称</th>
                <th field="departId" width="50">部门名称</th>
                <th field="brand" width="50">设备品牌</th>
                <th field="model" width="50">规格型号</th>
                <th field="position" width="50">位置</th>
                <th field="application" width="50">用途</th>
                <th field="openingDate" width="50">启用日期</th>
                <th field="equipStatus" width="50">设备状态</th>
                <th field="productionDate" width="50">生产日期</th>
                <th field="amount" width="50">数量</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editObject()">更新</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyObject()">删除</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:400px;height:430px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons" >
      <!--   <div class="ftitle">用户信息</div> -->
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>设备编码:</label>
                <input name="code">
                <input name="creatorId" type="hidden">
                <input name="id" type="hidden">
            </div>
            <div class="fitem">
                <label>设备种类:</label>
                <input name="typeId">
            </div>
            <div class="fitem">
                <label>设备名称:</label>
                <input name="name">
            </div>
            <div class="fitem">
                <label>部门名称:</label>
                <input name="departId">
            </div>
            <div class="fitem">
                <label>设备品牌:</label>
                <input name="brand">
            </div>
            <div class="fitem">
                <label>规格型号:</label>
                <input name="model">
            </div>
            <div class="fitem">
                <label>位置:</label>
                <input name="position">
            </div>
            <div class="fitem">
                <label>用途:</label>
                <input name="application">
            </div>
            <div class="fitem">
                <label>启用日期:</label>
                <input name="openingDate" class="easyui-datetimebox" editable="false">
            </div>
            <div class="fitem">
                <label>设备状态:</label>
                <input name="equipStatus">
            </div>
            <div class="fitem">
                <label>生产日期:</label>
                <input name="productionDate" class="easyui-datetimebox" editable="false">
            </div>
            <div class="fitem">
                <label>数量:</label>
                <input name="amount">
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
            $('#dlg').dialog('open').dialog('setTitle','新建设备');
            $('#fm').form('clear');
            url = '/tzyc/safe/device/save';
        }
        function editObject(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改设备');
                $('#fm').form('load',row);
                url = '/tzyc/safe/device/update';
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
            width:80px;
        }
    </style>
</body>
</html>