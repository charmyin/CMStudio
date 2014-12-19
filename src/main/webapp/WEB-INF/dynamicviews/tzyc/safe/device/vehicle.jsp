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
	<!--Start importing the ztree files -->
		<cmstudio:importJsCss name="ztree" version="${ztree_version}"/>
	<!--End importing the ztree files -->
</head>
<body>
    <table id="dg" title="车辆信息管理" class="easyui-datagrid" 
    		data-options="method:'GET'"
            url="/tzyc/safe/device/allWidthPagination?typeId=3"
            toolbar="#toolbar" pagination="true" 
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th data-options="field:'id', hidden:true">id</th>
                <th  data-options="field:'typeId', hidden:true">设备分类</th>
                <th field="plate_number" width="50" sortable="true" data-options="formatter:function(value, row, index){
                	return row.plateNumber;
                }">号牌号码</th>
                <th field="vehicle_type" width="50" sortable="true" data-options="formatter:function(value, row, index){
                	return row.vehicleType;
                }">车辆型号</th>
                <th field="initialRegisteTime" width="50">初次登记时间</th>
                <th field="engineCode" width="50">发动机号</th>
                <th field="frameCode" width="50">车架号</th>
                <th field="vehicleBookCode" width="50">机动车登记书编号</th>
                <th field="annualInspection" width="50">年检情况</th>
                <th field="openingDate" width="50">启用日期</th>
                <th field="departId" width="50">使用部门</th>
                <th field="productionDate" width="50">生产日期</th>
                <th field="driver" width="50">当前驾驶员</th>
                <th field="remark" width="50">备注</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editObject()">更新</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyObject()">删除</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:420px;height:480px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons" >
      <!--   <div class="ftitle">用户信息</div> -->
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>号牌号码:</label>
                <input name="plateNumber" class="easyui-validatebox" data-options="required:true,validType:['length[0,60]']">
                <input name="creatorId" type="hidden">
                <input name="id" type="hidden">
                <input name="typeId" id="typeId" value="3" type="hidden"> 
            </div>
            <div class="fitem">
                <label>车辆型号:</label>
                <input name="vehicleType"  class="easyui-validatebox" data-options="required:true,validType:['length[0,60]']">
            </div>
            <div class="fitem">
                <label>初次登记时间:</label>
                <input name="initialRegisteTime" class="easyui-datetimebox">
            </div>
            <div class="fitem">
                <label>发动机号:</label>
                <input name="engineCode" class="easyui-validatebox" data-options="required:true,validType:['length[0,60]']">
            </div>
            <div class="fitem">
                <label>车架号:</label>
                <input name="frameCode" class="easyui-validatebox" data-options="required:true,validType:['length[0,60]']">
            </div>
            <div class="fitem">
                <label>机动车登记书编号:</label>
                <input name="vehicleBookCode" class="easyui-validatebox" data-options="required:true,validType:['length[0,60]']">
            </div>
            <div class="fitem">
                <label>年检情况:</label>
                <input name="annualInspection" class="easyui-validatebox" data-options="required:true,validType:['length[0,60]']">
            </div>
            <div class="fitem">
                <label>启用日期:</label>
                <input name="openingDate" class="easyui-datetimebox" readonly="readonly" editable="false">
            </div>
            <div class="fitem">
                <label>使用部门:</label>
                <input id="depart_name" style="width:70px;" disabled>
                <input name="departId" id="input_department_id" style="width:70px;" type="hidden"> 
                <a id="dpt_btn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">选择</a>
            </div>
            <div class="fitem">
                <label>生产日期:</label>
                <input name="productionDate" class="easyui-datetimebox" readonly="readonly" editable="false">
            </div>
            <div class="fitem">
                <label>当前驾驶员:</label>
                <input name="driver" class="easyui-validatebox" data-options="required:true,validType:['length[0,60]']">
            </div>
            <div class="fitem">
                <label>备注:</label>
                <input name="remark" class="easyui-validatebox" data-options="validType:['length[0,1024]']">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveObject()">保存</a>
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
	    
    
    
    
        var url;
        function newObject(){
            $('#dlg').dialog('open').dialog('setTitle','新建');
            $('#fm').form('clear');
         	//初始化类型为1：通用设备类型
            $("#typeId").val(3);
            url = '/tzyc/safe/device/save';
        }
        function editObject(){
            var row = $('#dg').datagrid('getSelected');
          	//初始化类型为3：通用设备类型
            $("#typeId").val(3);
          	
          //显示修改的部门名称
            for(var i=0; i<departmentAllInJson.length; i++){
         	 
       			if(departmentAllInJson[i].id==row.departId){
       				$("#depart_name").val(departmentAllInJson[i].name);
       			}
         
         	}
          	
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改');
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
	    	
	    	$("#dg").datagrid({
	    		onLoadSuccess:function(){
	    			for(var i=0; i<departmentAllInJson.length; i++){
	             		$("td[field='departId']").each(function(){
	             			if(departmentAllInJson[i].id==$(this).text()){
	             				$(this).find("div").text(departmentAllInJson[i].name);
	             			}
	             		})
	             	}
	    		}
	    	});
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
            width:120px;
        }
    </style>
</body>
</html>