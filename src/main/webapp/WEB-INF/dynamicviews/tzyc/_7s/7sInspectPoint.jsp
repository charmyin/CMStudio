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
	
	<!--Start importing the ztree files -->
		<cmstudio:importJsCss name="ztree" version="${ztree_version}"/>
	<!--End importing the ztree files -->
	
</head>
<body>
    <table id="dg" title="7S检查点" class="easyui-datagrid"
            url="7sInspectPoint/all"
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th data-options="field:'id', hidden:true">id</th>
                
                <th field="code" width="50" sortable="true">检查点编码</th>        
                <th field="pointName" width="50">检查点名称</th>   
                <th field="position" width="50">位置</th>
                <th field="departName" width="50">部门</th>
                <th field="remark" width="50">描述</th>
                <th field="creatorName" width="50">创建者</th>
                <th field="createTime" width="50">创建时间</th>
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
                <label>检查点编码:</label>
                <input name="code" class="easyui-validatebox" data-options="required:true,validType:'length[1,30]'">
            </div>
            
            <div class="fitem">
                <label>检查点名称:</label>
                <input name="pointName" class="easyui-validatebox" data-options="required:true,validType:'length[1,30]'">
            </div>
            
             <div class="fitem departmentDiv">
                <label>部门名称:</label>
                <input id="depart_name" style="width:70px;" disabled>
                <input name="departId" id="input_department_id" style="width:70px;" type="hidden"> 
                <a id="dpt_btn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">选择</a>
            </div>
            
      		<div class="fitem">
                <label>位置:</label>
                <input name="position" class="easyui-validatebox" data-options="required:true,validType:'length[1,30]'">
            </div>
            <div class="fitem">
                <label>描述</label>
                <textarea name="remark"></textarea>
            </div>
        </form>
    </div>
    
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    
    
    <!-- 部门选择 -->
	<div id="department-dlg" class="easyui-dialog" data-options="closed:'true',buttons:'#department-dlg-buttons', modal:true">
        <div style="height:400px;width:300px;" class="ztree" id="div_department_tree"></div>
    </div>
    <div id="department-dlg-buttons">
	       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveDepartment()">保存</a>
	       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#department-dlg').dialog('close')">取消</a>
	</div>
    
    
    <script type="text/javascript">
    var departmentAllInJson;
    
    function getDepartmentDataInJson(){
    	$.ajax({ 
	           type: "GET", 
            url: "organization/all", 
             dataType: "json", 
             success: function (data) { 
            	 departmentAllInJson=data;
             }, 
             error: function (XMLHttpRequest, textStatus, errorThrown) { 
            	 $.messager.show({    // show error message
                     title: '<span style="color:red;">提示</span>',
                     msg: "<div style='text-align:center;margin-top:10px;'>系统错误</div>",
                     style:{
                 		right:'',
                 		top:document.body.scrollTop+document.documentElement.scrollTop,
                 		bottom:''
                 	}
                 });
             } 
         });
    }

    
    
    
      //菜单树
       var allDepartmentTreeObj;
       var allDepartmentTreeSetting = {
       		data:{
       			simpleData:{
       				enable:true,
       				idKey:"id",
       				pIdKey:"parentId"
       			}
       		},
       		check:{
       			enable:true,
       			chkStyle:"radio"
       		}
       };
       
        
      //Load all the menu Tree
        function loadDepartmentTree(){
       		  //Load the system manage tree
       		  allDepartmentTreeObj = $.fn.zTree.init($("#div_department_tree"), allDepartmentTreeSetting, departmentAllInJson);
       		  //rename the
       		  var rootNode = allDepartmentTreeObj.getNodeByParam("id","1");
       		  rootNode.name = $("title").html();
       		  allDepartmentTreeObj.refresh();
       		  allDepartmentTreeObj.expandAll(true);
       		  
       		//载入form下name中的值
       		var department = $("#input_department_id").val();
       		//如果name中含有值，则选中树中对应的选项
       		if(department&&department!=''){
       			var departmentArray = department.split(","); 
       			for(var i=0; i<departmentArray.length; i++){
       				var checkNodeTemp = allDepartmentTreeObj.getNodeByParam("id",departmentArray[i]);
       				allDepartmentTreeObj.checkNode(checkNodeTemp,true);
       			}
       		} 
 
        }
      
      //选择部门后保存
      function saveDepartment(){
    	 var nodes = allDepartmentTreeObj.getCheckedNodes();
    	 if(nodes && nodes.length==1){
    		 $("#depart_name").val(nodes[0].name);
    		 $("#input_department_id").val(nodes[0].id);
    		 $('#department-dlg').dialog('close');
    	 }else{
    		 $.messager.show({    // show error message
                 title: '提示<span style="color:red;">!</span>',
                 msg: "<div style='text-align:center;margin-top:10px;'>必须且只能选择一个节点</div>",
                 style:{
             		right:'',
             		top:document.body.scrollTop+document.documentElement.scrollTop,
             		bottom:''
             	}
             });
    		 return;
    	 }
    	 
      }
      
      
      $(function(){
      	
      	/* $('#department-dlg').dialog({
  		    width: 330,
  		    height: 500,
  		    closed: true,
  		    cache: false,
  		    modal: true
  		}); */
      	$("#dpt_btn").click(function(){
      		$('#department-dlg').dialog('open').dialog('setTitle','选择部门');
      		loadDepartmentTree();
      	});
      	getDepartmentDataInJson();
      	
      });
    
    
    
    
        var url;
        function newUser(){
            $('#dlg').dialog('open').dialog('setTitle','新建');
            $('#fm').form('clear');
            url = '7sInspectPoint/save';
        }
        function editUser(){
        	var row = $('#dg').datagrid('getSelected');
        	$("#depart_name").val(row.departName);
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改');
                $('#fm').form('load',row);
                url = '7sInspectPoint/update?id='+row.id+'&RECORD_STATUS=0';
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
   
