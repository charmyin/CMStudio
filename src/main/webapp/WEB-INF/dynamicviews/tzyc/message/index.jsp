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
	<script type="text/javascript">

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
    <script type="text/javascript">
    
    	function loadInitConfigInfo(){
    		$.ajax({ 
    		     type:"POST",
	             url: "messageConfig/getConfigInJson", 
	             dataType: "json", 
	             success: function (data) { 
	            	 //$('#dlg').dialog('open').dialog('setTitle','修改');
	                 $('#fm').form('load',data);
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
			  allInspectorsTreeObj = $.fn.zTree.init($("#div_receivers_tree"), allInspectorsTreeSetting, inspectorsAllInJson);
			  //rename the
			 /*  var rootNode = allInspectorsTreeObj.getNodeByParam("id","1");
			  rootNode.name = $("title").html(); */
			  allInspectorsTreeObj.refresh();
			  allInspectorsTreeObj.expandAll(true);
			  
	    		//载入form下name中的值
	    		var inspectors = $("#input_receivers_ids").val();
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

	     //确定选择的用户
	     function saveReceivers(){
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
	    	 // $("#inspectorNames").val(inspectorNames);
	    	 $("#input_receivers_ids").val(inspectorIds);
	    	 $('#inspector-dlg').dialog('close');
	     }

        
	        function saveObject(){
	        	
	            $('#fm').form('submit',{
	                url: 'messageConfig/save',
	                onSubmit: function(){
	                    return $(this).form('validate');
	                },
	                success: function(result){
	                    var result = eval('('+result+')');
	                    if (result.errorMsg){
	                        $.messager.show({
	                            title: '错误',
	                            msg: result.errorMsg
	                        });
	                    } else {
	                    	$.messager.show({
	                            title: '更新成功',
	                            style:{
	        						right:'',
	        						top:document.body.scrollTop+document.documentElement.scrollTop+150,
	        						bottom:''
	        					},
	                            msg: '短信接口配置提交成功!'
	                        });
	                    }
	                }
	            });
	        }
	     
	        
	        
    
    	$(function(){
    		//载入初始值
    		loadInitConfigInfo();
    		
    		
        	//载入检查人员树形菜单的
        	getInspectorDataInJson();

    		
    		$("#inspectors_btn").click(function(){
        		loadInspectorsTree();
        		$('#inspector-dlg').dialog('open').dialog('setTitle','选择巡检员');
        		//getInspectorDataInJson();
        	});
    		
    		
    		//载入初始数据
    		 
    		
    		
    	});
    </script>
</head>
<body>
 	<div style="text-align:center;width:410px;margin:50px auto 0 auto; ">
	    <div id="dlg" class="easyui-panel" title="短信接口配置" style="width:400px;height:410px;padding:10px 20px;" buttons="#dlg-buttons" >
	      <!--   <div class="ftitle">用户信息</div> -->
	        <form id="fm" method="post" novalidate>
	            <div class="fitem">
	                <label>IP地址:</label>
	                <input name="ip" class="easyui-validatebox" data-options="required:true,validType:['length[0,60]']">
	            </div>
	            <div class="fitem">
	                <label>端口:</label>
	                <input name="port" class="easyui-validatebox" data-options="required:true,validType:['length[0,60]']">
	            </div>
	            <div class="fitem departmentDiv">
	                <label>上下文:</label>
	             	<input name="context" class="easyui-validatebox" data-options="validType:['length[0,60]']">
	            </div>
	            <div class="fitem">
	                <label>页面:</label>
	                <input name="page"  class="easyui-validatebox" data-options="validType:['length[0,60]']">
	            </div>
	            <div class="fitem" >
	                <label>登录码:</label>
	                <input name="logoCode"  class="easyui-validatebox" data-options="validType:['length[0,60]']">
	            </div>
	            <div class="fitem">
	                <label>消息标签:</label>
	                <input name="messageFlag" class="easyui-validatebox" data-options="validType:['length[0,60]']">
	            </div>
	            <div class="fitem">
	                <label>模块名:</label>
	                <input name="moduleName" class="easyui-validatebox" data-options="validType:['length[0,60]']">
	            </div>
	            <div class="fitem">
	                <label>源手机:</label>
	                <input name="sourcePhone" class=easyui-validatebox >
	            </div>
	            <div class="fitem" style="margin-top:15px;">
                	<label>接收人员:</label>
	                <!--  <textarea name="inspectorNames" id="inspectorNames" disabled="disabled"></textarea> -->
	                <input name="receivers" id="input_receivers_ids" type="hidden"> 
	                <a id="inspectors_btn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">选择</a>
	            </div>
	        </form>
	        <hr/>

	        <div id="dlg-buttons" style="text-align:center;margin-top:15px;">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveObject()">保存</a>
		    </div>
	    </div>
    </div>
 
    
    
   <!-- 人员选择 -->
	<div id="inspector-dlg" class="easyui-dialog" data-options="closed:'true',buttons:'#objectId-dlg-buttons', modal:true">
        <div style="height:400px;width:300px;" class="ztree" id="div_receivers_tree"></div>
    </div>
	<div id="objectId-dlg-buttons">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveReceivers()">确定</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#inspector-dlg').dialog('close')">取消</a>
	</div>
 
</body>
</html>