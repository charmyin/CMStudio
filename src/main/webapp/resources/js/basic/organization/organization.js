/***
 * author: charmyin
 * datetime: 2013-2-26 21:00
 * title: Control the organizationManage.jsp ~
 ***/

//Global the item name
var itemName="组织机构";

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
				$("#organizationGrid").datagrid({
					url:'organizationparent/'+node.id+'/all', 
					loadFilter:pagerFilter,
					method:'get',
					toolbar:'#toolbar',
					pagination:true,
					collapsible:true,
					title:"组织架构管理&nbsp----&nbsp"+node.name,
					rownumbers:true,
					singleSelect:false,
					pageSize:8,
				    pageList:[8,16,32,48,64],
					columns:[[
					          {field:'ck', checkbox:true },
					          {field:'id', title:'编号' },
					          {field:'name', title:'名称'},
					          {
					        	  field:'organizationType', 
					        	  title:'机构类型', 
					        	  formatter:function(value,row,index){
	        		  				if (row.organizationType==0){
	        		  					return "部门"
	        		  				} else if (row.organizationType==1){
	        		  					return "公司";
	        		  				}else{
	        		  					return "";
	        		  				}
	        		  			  }
					          },
					          //{field:'parentId', title:'父级菜单'},
					          {field:'orderNumber', title:'排序'},
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
	$("#hidden_parentId").val(selectedNodeId);
	$("#input_parentId").val(selectedNode.name);
}

function newForm(){
    $('#dlg').dialog('open').dialog('setTitle','新建'+itemName+'');
    $('#fm').form('clear');
    initParentId();
    url = 'organization/save';
    //初始化机构类型：0：公司， 1：部门
	$('#organizationType').combobox('setValue', '1');
}
function editForm(){
    var row = $('#organizationGrid').datagrid('getSelected');
    initParentId();
    if (row){
        $('#dlg').dialog('open').dialog('setTitle','修改'+itemName+':'+row.name);
        $('#fm').form('load',row);
        url = 'organization/update?id='+row.id;
    }
}
function saveForm(){
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
                
                selectedNodeId = $("#hidden_parentId").val();
                //Reload left tree and refresh the datagrid
                loadOrganizationTree();
            }
        }
    });
}
function destroySelectedItems(){
    var rows = $('#organizationGrid').datagrid('getSelections');
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
            	$.post('organization/deleteByIds',{ids:idsString},function(result){
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


