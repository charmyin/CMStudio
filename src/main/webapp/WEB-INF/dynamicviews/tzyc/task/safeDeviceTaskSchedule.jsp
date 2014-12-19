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
            width:130px;
            text-align:right;
			margin-right:10px;
        }
        textarea{
           position:relative;
           top:18px;
           height:40px;
        }
    </style>
</head>
<body>
    <table id="dg" title="安全计划调度管理" class="easyui-datagrid" 
    		data-options="method:'GET'"
            url="/tzyc/taskSchedule/allTaskScheduleWithPagination?taskTableName=TB_SAFE_TASK"
            toolbar="#toolbar" pagination="true" 
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
             	<th field="id" data-options="hidden:true">id</th>
                <th field="taskName" width="50">计划调度名称</th>
                <th field="taskType" width="50" data-options='formatter:function(value,row,index){
                				switch(row.taskType){
                					case 0 : 
                						return "即时";
                					case 1 :
                						return "按日";
                					case 2 :
                						return "按周";
                					case 3 :
                						return "按月";
                					case 4 :
                						return "按季度";
                					case 5 :
                						return "按年";
                					default :
                						break;
                				};
                				
        		  			}'>计划调度分类</th>
                <th field="onceTime" width="50">单次执行时间</th>
                <th field="frequencyInterval" width="50">间隔周期</th>
                <th field="startTime" width="50">开始时间</th>
                <th field="endTime" width="50">结束时间</th>
<!--                 <th field="taskTableName" width="50">对应插入任务表名</th>
                <th field="taskDetailTableName" width="50">对应插入任务详细表名</th> -->
                <th field="creatorId" width="50" hidden="true">创建人员ID</th>
                <th field="objectId" width="50" hidden="true">对象ID</th>
                <th field="objectName" width="50" >安全设备名称</th>
                <th field="inspectorIds" width="50" hidden="true">检查人员IDs</th>
                <th field="standardIds" width="50" hidden="true">标准IDs</th>
                <th field="statusFlag" width="50" data-options='formatter:function(value,row,index){
        		  				if (row.statusFlag==0){
        		  					return "已经启用"
        		  				} else if (row.statusFlag==1){
        		  					return "已经禁用";
        		  				}else{
        		  					return "";
        		  				}
        		  			}'>任务有效性</th> 
        		<th field="creatorName" width="50">创建人员</th>
                <th field="createTime" width="50">创建时间</th>
                <th field="modifyTime" width="50" hidden="true">修改时间</th>
                <th field="operation" width="50" data-options='align:"center",formatter:function(value,row,index){
        		  				if (row.taskType==0){
        		  					return deleteInstantTask1+"\""+row.reserved+"\",\""+row.id+"\""+deleteInstantTask2;
        		  				}else{
        		  					return "无操作";
        		  				}
        		  			}'>操作</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editObject()">更新</a>
        <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyObject()">删除</a> -->
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:530px;height:430px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons" >
      	<!--   <div class="ftitle">用户信息</div> -->
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>计划调度名称:</label>
                <input name="taskName">
                <input name="id" type="hidden">
                <input type="hidden" value="TB_SAFE_TASK" name="taskTableName" id="taskTableName" />
            </div>

			<div class="fitem">
                <label>计划调度分类:</label>
                <select class="easyui-combobox" id="selectFrequencyCombo" name="taskType" data-options="panelHeight:'auto', onSelect:function(record){selectFrequencyComboBox(record.value)}" style="width:100px;">
                	<option value="0">即时</option>
				    <option value="1">按日</option>
					<option value="2">按周</option>
					<option value="3">按月</option>
					<option value="4">按季度</option>
					<option value="5">按年</option>
				</select>
            </div>
            <div class="fitem"  style="" id="cycleFitem">
                <label>间隔周期(天):</label>
                <input name="frequencyInterval" class="easyui-numberbox" data-options="min:0">
            </div>
            <div class="fitem" id="onceTimeFitem">
                <label>单次执行时间:</label>
                <input class="easyui-datetimebox" name="onceTime">
            </div>
            <div class="fitem" id="startTimeFitem">
                <label>开始时间:</label>
                <input name="startTime" class="easyui-datetimebox">
            </div>
            <div class="fitem" id="endTimeFitem">
                <label>结束时间:</label>
                <input name="endTime" class="easyui-datetimebox">
               <!--  <input type="hidden" name="taskDetailTableName" value="tb_safe_task_detail"/> -->
               
            </div>
            
            
           <!--  <div class="fitem">
                <label>创建时间:</label>
                <input name="createTime">
            </div>
            <div class="fitem">
                <label>修改时间:</label>
                <input name="modifyTime">
            </div> -->
            <!--  <div class="fitem">
                <label>对应插入任务表名:</label>
                <select class="easyui-combo" name="taskTableName" data-options="panelHeight:'auto'" style="width:100px;">
				    <option value="tb_safe_task">安全设备</option>
					<option value="tb_7s_inspect_task">7S检查</option>
				</select>
            </div>
            <div class="fitem">
                <label>对应插入任务详细表名:</label>
                <select class="easyui-combo" name="taskDetailTableName" data-options="panelHeight:'auto'" style="width:120px;">
				    <option value="tb_safe_task_detail">安全设备详细</option>
					<option value="tb_7s_inspect_task_detail">7S检查详细</option>
				</select>
            </div> -->
            <div class="fitem">
                <label>检查对象:</label>
                <input type="text" name="objectName" id="objectName"  disabled="disabled">
                <input name="objectId" id="input_object_id" type="hidden"> 
                <a id="object_btn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">选择</a>
            </div>
            <div class="fitem">
                <label>检查人员:</label>
               <!--  <textarea name="inspectorNames" id="inspectorNames" disabled="disabled"></textarea> -->
                <input name="inspectorIds" id="input_inspectors_id" type="hidden"> 
                <input name="inspectorNames" id="input_inspectorNames" type="text" disabled="disabled"> 
                <a id="inspectors_btn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">选择</a>
            </div>
            <div class="fitem" style="margin-top:10px;">
                <label>检查标准:</label>
                <!-- <textarea name="parameters" disabled="disabled"></textarea> -->
                <input name="standardIds" id="input_standard_id" type="hidden">
                <a id="standard_btn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">查看与选择</a>
            </div>
            <div class="fitem" >
                <label>任务有效性:</label>
                <select class="easyui-combobox" name="statusFlag" id="taskStatusCombobox" data-options="panelHeight:'auto'" style="width:100px;">
				    <option value="0">启用</option>
					<option value="1">禁用</option>
				</select>
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveObject()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    
     <!-- 对象选择 -->
     <div id="objectId-dlg" class="easyui-dialog" data-options="closed:'true',buttons:'#objectId-dlg-buttons', modal:true" style="width:950px;height:400px;">
     
    	<div id="objectToolbar" style="text-align:center;margin:5px 0 5px 0;">
	       <label style="position:relative;top:2px;font-weight:bold;">设备种类:</label>
	       <select class="easyui-combobox" name="equipStatus" style="width:100px;" data-options="panelHeight:'auto', onSelect:objectToolbarChange">
			    <option value="1">通用设备</option>
				<option value="2">特种设备</option>
				<option value="3">车辆</option>
				<option value="4">消防设备设施</option>
			</select>
	    </div>
     
     	<table id="object1Id-dg" class="objectGrid"></table>

    </div>
    <div id="objectId-dlg-buttons">
	       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveObjectId()">确定</a>
	       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#objectId-dlg').dialog('close')">取消</a>
	</div>
    
    <!-- 人员选择 -->
	<div id="inspector-dlg" class="easyui-dialog" data-options="closed:'true',buttons:'#inspector-dlg-buttons', modal:true">
        <div style="height:400px;width:300px;" class="ztree" id="div_inspectors_tree"></div>
    </div>
    <div id="inspector-dlg-buttons">
	       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveInspectors()">确定</a>
	       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#inspector-dlg').dialog('close')">取消</a>
	</div>
	
	<!-- 标准选择 -->
	<div id="standard-dlg" class="easyui-dialog" data-options="closed:'true',buttons:'#standard-dlg-buttons', modal:true, maximizable:true" style="width:1100px;">
	
		<div id="cc" class="easyui-layout" style="width:100%;height:400px;" data-options="fit:true">
		    <div data-options="region:'west',split:true" style="width:300px;">
		    	<div id="selectedStandardsToolbar">
		    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteSelectedStandards()">删除</a>
		    	</div>
		    	<table id="selectedStandardsDataGrid" >
				   <!--  <table id="object1Id-dg" class="objectGrid"></table> -->
				</table>
		    
		    </div>
		    <div data-options="region:'center'" style="padding:5px;background:#eee;">
		        <table id="standardGrid" title="安全管理检查标准" class="easyui-datagrid" 
		            url="safe/inspect/standard/all"
		            toolbar="#standardToolbar" pagination="true" 
		            rownumbers="true" fitColumns="true" fit="true" singleSelect="false">
			        <thead>
			            <tr>
			            	<th data-options="field:'ck',checkbox:true"></th>
			                <th data-options="field:'id', hidden:true">id</th>
			                <th field="typeName" width="50">安全管理评价分类</th>
			                <th field="itemName" width="50">安全管理评价项目</th>
			                <th field="decription" width="50">标准描述</th>
			                <th field="remark" width="50">备注</th>
			                <th field="createTime" width="50">创建时间</th>
			            </tr>
			        </thead>
			    </table>
			    <div id="standardToolbar" style="text-align:center;margin:0 0 5px 0;">
			       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addToLeftStandards()">添加至左侧</a>
			       <label style="position:relative;top:2px;font-weight:bold;">评价种类:</label>
			       <select name="evaluateType" id="evaluateType" style="width:100px;"  class="easyui-combobox" >
				   </select>
					<label style="position:relative;top:2px;font-weight:bold;">评价项目:</label>
					<select class="easyui-combobox" name="evaluateItem" id="evaluateItem" style="width:100px;" data-options="
							method: 'post',
							valueField:'id',
							textField:'name',
							panelHeight:'auto',
							onSelect:evaluateItemSelectChange
							">
					</select>					 
			    </div>
		    </div>
		</div>
    </div>
    <div id="standard-dlg-buttons">
	       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveStandard()">确定</a>
	       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#standard-dlg').dialog('close')">取消</a>
	</div>
	
    <script type="text/javascript">
    
    //删除临时任务
      function deleteInstantTask(taskId, id){
    	  $.ajax({ 
             url: "taskSchedule/deleteInstantTask?taskId="+taskId+"&id="+id, 
            // dataType: "text/plain", 
             success: function (data) { 
            	 if(data=='{\'suc\':true}'){
            		 $.messager.show({    // show error message
                         title: '<span style="color:red;">提示</span>',
                         msg: "<div style='text-align:center;margin-top:10px;'>删除成功</div>",
                         style:{
                     		right:'',
                     		top:document.body.scrollTop+document.documentElement.scrollTop,
                     		bottom:''
                     	}
                     });
            	 }else{
               		$.messager.show({    // show error message
                        title: '<span style="color:red;">提示</span>',
                        msg: "<div style='text-align:center;margin-top:10px;'>任务已经被执行,不可被删除!</div>",
                        style:{
                    		right:'',
                    		top:document.body.scrollTop+document.documentElement.scrollTop,
                    		bottom:''
                    	}
                    });
              	 }
            	  $('#dg').datagrid('reload');
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
      //删除操作
      var deleteInstantTask1 = "<a href='javascript:deleteInstantTask(";
      var deleteInstantTask2 = ");'>删除</a>";
    
    
	    var inspectorsAllInJson;
	    
	    function getInspectorDataInJson(){
	    	$.ajax({ 
	             url: "organizationWithUser/treeItems?organizationId="+1, 
	             dataType: "json", 
	             success: function (data) { 
	            	 inspectorsAllInJson=data;
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
	    var allInspectorsTreeObj;
	    var allInspectorsTreeSetting = {
    		data:{
    			simpleData:{
    				enable:true,
    				idKey:"id",
    				pIdKey:"parentId"
    			}
    		},
    		check:{
    			enable:true,
    			chkStyle:"checkbox"
    		}
	    };
	    
	     
	     //Load all the menu Tree
	     function loadInspectorsTree(){
    		  //Load the system manage tree
    		  allInspectorsTreeObj = $.fn.zTree.init($("#div_inspectors_tree"), allInspectorsTreeSetting, inspectorsAllInJson);
    		  //rename the
    		/*   var rootNode = allInspectorsTreeObj.getNodeByParam("id","1");
    		  rootNode.name = $("title").html(); */
    		  allInspectorsTreeObj.refresh();
    		  allInspectorsTreeObj.expandAll(true);
    		  
	    		//载入form下name中的值
	    		var inspectors = $("#input_inspectors_id").val();
	    		//如果name中含有值，则选中树中对应的选项
	    		if(inspectors&&inspectors!=''){
	    			var inspectorsArray = inspectors.split(","); 
	    			for(var i=0; i<inspectorsArray.length; i++){
	    				var checkNodeTemp = allInspectorsTreeObj.getNodeByParam("id",parseInt(inspectorsArray[i])+100000);
	    				if(checkNodeTemp)
	    				allInspectorsTreeObj.checkNode(checkNodeTemp,true);
	    			}
	    		} 
	     }
    
	     //载入选中对象到页面
	     function saveObjectId(){
	    	 var row = $('#object1Id-dg').datagrid('getSelected');
	            if(row){
	               $("#input_object_id").val(row.id);
	               if(row.name){
	            	   $("#objectName").val(row.name);
	               }else{
	            	   $("#objectName").val(row.vehicleType);
	               }
	               
	              
	               $("#objectId-dlg").dialog("close");
	            } 
	     }
	     
	     
	     //确定选择的用户
	     function saveInspectors(){
	    	 var nodes = allInspectorsTreeObj.getCheckedNodes();
	    	 var inspectorNames;
	    	 var inspectorIds;
	    	 for(var i=0; i<nodes.length; i++){
	    		 if(i==0){
	    			 inspectorNames=nodes[i].name;
	    			 inspectorIds = (nodes[i].id-100000);
	    		 }else{
	    			 inspectorNames=(nodes[i].name+',')+inspectorNames;
	    			 inspectorIds = ((nodes[i].id-100000)+',')+inspectorIds;
	    		 }
	    	 }
	    	 $("#input_inspectorNames").val(inspectorNames);
	    	 $("#input_inspectors_id").val(inspectorIds);
	    	 
	    	 $('#inspector-dlg').dialog('close');
	     }
    
        var url;
        function newObject(){
            $('#dlg').dialog('open').dialog('setTitle','新建');
            $('#fm').form('clear');
            $('#taskStatusCombobox').combobox('setValue', '0');
            //任务类型combobox，初值设定
        	$('#selectFrequencyCombo').combobox("setValue",'0');
        	selectFrequencyComboBox('0');
            
            url = '/tzyc/taskSchedule/save';
        }
        function editObject(){
            var row = $('#dg').datagrid('getSelected');
            
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改');
                $('#fm').form('load',row);
                url = '/tzyc/taskSchedule/update';
            }
        }
        function saveObject(){
        	
        	$("#taskTableName").val("TB_SAFE_TASK");
        	
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
                        $.post('/tzyc/safe/evaluate/type/deleteByIds',{ids:row.id}, function(result){
                        	var resultJson =  eval('(' + result + ')');
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
        
        
        // 选择安全标准
        function addToLeftStandards(){
        	var selections = $("#standardGrid").datagrid("getSelections");
        	
        	if(selections && selections.length>0)
        	for(var i=0; i<selections.length; i++){
        		//左侧以选中的grid
        		var selectedRows = $("#selectedStandardsDataGrid").datagrid("getRows");
        		var isExist=false;
        		for(var j=0; j<selectedRows.length; j++){
        			if(selectedRows[j].id == selections[i].id){
        				isExist = true;
        				//提示已经存在
        				$.messager.show({
        					title:'提示',
        					msg:"<p style='color:orange; font-weight:bold; text-align:center;'>已经存在</p>",
        					timeout : 2000,
        					showType:'slide',
        					style:{
        						right:'',
        						top:document.body.scrollTop+document.documentElement.scrollTop+150,
        						bottom:''
        					}
        				});
        			}
        		}
        		if(!isExist){
        			$("#selectedStandardsDataGrid").datagrid("appendRow", selections[i]);
        		}
        		isExist = false;
        	}
        }
        //保存安全标准到页面
        function saveStandard(){
        	var selectedRows = $("#selectedStandardsDataGrid").datagrid("getRows");
        	var selectedStandardIds="";
        	for(var i=0; i<selectedRows.length; i++){
        		if(i==0){
        			selectedStandardIds=(selectedRows[i].id);
        		}else{
        			selectedStandardIds=(selectedStandardIds+","+selectedRows[i].id);
        		}
        	}
        	
        	$("#input_standard_id").val(selectedStandardIds);
        	
        	$('#standard-dlg').dialog('close');        // close the dialog
        }
        
        //删除已经选中的标准
        function deleteSelectedStandards(){
        	var selectedRows = $("#selectedStandardsDataGrid").datagrid("getSelections");
        	if(selectedRows && selectedRows.length>0){
        		for(var i=0; i<selectedRows.length; i++){
            		var index = $("#selectedStandardsDataGrid").datagrid("getRowIndex", selectedRows[i]);
            		$("#selectedStandardsDataGrid").datagrid("deleteRow", index);
            	}
        	}
        	

        }
        
        
        
      //更改执行周期事件
        function selectFrequencyComboBox(value){
         
        	switch(value) {
        		//单次
        		case '0':
        		 
        			$('#cycleFitem').hide();
        			$('#onceTimeFitem').hide();
        			$('#startTimeFitem').hide();
        			$('#endTimeFitem').hide();
        			break;
        		//按天
        		case '1':
        			$('#cycleFitem').show();
        			$('#onceTimeFitem').hide();
        			$('#cycleFitem').find("label").html('循环周期');
        			$('#startTimeFitem').show();
        			$('#endTimeFitem').show();
        			break;
        		//按周
        		case '2':
        			$('#cycleFitem').show();
        			$('#onceTimeFitem').hide();
        			$('#cycleFitem').find("label").html('按周几执行');
        			$('#startTimeFitem').show();
        			$('#endTimeFitem').show();
        			break;
        		//按月
        		case '3':
        			$('#cycleFitem').show();
        			$('#onceTimeFitem').hide();
        			$('#cycleFitem').find("label").html('按月第几天执行');
        			$('#startTimeFitem').show();
        			$('#endTimeFitem').show();
        			break;
        		//按季度
        		case '4':
        			$('#cycleFitem').show();
        			$('#onceTimeFitem').hide();
        			$('#cycleFitem').find("label").html('按季度第几天执行');
        			$('#startTimeFitem').show();
        			$('#endTimeFitem').show();
        			break;
        		//按年
        		case '5':
        			$('#cycleFitem').show();
        			$('#onceTimeFitem').hide();
        			$('#cycleFitem').find("label").html('按年第几天执行');
        			$('#startTimeFitem').show();
        			$('#endTimeFitem').show();
        			break;
        		default:
        			
        	}
        }
        
        
        var deviceDataGrid;
        
        
        //设备种类Combobox变换事件
        function objectToolbarChange(record){
        	if(record.value==1){
	        	 //初始化commonDevice
	           	 deviceDataGrid = $('#object1Id-dg').datagrid({
	   			    url:'/tzyc/safe/device/allWidthPagination?typeId=1',
	   			    method:'GET', 
	   			    title:"通用设备信息管理",
	   			    closed:false,
	   			    pagination:true,
	   	            rownumbers:true,
	   	            fitColumns:true,
	   	            singleSelect:true,
	   			    columns:[[
	   			        {field:'id',title:'Code',width:100, hidden:true},
	   			        {field:'code',title:'设备编码',width:100, sortable:true},
	   			        {field:'typeId',title:'设备分类',hidden:true},
	   			        {field:'name',title:'设备名称',width:100},
	   			        {field:'departId',title:'部门名称',width:100},
	   			        {field:'brand',title:'设备品牌',width:100},
	   			        {field:'model',title:'规格型号',width:100},
	   			        {field:'position',title:'位置',width:100},
	   			        {field:'application',title:'用途',width:100},
	   			        {field:'openingDate',title:'启用日期',width:100},
	   			        {
	   			        	field:'equipStatus',
	   			        	title:'设备状态',
	   			        	width:100,
	   			        	formatter:function(value,row,index){
	   			  				if (row.equipStatus==0){
	   			  					return "启用"
	   			  				} else if (row.equipStatus==1){
	   			  					return "禁用";
	   			  				}else{
	   			  					return "";
	   			  				}
	   			  			}
	   			        },
	   			        {field:'creatorName',title:'创建人',width:100},
	   			        {field:'productionDate',title:'生产日期',width:100},
	   			        {field:'amount',title:'数量',width:100}
	   			    ]]
	   			});
        	}else if(record.value==3){
        		
        		//初始化commonDevice
            	vehicleDataGrid = $('#object1Id-dg').datagrid({
    			    url:'/tzyc/safe/device/allWidthPagination?typeId=3',
    			    method:'GET', 
    			    title:"车辆信息管理",
    			    closed:false,
    			    pagination:true,
    	            rownumbers:true,
    	            fitColumns:true,
    	            singleSelect:true,
    			    columns:[[
    			        {field:'id',title:'Code',width:100, hidden:true},
    			        {field:'typeId',title:'设备分类',hidden:true},
    			        {field:'plateNumber',title:'号牌号码',width:100},
    			        {field:'vehicleType',title:'车辆型号',width:100},
    			        {field:'initialRegisteTime',title:'初次登记时间',width:100},
    			        {field:'engineCode',title:'发动机号',width:100},
    			        {field:'frameCode',title:'车架号',width:100},
    			        {field:'vehicleBookCode',title:'机动车登记书编号',width:100},
    			        {field:'annualInspection',title:'年检情况',width:100},
    			        {field:'openingDate',title:'启用日期',width:100},
    			        {field:'departId',title:'使用部门',width:100},
    			        {field:'productionDate',title:'生产日期',width:100},
    			        {field:'driver',title:'当前驾驶员',width:100},
    			        {field:'remark',title:'备注',width:100}
    			    ]]
    			});
        		
        		
        	}else if(record.value==4){
        		deviceDataGrid = $('#object1Id-dg').datagrid({
	   			    url:'/tzyc/safe/device/allWidthPagination?typeId=4',
	   			    method:'GET', 
	   			    title:"通用设备信息管理",
	   			    closed:false,
	   			    pagination:true,
	   	            rownumbers:true,
	   	            fitColumns:true,
	   	            singleSelect:true,
	   			    columns:[[
	   			        {field:'id',title:'Code',width:100, hidden:true},
	   			        {field:'code',title:'设备编码',width:100},
	   			        {field:'typeId',title:'设备分类',hidden:true},
	   			        {field:'name',title:'设备名称',width:100},
	   			        {field:'departId',title:'部门名称',width:100},
	   			        {field:'brand',title:'设备品牌',width:100},
	   			        {field:'model',title:'规格型号',width:100},
	   			        {
	   			        	field:'equipStatus',
	   			        	title:'设备状态',
	   			        	width:100,
	   			        	formatter:function(value,row,index){
	   			  				if (row.equipStatus==0){
	   			  					return "启用"
	   			  				} else if (row.equipStatus==1){
	   			  					return "禁用";
	   			  				}else{
	   			  					return "";
	   			  				}
	   			  			}
	   			        },
	   			        {field:'financialCode', title:'财务编码', width:100},
	   			     	{field:'position',title:'位置',width:100},
	   			     	{field:'openingDate',title:'启用日期',width:100},
	   			        {field:'creatorName',title:'创建人',width:100},
	   			        {field:'productionDate',title:'生产日期',width:100},
	   			        {field:'amount',title:'数量',width:100},
	   			     	{field:'remark',title:'备注',width:100}
	   			    ]]
	   			});
        	}else if(record.value==2){
        		deviceDataGrid = $('#object1Id-dg').datagrid({
	   			    url:'/tzyc/safe/device/allWidthPagination?typeId=2',
	   			    method:'GET', 
	   			    title:"通用设备信息管理",
	   			    closed:false,
	   			    pagination:true,
	   	            rownumbers:true,
	   	            fitColumns:true,
	   	            singleSelect:true,
	   			    columns:[[
	   			        {field:'id',title:'Code',width:100, hidden:true},
	   			        {field:'code',title:'设备编码',width:100},
	   			        {field:'typeId',title:'设备分类',hidden:true},
	   			        {field:'name',title:'设备名称',width:100},
	   			        {field:'departId',title:'部门名称',width:100},
	   			        {field:'brand',title:'设备品牌',width:100},
	   			        {field:'model',title:'规格型号',width:100},
	   			    	{field:'productionBatch',title:'生产批号',width:100},
	   			  		{field:'registrationCode',title:'注册代码',width:100},
	   			        {field:'position',title:'位置',width:100},
	   			        {field:'openingDate',title:'启用日期',width:100},
	   			        {
	   			        	field:'equipStatus',
	   			        	title:'设备状态',
	   			        	width:100,
	   			        	formatter:function(value,row,index){
	   			  				if (row.equipStatus==0){
	   			  					return "启用"
	   			  				} else if (row.equipStatus==1){
	   			  					return "禁用";
	   			  				}else{
	   			  					return "";
	   			  				}
	   			  			}
	   			        },
	   			        {field:'productionDate',title:'生产日期',width:100}
	   			    ]]
	   			});
        	}

        }
        
        
        //更改EvaluateItem（评价项目）时，出发事件
		function evaluateItemSelectChange(record){
			//console.log(record.value);
			$('#standardGrid').datagrid('load',{
				typeId: $("#evaluateType").combobox("getValue"),
				itemId: $("#evaluateItem").combobox("getValue")
			});
        }
        
        
        $(function(){
        	
        	//载入检查人员树形菜单的
        	getInspectorDataInJson();
        	
        	
    	    $('#evaluateType').combobox({
        		url: 'safe/evaluate/type/allTypesNoPagerInfo',
				method: 'get',
				valueField:'id',
				textField:'name',
				panelHeight:'auto',
				onSelect:function(record){
					$('#evaluateItem').combobox('reload', 'safe/evaluate/item/allWithoutPagerInfo?typeId='+record.id);
					$('#evaluateItem').combobox('setValue', '');
					$('#standardGrid').datagrid('load',{
						typeId: $("#evaluateType").combobox("getValue")
					});
				}
    	    	
        	});	
    	    
        	
        	$("#object_btn").click(function(){
        		objectToolbarChange({value:1});
        		$('#objectId-dlg').dialog('open').dialog('setTitle','选择检查对象');
        		if($("#input_object_id").val()){
        			$("#objectId-dg").datagrid("selectRecord", $("#input_object_id").val());
        		}
        		//getInspectorDataInJson();
        	});
        	
        	$("#inspectors_btn").click(function(){
        		loadInspectorsTree();
        		$('#inspector-dlg').dialog('open').dialog('setTitle','选择巡检员');
        		//getInspectorDataInJson();
        	});
        	
        	
        	//初始化评价标准选择
        	var selectedStandardDataGrid = $('#selectedStandardsDataGrid').datagrid({
			    url:'safe/inspect/standard/byStandardIds',
			    method:'POST', 
			    title:"已选择标准",
			    closed:false,
			    toolbar:"#selectedStandardsToolbar",
			    pagination:false,
	            rownumbers:true,
	            fit:true,
	            fitColumns:true,
	            singleSelect:true,
			    columns:[[
			        {field:'id',title:'Code', hidden:true},
			        {field:'typeName',title:'评价分类',width:50},
			        {field:'itemName',title:'评价项目',width:50},
			        {field:'decription',title:'评价描述',width:100}
			    ]]
			});
        	
        	
        	$("#standard_btn").click(function(){
        		$('#standard-dlg').dialog('open').dialog('setTitle','选择标准');
        		//loadDepartmentTree();
        		$('#selectedStandardsDataGrid').datagrid("load", {
        			ids:$("#input_standard_id").val()
        		});
        	});
        	
        });
    </script>
    
</body>
</html>