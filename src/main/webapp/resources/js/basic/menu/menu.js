/***
 * author: charmyin
 * datetime: 2013-2-26 21:00
 * title: Control the menuManage.jsp ~
 ***/
//the tree used for choosing a parent menu 
var selectSingleMenuTreeObj;

//Global all menu treeObj and setting
var selectedNodeId;
var allMenuTreeObj;
var allMenuTreeSetting = {
		data:{
			simpleData:{
				enable:true,
				idKey:"id",
				pIdKey:"parentId"
			}
		},
		callback:{
			onClick:function (event,treeId,node) {
				//Load menuGrid by selected tree node's id as parentId
				//Set the node id which by clicked~
				selectedNodeId = node.id;
				
				//Load grid
				$("#menuGrid").datagrid({
						url:'menuparent/'+node.id+'/menu',
						loadFilter:pagerFilter,
						method:'get',
						toolbar:'#toolbar',
						pagination:true,
						collapsible:true,
						title:"菜单资源管理&nbsp----&nbsp"+node.name,
						rownumbers:true,
						singleSelect:false,
						pageSize:8,
					    pageList:[8,16,32,48,64],
						columns:[[
						          {field:'ck', checkbox:true },
						          {field:'id', title:'菜单编号' },
						          {field:'name', title:'名称'},
						          {field:'parentId', title:'父级菜单'},
						          {field:'linkUrl', title:'链接地址'},
						          {field:'orderNumber', title:'排序'},
						          {field:'remark', title:'备注'}
						]],
						onLoadError: function(msge){
							$.messager.alert('错误信息','服务器连接已断开或服务器内部错误！','error');
						}
					});
					
				//Load Menu permission grid
				/*$("#menuPermissionGrid").datagrid({
					iconCls: 'icon-edit',
					singleSelect: true,
					rownumbers:true,
					toolbar: '#menuPermissionGridTB',
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
				});*/
			}
		}
};

//Load all the menu Tree
function loadMenuTree(){
	$.ajax({
	  type: "GET",
	  url: "menu/all"
	}).done(function( msg ) {
	  //Load the system manage tree
	  allMenuTreeObj = $.fn.zTree.init($("#div_allMenu_tree"), allMenuTreeSetting, msg);
	 //rename the
	  var rootNode = allMenuTreeObj.getNodeByParam("id","1");
	  rootNode.name = $("title").html();
	  allMenuTreeObj.refresh();
	  //if selected a node, then append it ,else append the root node 
	  var selectedNode;
	  if(selectedNodeId){
		  selectedNode = allMenuTreeObj.getNodeByParam("id",selectedNodeId);
	  }else{
		  selectedNode = allMenuTreeObj.getNodes()[0];
	  }
	  allMenuTreeObj.expandNode(selectedNode,true,false,false,false);
	  //Select the node which id is selectedNodeId, then trigger the click event on it
	  allMenuTreeObj.selectNode(selectedNode);
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

//When choosed the parent menu, click "sure", then trigger this event
function selectTreeNodeSure(){
	//Find the checked node, give the id and name to input, then close the dialog~
	var nodes = selectSingleMenuTreeObj.getCheckedNodes();
	//If select nothing, do nothing on clicking sure~
	if(nodes.length==0){
		return false;
	}
	$("#input_parentId").val(nodes[0].name);
	$("#hidden_parentId").val(nodes[0].id);
	$("#input_parentId").focus();
	$("#div_winSelectParentMenu").dialog("close");
}


/********************************************************Initial the page*****************************************************/
$(function(){
	//Disable cache
	jQuery.ajaxSetup({ cache: false });
	//载入成功后，刷新左边树
	//Load the menu tree
	loadMenuTree();
	
	//Show parentId select dialog;Select the parentId
	$("#btn_parentId").click(function(){
		//Initial the tree
		var nodes = allMenuTreeObj.getNodes();
		var setting = {
						   view: {
							   selectedMulti: false
						   },
						   check:{
							   enable:true,
							   chkStyle:"radio",
							   radioType:"all"
						   }
					   };
		selectSingleMenuTreeObj = $.fn.zTree.init($("#div_SelectSingleMenu_tree"), setting, nodes);
		//Open the dialog
		$("#div_winSelectParentMenu").dialog("open").dialog('setTitle','选择父级菜单');
		//expand the node of selectSingleMenuTreeObj
		var selectedNodeTemp = selectSingleMenuTreeObj.getNodeByParam("id",selectedNodeId);
		selectSingleMenuTreeObj.expandNode(selectedNodeTemp);
		selectSingleMenuTreeObj.checkNode(selectedNodeTemp,true,false);
	});
});

//MenuCrud dialog
var url;

//Initial the parentId
function initParentId(){
	//Input the value and hidden value of parentId input 
	var selectedNode = allMenuTreeObj.getNodeByParam("id",selectedNodeId);
	$("#hidden_parentId").val(selectedNodeId);
	$("#input_parentId").val(selectedNode.name);
}
function newMenu(){
	//清空权限grid
	$('#menuPermissionGrid').datagrid('loadData',[]);
    $('#dlg').dialog('open').dialog('setTitle','新建菜单');
    $('#fm').form('clear');
    initParentId();
    url = 'menu/save';
}
function editMenu(){
    var row = $('#menuGrid').datagrid('getSelected');
    initParentId();
    if (row){
    	if(row.fullPermission){
    		//载入权限
        	$('#menuPermissionGrid').datagrid('loadData',eval(row.fullPermission));
    	}else{
    		//清空权限grid
    		$('#menuPermissionGrid').datagrid('loadData',[]);
    	}
        $('#dlg').dialog('open').dialog('setTitle','修改菜单:'+row.name);
        $('#fm').form('load',row);
        url = 'menu/update?id='+row.id;
    }
}
function saveMenu(){
	//获取权限json字符串，如果校验未通过返回false
	/*var permissionString = getPermissionString();
	if(permissionString){
		$("#hidden_FullPermission").val(permissionString);
	}else{
		return false;
	}*/
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
                selectedNodeId = $("#hidden_parentId").val();
                $.messager.show({
                	title: '提示',
                    msg: "<div style='text-align:center;margin-top:10px;'>保存成功!</div>",
                    style:{
                		right:'',
                		top:document.body.scrollTop+document.documentElement.scrollTop,
                		bottom:''
                	}
                });
                //Reload left tree and refresh the datagrid
                loadMenuTree();
            }
        }
    });
}
function destroyMenu(){
    var rows = $('#menuGrid').datagrid('getSelections');
    var rowsLength = rows.length;
    if (rowsLength>0){
        $.messager.confirm('提示信息','确定删除选中菜单？',function(r){
            if (r){
            	var idsString='';
            	for(var i=0; i<rows.length; i++){
            		if((i+1)==rowsLength){
            			idsString+=rows[i].id;
            		}else{
            			idsString+=(rows[i].id+',');
            		}
            	}
            	$.post('menu/deleteByIds',{ids:idsString},function(result){
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
                    	loadMenuTree();
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


