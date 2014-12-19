<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>7s检查结果</title>
<cmstudio:htmlBase />
<!--Start importing the jquery files -->
<cmstudio:importJsCss name="jquery" version="${jquery_version}" />
<!--End import the jquery files -->
<!--Start importing the jeasyui files -->
<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}" />
<!--End importing the jeasyui files -->
<!--Start importing the ztree files -->
	<cmstudio:importJsCss name="ztree" version="${ztree_version}"/>
	<!--End importing the ztree files -->
</head>
<body>
	<table id="dg" title="7s检查结果" class="easyui-datagrid"
		url="7sCheckResult/all" toolbar="#tb"  
		pagination="true" rownumbers="true" fitColumns="true"
		data-options="onCheck:function(index, row){
			if(row.statusFlag==1){
				showMessage('该条任务已经处理！');
				setTimeout(function(){ 
					$('#dg').datagrid('unselectRow',index);
			        $('#dg').datagrid('uncheckRow',index);
				 }, 200);
				return;
			}
			if(row.taskStatus==1){
				showMessage('该条任务正常，无需整改！');
				setTimeout(function(){ 
					$('#dg').datagrid('unselectRow',index);
			        $('#dg').datagrid('uncheckRow',index);
				 }, 200);
				return;
			}
			if(!row.inspectName){
				showMessage('该条任务还未检查！');
				setTimeout(function(){ 
					$('#dg').datagrid('unselectRow',index);
			        $('#dg').datagrid('uncheckRow',index);
				 }, 200);
				return;
			}
			
		}"
		singleSelect="false" >
		<thead>
			<tr>
				<!-- <th data-options="field:'ck',checkbox:true"></th> -->
				<th field="pointName" width="50">检查点</th>
				<th field="taskType" width="50"data-options='formatter:function(value,row,index){
                				switch(row.taskType){
                					case 0 : 
                						return "单次";
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
                				
        		  			}'>任务类型</th>  
        		  			
        		<th field="inspectStatus" width="50" data-options="align:'center',
					formatter: function(value,row,index){
						if (!row.inspectItem || row.inspectItem==''){
							 return '未检查';
						} else {
							 if(row.taskStatus==0){
							 	return '不合格';
							 }else if(row.taskStatus==1){
							 	return '全部合格';
							 }
						}
					}
				" >检查状态</th>
				<th field="taskResult" data-options="align:'center',
					formatter: function(value,row,index){
						if (row.taskStatus==1){
							return '一切正常';
						} else if(row.taskStatus==0){
							return formatHTML+row.taskResult+formatHTML1;
						}
					}
				" width="50">任务结果</th>
				<th field="filePath" width="50" data-options="align:'center',
					formatter: function(value,row,index){
						 
						if(!row.filePath || row.filePath.length<3){
			    			return '无图片';
			    		}
						return formatHTML3+row.filePath+formatHTML4;
						 
					}
				" >图片记录</th>
				<th field="inspectName" width="50">检查员</th>
				<th field="inspectItem" width="50">检查时间</th>
				<th field="taskStartTime" width="50">开始时间</th>   
                <th field="taskEndTime" width="50">结束时间</th>
                <th field="creatorName" width="50">创建者</th>
				<th field="taskCreateTime" width="50">创建时间</th>
				<th field="stausFlag" width="50" data-options="formatter: function(value,row,index){
						if(row.statusFlag==1){
							return '已处理整改';
						}else if(row.taskStatus==1){
							return '正常';
						}else if(row.taskStatus==0){
							return '未处理整改';
						}
					}">整改状态</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
       	开始时间: <input class="easyui-datebox" style="width:180px" readonly="readonly" id="startTime">
       	结束时间: <input class="easyui-datebox" style="width:180px" readonly="readonly" id="endTime">
        <a href="javascript:searchResult();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="length()">导出</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="rectify()">整改</a>
    </div>
	
	<div id="dialog-issueResult" closed="true" class="easyui-dialog" title="检查问题列表" style="width:800px;height:372px;"
	        data-options="iconCls:'icon-save',resizable:true,modal:true">
        <table id="standardErrorResultGrid"></table> 
	</div>
	
	<div id="dialog-images" data-options="maximizable:true" closed="true" class="easyui-dialog" toolbar="tbrectify" title="检查问题列表" style="padding:10px 0 0 0 ;text-align:center;width:800px;height:600px;"
	        data-options="iconCls:'icon-save',resizable:true,modal:true, buttons:'#images-dlg-buttons'">
	        <div id="inner-dialog-images"></div>
	        <div id="images-dlg-buttons">
			       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dialog-images').dialog('close')">关闭</a>
			</div>
	</div>
	
	<div id="tbrectify">
       	整改期限:<input class="easyui-datebox" style="width:180px" id="deadline">
		整改负责人:<input style="width:100px" required="true"  readonly="readonly" id="rectifier">
		<input type="hidden"  id="rectifierId">
		<input type="hidden"  id="rectifierOrgId">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" id="inspectors_btn">选择整改人</a>
    </div>
	<div id="rectifyDiv" class="easyui-dialog" title="整改内容" buttons="#rectifyBtns" closed="true" style="width:600px;height:300px;"
	        data-options="iconCls:'icon-save',resizable:true,modal:true, toolbar:'#tbrectify'">
	    <table id="rectifyDivDg"></table>
	    
	    <div id="rectifyBtns">
			<a href="javascript:submitRectify();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" >确认</a>
			<a href="javascript:$('#rectifyDiv').dialog('close');" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" >关闭</a>
		</div>
	</div>
	
	<!-- 人员选择 -->
	<div id="inspector-dlg" class="easyui-dialog" data-options="closed:'true',buttons:'#inspector-dlg-buttons', modal:true">
        <div style="height:400px;width:300px;" class="ztree" id="div_inspectors_tree"></div>
    </div>
    <div id="inspector-dlg-buttons">
	       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveInspectors()">确定</a>
	       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#inspector-dlg').dialog('close')">取消</a>
	</div>
	
	
	
	<script type="text/javascript">
	function showMessage(message){
		$.messager.show({    // show error message
            title: '<span style="color:red;">提示</span>',
            msg: "<div style='text-align:center;margin-top:10px;'>"+message+"</div>",
            style:{
        		right:'',
        		top:document.body.scrollTop+document.documentElement.scrollTop,
        		bottom:''
        	}
        });
	}
	//提交整改的数据
	function submitRectify(){
		 var selections = $('#rectifyDivDg').datagrid("getRows");
		 if(selections.length<1){
			 $.messager.alert('提示','未选择任务！');
			 return;
		 }
		 
		 var taskDetails = "";
		 for(var i=0; i<selections.length;i++){
			 taskDetails+=(selections[i].id+";");
		 }
		 
		$.messager.confirm('提示','确定提交所选任务？',function(r){
		    if (!r){
		        return;
		    }
		    var data = {};
		    data.checker = $("#rectifierId").val();
		    data.deadline =$('#deadline').datebox('getValue')+" 00:00:00";	 //$("#").val();
		    data.checkerorg= $("#rectifierOrgId").val();
		    data.taskDetails = taskDetails;
		    
		   $.post("SsProblemRectify/save", data, function(result){
			   var resultObj = eval("("+result+")");
			   if(resultObj && resultObj.suc){
				   $('#rectifyDiv').dialog('close');
				   showMessage("提交成功！");
				   $("#dg").datagrid("reload");
			   }else{
				   showMessage("提交失败！");
			   }
		   }); 
		    
		});
	}	
	//选取整改的任务条目
	function rectify(){
		$('#rectifyDiv').dialog('open');
		//获取当选中的行
		var selections = $('#dg').datagrid("getSelections");
		var allRows = $('#rectifyDivDg').datagrid("getRows");
		var rectifyLength = allRows.length;
		for(var j=0; j<rectifyLength; j++){
			$("#rectifyDivDg").datagrid("deleteRow",0);
				
		}

		//录入到当前弹出的dialog
		if(selections.length>0){
			for(var i=0; i<selections.length; i++){
				$('#rectifyDivDg').datagrid("appendRow",selections[i]);
			}
		}
		
		//可以删除当前行
		
	}
	
	
	 function length(){
	    	//取到开始,结束时间
			var startDate = $("#startTime").datebox("getValue");  
			var endDate = $("#endTime").datebox("getValue");
			
			//格式化开始,结束时间
			var start=new Date(startDate.replace("-", "/").replace("-", "/"));  
			var end=new Date(endDate.replace("-", "/").replace("-", "/"));  
	
	    	$.post('7sInspectTask/exportExcel',{
	    		exportfield:[1,3,3],
	    		exportheaders:[1,3,3],
	    		startDate : startDate,
				endDate : endDate,
				
	    		title:"7S检查任务"
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
	
		function searchResult(){
			$("#dg").datagrid("load", {
			    startTime: $('#startTime').datebox('getValue'),
			    endTime: $('#endTime').datebox('getValue')
			});
		}
	
		/* function length() {
			var exportfield = [];
			var exportheaders = [];
			var length = $("#dg").datagrid('options').columns[0].length;
			for (var j = 0; j < length; j++) {
				var obj = $("#dg").datagrid('options').columns[0][j];
				if (!obj.hidden) {
					exportfield.push(obj.field);
					exportheaders.push(obj.title);
				}
			}
			var title = $("#dg").datagrid('options').title;
			$.post('room/getlist', {
				exportfield : exportfield,
				exportheaders : exportheaders,
				title : title
			}, function(result) {
				var result = eval('(' + result + ')');
				if (result.suc) {
					location.href = $("base").attr("href")
							+ "resources/download/report.xls";
				} else {
					$.messager.show({ // show error message
						title : '错误',
						msg : result.errorMsg
					});
				}
			});
		} */

		var url;
		function newObject() {
			$('#dlg').dialog('open').dialog('setTitle', '新建');
			$('#fm').form('clear');
			url = 'logistic/save?typeId=4';
		}
		function editObject() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '修改');
				$('#fm').form('load', row);
				url = 'logistic/update?id=' + row.id;
			}
		}
		function saveObject() {
			$('#fm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.errorMsg) {
						$.messager.show({
							title : 'Error',
							msg : result.errorMsg
						});
					} else {
						$('#dlg').dialog('close'); // close the dialog
						$('#dg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyObject() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('删除', '确定删除吗', function(r) {
					if (r) {
						$.post('logistic/delete', {
							id : row.id
						}, function(result) {
							if (result.suc) {
								$('#dg').datagrid('reload'); // reload the user data
							} else {
								$.messager.show({ // show error message
									title : 'Error',
									msg : result.errorMsg
								});
							}
						}, 'json');
					}
				});
			}
		}
		
 
		
		function getData(){
           
            return rows;
        }
        
        function pagerFilter(data){
            if (typeof data.length == 'number' && typeof data.splice == 'function'){    // is array
                data = {
                    total: data.length,
                    rows: data
                }
            }
            var dg = $(this);
            var opts = dg.datagrid('options');
            var pager = dg.datagrid('getPager');
            pager.pagination({
                onSelectPage:function(pageNum, pageSize){
                    opts.pageNumber = pageNum;
                    opts.pageSize = pageSize;
                    pager.pagination('refresh',{
                        pageNumber:pageNum,
                        pageSize:pageSize
                    });
                    dg.datagrid('loadData',data);
                }
            });
            if (!data.originalRows){
                data.originalRows = (data.rows);
            }
            var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
            var end = start + parseInt(opts.pageSize);
            data.rows = (data.originalRows.slice(start, end));
            return data;
        }
        
        var standardResultDataGrid;
    	
    	function showIssueResults(taskResult){
    		
    		$("#dialog-issueResult").dialog("open");
    		
    		 //初始化commonDevice
          	 standardResultDataGrid = $('#standardErrorResultGrid').datagrid({
    			    url:'7sInspectStandard/findAllErrorStandardWithOptions',
    			    method:'GET', 
    			    queryParams:{
    			    	ids:taskResult
    			    },
    			    title:"7S检查标准问题列表",
    			    closed:false,
    			    pagination:true,
    			    loadFilter:pagerFilter,
    	            rownumbers:true,
    	            fitColumns:true,
    	            singleSelect:true,
    			    columns:[[
    			        {field:'standardDesc',title:'标准描述',width:200},
    			        {field:'remark',title:'备注',width:100},
    			        {field:'recordStatus',title:'状态',width:100,formatter: function(value,row,index){
    						return "不合格"
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
    	var formatHTML="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-search' onclick='showIssueResults(\"";
    	var formatHTML1 = "\")'>检查问题详细</a>";
    	//检查图片详细查看
    	var formatHTML3="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-search' onclick='showResultsImages(\"";
    	var formatHTML4 = "\")'>查看</a>";
    	
		var deleteCurrentRow = function(){
			var index = $("#rectifyDivDg").datagrid("getSelected");
		}
    	
    	$(function(){
    		
    		//初始化整改负责人数据
    		getInspectorDataInJson();
    		
    		$("#inspectors_btn").click(function(){
        		loadInspectorsTree();
        		$('#inspector-dlg').dialog('open').dialog('setTitle','选择巡检员');
        	});

    		
    		
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
    		        {field:'pointName',title:'检查点名称',width:100},
    		        {field:'inspectName',title:'检查员',width:100},
    		        {field:'taskResult',title:'检查结果',width:100,
    		        	formatter: function(value,row,index){
						if (row.taskStatus==1){
							return '一切正常';
						} else if(row.taskStatus==0){
							return formatHTML+row.taskResult+formatHTML1;
						}
					}},
    		        {field:'inspectItem',title:'检查时间',width:100},
    		        {field:'delete',title:'删除',width:100, align:'center', formatter:function(value, row, index){
    		        	return  "<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-search'>删除</a>";
    		        }}
    		    ]]
    		});
    		
    		
    		
    	});
    	
    	
    ////////////////选择检查人
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
    			chkStyle: "radio"
    		}
	    };
	    
	     
	     //Load all the menu Tree
	     function loadInspectorsTree(){
    		  //Load the system manage tree
    		  allInspectorsTreeObj = $.fn.zTree.init($("#div_inspectors_tree"), allInspectorsTreeSetting, inspectorsAllInJson);
    		  //rename the
    		 /*  var rootNode = allInspectorsTreeObj.getNodeByParam("id","1");
    		  rootNode.name = $("title").html(); */
    		  allInspectorsTreeObj.refresh();
    		  allInspectorsTreeObj.expandAll(true);
    		  
	    		//载入form下name中的值
	    		var inspectors = $("#rectifierId").val();
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
	               $("#objectName").val(row.pointName);
	               $("#objectId-dlg").dialog("close");
	            } 
	     }
	     
	     
	     //确定选择的用户
	     function saveInspectors(){
	    	 var nodes = allInspectorsTreeObj.getCheckedNodes();
	    	 var inspectorNames;
	    	 var inspectorIds;
	    	 /* for(var i=0; i<nodes.length; i++){
	    		 if(i==0){
	    			 inspectorNames=nodes[i].name;
	    			 inspectorIds = (nodes[i].id-100000);
	    		 }else{
	    			 inspectorNames=(nodes[i].name+',')+inspectorNames;
	    			 inspectorIds = ((nodes[i].id-100000)+',')+inspectorIds;
	    		 }
	    	 } */
	    	 if(nodes.length>1){
	    		 $.messager.show({    // show error message
                    title: '提示',
                    msg: '只能选择一位负责人！',
                    style:{
                 		right:'',
                 		top:document.body.scrollTop+document.documentElement.scrollTop,
                 		bottom:''
                 	}
                });
	    		 return;
	    	 }
	    	// $("#inspectorNames").val(inspectorNames);
	    	 $("#rectifier").val(nodes[0].name);
	    	 $("#rectifierId").val(nodes[0].id-100000);
	    	 var parentNode = nodes[0].getParentNode().id;
	    	 $("#rectifierOrgId").val(parentNode);
	    	 $('#inspector-dlg').dialog('close');
	     }
    

    	
    	
	</script>
	<style type="text/css">
		#fm {
			margin: 0;
			padding: 10px 30px;
		}
		
		.ftitle {
			font-size: 14px;
			font-weight: bold;
			padding: 5px 0;
			margin-bottom: 10px;
			border-bottom: 1px solid #ccc;
		}
		
		.fitem {
			margin-bottom: 5px;
		}
		
		.fitem label {
			display: inline-block;
			width: 80px;
		}
	</style>
</body>
</html>