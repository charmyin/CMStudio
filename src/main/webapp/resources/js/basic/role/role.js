/***
 * author: charmyin
 * datetime: 2013-2-26 21:00
 * title: Control the organizationManage.jsp ~
 ***/

//Global the item name
var itemName="角色";

//Global all organization treeObj and setting
var selectedNodeId;//The selected node's object id of left tree

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
				
				//Get the id of selected node, and set it to global variable selectedNodeId
				selectedNodeId = node.id;
				$("#roleGrid").datagrid({
					url:'role/orgId/'+node.id+'/all', 
					loadFilter:pagerFilter,
					method:'get',
					toolbar:'#toolbar',
					pagination:true,
					collapsible:true,
					title:"角色管理&nbsp----&nbsp"+node.name,
					rownumbers:true,
					singleSelect:false,
					pageSize:8,
				    pageList:[8,16,32,48,64],
					columns:[[
					          {field:'ck', checkbox:true },
					          {field:'name', title:'名称'},
					          {field:'description', title:'描述'},
					          {field:'permission', title:'权限'},
					          {field:'remark', title:'备注'}
					]],
					onLoadError: function(msge){
						$.messager.alert('错误信息','服务器连接已断开或服务器内部错误！','error');
					}
				});
				
				//Load Menu permission grid
				$("#permissionGrid").datagrid({
					iconCls: 'icon-edit',
					singleSelect: true,
					rownumbers:true,
					toolbar: '#permissionGridTB',
					method: 'get',
					url:'',
					columns:[[
				       {field:'permission',title:'Shiro权限',width:100, align:'center',editor:{
				    	   type:'validatebox',
				    	   options:{
                               required:true
                           }
				       }},
				       {field:'remark',title:'说明',width:140, align:'center', editor:'text'},
				       {field:'menuId',title:'菜单ID', hidden:true}
					]],
					onClickRow: onClickRow
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
	  //sometime it request the server twice, I wonder~
	  $("#"+selectedNode.tId+"_a").trigger("click");
	});
}

//Used for client pagination
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




/********************************************************Initial the page*****************************************************/
$(function(){
	//Disable cache
	jQuery.ajaxSetup({ cache: false });
	//载入成功后，刷新左边树
	//Load the organization tree
	loadOrganizationTree();
	//Load grid
});

//OrganizationCrud dialog
var url;

//Initial the parentId
function initParentId(){
	//Input the value and hidden value of parentId input 
	var selectedNode = allOrganizationTreeObj.getNodeByParam("id",selectedNodeId);
	$("#hidden_organizationId").val(selectedNodeId);
	$("#input_organizationId").val(selectedNode.name);
}

function newForm(){
	//清空权限grid
	$('#permissionGrid').datagrid('loadData',[]);
	
    $('#dlg').dialog('open').dialog('setTitle','新建'+itemName+'');
    $('#fm').form('clear');
    initParentId();
    url = 'role/save';
}
function editForm(){
    var row = $('#roleGrid').datagrid('getSelected');
    initParentId();
    if (row){
    	//如果permission含有值，则载入，否则清空
    	if(row.permission){
    		//载入权限
        	$('#permissionGrid').datagrid('loadData',eval(row.permission));
    	}else{
    		//清空权限grid
    		$('#permissionGrid').datagrid('loadData',[]);
    	}
        $('#dlg').dialog('open').dialog('setTitle','修改'+itemName+':'+row.name);
        $('#fm').form('load',row);
        url = 'role/update';
    }
}
function saveForm(){
	
	//获取权限json字符串，如果校验未通过返回false
	var permissionString = getPermissionString();
	if(permissionString){
		$("#hidden_permission").val(permissionString);
	}else{
		return false;
	}
	
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
        	//组件验证，未通过则返回false
        	return $(this).form('validate');
        },
        success: function(resultString){
        	var result = eval("("+resultString+")");
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
                
                //Get the organizationId of organizationTree, Used to auto click the left tree element~ 
                selectedNodeId = $("#hidden_organizationId").val();
                //Reload left tree and refresh the datagrid
                loadOrganizationTree();
            }
        }
    });
}
function destroySelectedItems(){
    var rows = $('#roleGrid').datagrid('getSelections');
    var rowsLength = rows.length;
    if (rowsLength>0){
        $.messager.confirm('提示信息','确定删除选中'+itemName+'？',function(r){
            if (r){
            	var namesString='';
            	for(var i=0; i<rows.length; i++){
            		if((i+1)==rowsLength){
            			namesString+=rows[i].name;
            		}else{
            			namesString+=(rows[i].name+',');
            		}
            	}
            	$.post('role/deleteByNames',{names:namesString},function(result){
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

