/***
 * author: charmyin
 * datetime: 2013-2-26 21:00
 * title: Control the organizationManage.jsp ~
 ***/

//Global the item name
var itemName="用户";

//Global all organization treeObj and setting
var selectedNodeId;
var allOrganizationTreeObj;
var allOrganizationTreeSetting = {
		data:{
			simpleData:{
				enable:true,
				idKey:"id",
				pIdKey:"parentId"
			}
		},
		callback:{
			onClick:function (event,treeId,node) {
				//Load organizationGrid by selected tree node's id as parentId
//				$("#organizationGrid").datagrid({
//					url:'organizationparent/'+node.id+'/all'
//				});
				selectedNodeId = node.id;
				//初始化新增和修改页面的隐藏值
				initParentId();
				$("#userGrid").datagrid({
					url:'user/organizationId/'+node.id+'/allUser', 
					loadFilter:pagerFilter,
					method:'get',
					toolbar:'#toolbar',
					pagination:true,
					collapsible:true,
					title:"用户管理&nbsp----&nbsp"+node.name,
					rownumbers:true,
					singleSelect:false,
					pageSize:8,
				    pageList:[8,16,32,48,64],
					columns:[[
					          {field:'ck', checkbox:true },
					          {field:'id', title:'编号' },
					          {field:'loginId', title:'登录名'},
					          {field:'name', title:'昵称'},
					          {field:'cellPhone', title:'电话号码'},
					          {field:'sex', title:'性别',formatter:function(value,row,index){
	        		  				if (row.sex==0){
	        		  					return "男"
	        		  				} else if (row.sex==1){
	        		  					return "女";
	        		  				}else{
	        		  					return "";
	        		  				}
	        		  			}},
					          //{field:'dateCreated', title:'注册日期'},
					          //{field:'state', title:'是否禁用'},
					          {field:'remark', title:'备注'}
					]],
					onLoadError: function(msge){
						$.messager.alert('错误信息','服务器连接已断开或服务器内部错误！','error');
					}
				});
			}
		}
};

//Load all the organization Tree
function loadOrganizationTree(){
	$.ajax({
	  type: "GET",
	  url: "organization/all"
	}).done(function( msg ) {
		for(var i=0; i<msg.length; i++){
			msg[i].organizationType==0 ? (msg[i].isParent = false) : (msg[i].isParent = true);
		}
	  //Load the system manage tree
	  allOrganizationTreeObj = $.fn.zTree.init($("#div_allOrganization_tree"), allOrganizationTreeSetting, msg);
	 //rename the
	  var rootNode = allOrganizationTreeObj.getNodeByParam("id","1");
	  rootNode.name = $("title").html();
	  allOrganizationTreeObj.refresh();
	  //if selected a node, then append it ,else append the root node 
	  var selectedNode;
	  if(selectedNodeId){
		  selectedNode = allOrganizationTreeObj.getNodeByParam("id",selectedNodeId);
	  }else{
		  selectedNode = allOrganizationTreeObj.getNodes()[0];
	  }
	  allOrganizationTreeObj.expandNode(selectedNode,true,false,false,false);
	  //Select the node which id is selectedNodeId, then trigger the click event on it
	  allOrganizationTreeObj.selectNode(selectedNode);
	  //sometime it require the server twice, I wonder~
	  $("#"+selectedNode.tId+"_a").trigger("click");
	});
}

//Used for client pagination
function pagerFilter(data){
    if (typeof data.length == 'number' && typeof data.splice == 'function'){    // is array
        data = {
            total: data.length,
            rows: data
        };
    }
    var dg = $(this);
    var opts = dg.datagrid('options');
    var pager = dg.datagrid('getPager');
    pager.pagination({
    	onBeforeRefresh:function(){
    		dg.datagrid("reload");
    	},
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

//载入
function loadRolesForChoose(){
	var selectedNode = allOrganizationTreeObj.getNodeByParam("id",selectedNodeId);
	var htmlInner = "";
	//The first Get all the role belong to the same organization
	$.ajax({
	  type: "GET",
	  url: "role/orgId/"+selectedNode.id+"/all"
	}).done(function( msg ) {
		if(url.indexOf('update')>-1){
			//Get the uset id
			var row = $('#userGrid').datagrid('getSelected');
			//The second request the role of the user
			$.ajax({
			  type: "GET",
			  url: "user/"+row.id+"/roleNames"
			}).done(function( msg ) {
				//Check the checkboxes which 
				$(".roleChooseClass").each(function(){
					for(var i=0; i<msg.length; i++){
						if($(this).val()==msg[i]){
							$(this).attr("checked","checked");
						}
					}
				});
			});
		}
		
		
		
		//alert(msg[0].name);
		htmlInner+="<br/><br/>";
		for(var i=0; i<msg.length; i++){
			htmlInner+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" class="roleChooseClass" value="'+msg[i].name+'"/>'+msg[i].name+'<br/>';
		}
		$("#innerRoleChoose").html(htmlInner);
	});
}


/********************************************************Initial the page*****************************************************/
$(function(){
	
	//Disable cache
	jQuery.ajaxSetup({ cache: false });
	//载入成功后，刷新左边树
	//Load the organization tree
	loadOrganizationTree();
	//Load grid
	//选择用户角色
	$("#btn_roles").click(function(){
		$('#role-dlg').dialog('open').dialog('setTitle','选择角色');
		loadRolesForChoose();
	});
});

//OrganizationCrud dialog
var url;

//Initial the parentId
function initParentId(){
	//Input the value and hidden value of parentId input 
	var selectedNode = allOrganizationTreeObj.getNodeByParam("id",selectedNodeId);
	$("#hidden_organizationId").val(selectedNodeId);
	$("#input_organizationName").val(selectedNode.name);
}

function newForm(){
    $('#dlg').dialog('open').dialog('setTitle','新建'+itemName+'');
    $("#div_initPassphrase").hide();
    $("#input_loginId").removeAttr("readonly");
    $('#fm').form('clear');
    initParentId();
    $('#sexCombox').combobox('setValue', 0);
    url = 'user/save';
}
function editForm(){
	
    var row = $('#userGrid').datagrid('getSelected');
    
  //载入角色到input_role
	$.ajax({
	  type: "GET",
	  url: "user/"+row.id+"/roleNames"
	 // url: "role/getRolesByUserId/"+row.id
	}).done(function( msg ) {
		var roleString='';
		for(var i =0; i<msg.length;i++){
			if(i==(msg.length-1)){
				roleString+=msg[i]
			}else{
				roleString+=(msg[i]+',');
			}
			
		}
		$("#input_role").val(roleString);
		if (row){
	    	initParentId();
	    	$("#input_loginId").attr("readonly","readonly");
	        $('#dlg').dialog('open').dialog('setTitle','修改'+itemName+':'+row.loginId);
	        $("#div_initPassphrase").show();
//	        获取所属群组，修改时显示
//	        var nodes=allOrganizationTreeObj.getSelectedNodes();
	        $('#fm').form('load',row);
	        url = 'user/update?id='+row.id;
	    }
	});
    
    
}

function saveForm(){
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
        	//组件验证，未通过则返回false
        	return $(this).form('validate');
        },
        success: function(resultString){
        	var result;
        	try{
        		 result = eval("("+resultString+")");
        	}catch(error){
        		$.messager.show({
                	title: '提示',
                    msg: resultString,
                    style:{
                		right:'',
                		top:document.body.scrollTop+document.documentElement.scrollTop,
                		bottom:''
                	}
                });
        		return;
        	}
        	
            if (result.errorMsg){
                $.messager.show({
                	title: '提示<span style="color:red;">!</span>',
                    msg: "<div style='text-align:center;margin-top:10px;'>"+result.errorMsg+"</div>",
                    style:{
                		right:'',
                		top:document.body.scrollTop+document.documentElement.scrollTop,
                		bottom:''
                	}
                });
            } else {
                $('#dlg').dialog('close');        // close the dialog
                
                $.messager.show({
                	title: '提示',
                    msg: "<div style='text-align:center;margin-top:10px;'>保存成功!</div>",
                    style:{
                		right:'',
                		top:document.body.scrollTop+document.documentElement.scrollTop,
                		bottom:''
                	}
                });
                
                selectedNodeId = $("#hidden_organizationId").val();
                //Reload left tree and refresh the datagrid
                loadOrganizationTree();
            }
        }
    });
}

function destroySelectedItems(){
    var rows = $('#userGrid').datagrid('getSelections');
    var rowsLength = rows.length;
    if (rowsLength>0){
        $.messager.confirm('提示信息','确定删除选中'+itemName+'？',function(r){
            if (r){
            	var idsString='';
            	for(var i=0; i<rows.length; i++){
            		if((i+1)==rowsLength){
            			idsString+=rows[i].id;
            		}else{
            			idsString+=(rows[i].id+',');
            		}
            	}
            	$.post('user/deleteByIds',{ids:idsString},function(result){
                    if (result.suc){
                    	$.messager.show({
                        	title: '提示',
                            msg: "<div style='text-align:center;margin-top:10px;'>删除成功!</div>",
                            style:{
                        		right:'',
                        		top:document.body.scrollTop+document.documentElement.scrollTop,
                        		bottom:''
                        	}
                        });
                    	//Reload left tree and refresh the datagrid
                    	loadOrganizationTree();
                    } else {
                        $.messager.show({    // show error message
                            title: '提示<span style="color:red;">!</span>',
                            msg: "<div style='text-align:center;margin-top:10px;'>"+result.errorMsg+"</div>",
                            style:{
                        		right:'',
                        		top:document.body.scrollTop+document.documentElement.scrollTop,
                        		bottom:''
                        	}
                        });
                    }
                });
            }
        });
    }else{
    	$.messager.show({    // show error message
            title: '提示<span style="color:red;">!</span>',
            msg: "<div style='text-align:center;margin-top:10px;'>请选择要删除的行！</div>",
            style:{
        		right:'',
        		top:document.body.scrollTop+document.documentElement.scrollTop,
        		bottom:''
        	}
        });
    }
}

//保存选择的role到form隐藏input中
var saveRole = function(){
	var roles="";
	$(".roleChooseClass").each(function(){
		if($(this).is(':checked')){
			roles+=($(this).val()+',');
		}
	});
	if(roles!=""){
		roles = roles.substring(0,roles.length-1);
	}
	$("#input_role").val(roles);
	$('#role-dlg').dialog('close');
};


