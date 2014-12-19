/*******************************************************************************
 * author: charmyin datetime: 2013-2-26 21:00 title: The main page of this
 * application~
 ******************************************************************************/
// 移除加载页面
var removeLoadingDiv = function() {
	$('#divLoading_Main').fadeOut("slow", function() {
		$(this).remove();
	});
};

// Open tab
var openMainTab = function(node) {
	var isNotExisted = true;// tab不存在
	var tabs = $("#divTab_Main").tabs('tabs');
	for ( var i = 0; i < tabs.length; i++) {
		if ('divTab_Main_' + node.tId == $(tabs[i]).attr('id')) {
			$("#divTab_Main").tabs('select', i);
			isNotExisted = false;// tab已存在
		}
	}
	if (isNotExisted) {
		$('#divTab_Main')
				.tabs(
						'add',
						{
							id : 'divTab_Main_' + node.tId,// tab的Id格式为divTab_Main_12
							title : node.name,
							content : '<iframe id="iframeTab_'
									+ node.tId
									+ '" src="'
									+ node.linkUrl
									+ '" style="border:none; overflow:auto;width:100%;height:99%;" frameBorder="0"></iframe>',// '',
							closable : true,
							fit : true,
							tools : [ {
								iconCls : 'icon-mini-refresh',
								handler : function() {
									$('#iframeTab_' + node.tId).attr(
											"src",
											$('#iframeTab_' + node.tId).attr("src"));
								}
							} ]
						});
		$('#divTab_Main_' + node.tId).css({
			'padding' : '0',
			'margin' : '0',
			'overflow' : 'auto'
		});
	}
};

// doc加载后执行
$(function() {
	// 去除加载mask效果
	if ($("#divLoading_Main").length > 0) {
		$('#divLoading_Main').fadeOut("slow", function() {
			$(this).remove();
		});
	}

	// system manage tree
	var systemManageTreeObj;
	// system develop tool tree
	var divDevelopToolTreeObj;
	// system develop tool tree
	var moduleSystemTreeObj;

	var menuTreeSetting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId"
			}
		},
		callback : {
			onClick : function(event, treeId, node) {
				var boo = node.isParent;
				if (!boo) {
					openMainTab(node);
				}
			}
		}
	};

	$.ajax({
		type : "GET",
		url : "menu/user"
	}).done(
			function(msg) {
				
				var countMenuNav=0;
				
				// Load the system manage tree
				systemManageTreeObj = $.fn.zTree.init($("#divSystemManage_main_tree"), menuTreeSetting, msg);
				var newNode = systemManageTreeObj.getNodeByParam("id", "2");
				
				if (newNode) {
					systemManageTreeObj.removeNode(systemManageTreeObj.getNodes()[0]);
					systemManageTreeObj.addNodes(null, newNode.children);
					countMenuNav++;
				} else {
					$('#divAccordion_main').accordion('remove',countMenuNav);
				}
				// Load the system develop tool tree
				divDevelopToolTreeObj = $.fn.zTree.init($("#divDevelopTool_tree"), menuTreeSetting, msg);
				var newNode1 = divDevelopToolTreeObj.getNodeByParam("id", "3");
				// If has not the node of 3 "develop tool", remove the div
				// related
				if (newNode1) {
					divDevelopToolTreeObj.removeNode(divDevelopToolTreeObj.getNodes()[0]);
					divDevelopToolTreeObj.addNodes(null, newNode1.children);
					countMenuNav++;
				} else {
					$('#divAccordion_main').accordion('remove',countMenuNav);
				}

				// Load the Module Samples tree
				moduleSystemTreeObj = $.fn.zTree.init($("#moduleSystem_tree"),menuTreeSetting, msg);
				var newNode2 = moduleSystemTreeObj.getNodeByParam("id", "4");
				if (newNode2) {
					moduleSystemTreeObj.removeNode(moduleSystemTreeObj.getNodes()[0]);
					moduleSystemTreeObj.addNodes(null, newNode2.children);
					countMenuNav++;
				} else {
					$('#divAccordion_main').accordion('remove',countMenuNav);
				}

			});

	// Change the theme
	$(".divOnChangeTheme").click(function() {
		changeTheme($(this).attr('value'));
	});
	var changeTheme = function(theme) {
		// link.attr('href', '/easyui/themes/'+theme+'/easyui.css');
	};
	
	// Logout the system
	/*$("#logout").click(function() {
		$.messager.confirm('退出提示', '确定退出系统？', function(r) {
			if (r) {
				window.location.href = "identity/logout";
			}
		});

	});*/
});

function logOut(){
	$.messager.confirm('退出提示', '确定退出系统？', function(r) {
		if (r) {
			window.location.href = "identity/logout";
		}
	}); 
}

//Change password
function modifyPassword(){
	$('#modifyPassword').dialog({
	    title: '密码修改',
	    width: 350,
	    buttons:'#mp_form_btns',
	    height: 185,
	    closed: false,
	    cache: false,
	    modal: true
	});
}

function savePassword(){
    $('#mp_form').form('submit',{
        url: "user/modifyPassword",
        onSubmit: function(){
        	
        	var isValidated = $(this).form('validate');
        	
        	if(isValidated){
        		if($("#newPW").val() != $("#newPW1").val()){
        			isValidated = false;
        			$.messager.show({
                    	title: '提示<span style="color:red;">!</span>',
                        msg: "<div style='text-align:center;margin-top:10px;'>新密码不一致！</div>",
                        style:{
                    		right:'',
                    		top:document.body.scrollTop+document.documentElement.scrollTop,
                    		bottom:''
                    	}
                    });
        		}
        	}
        	
        	//组件验证，未通过则返回false
        	return isValidated;
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
                $('#modifyPassword').dialog('close');        // close the dialog
                
                $.messager.show({
                	title: '提示',
                    msg: "<div style='text-align:center;margin-top:10px;'>保存成功,请重新登录!</div>",
                    style:{
                		right:'',
                		top:document.body.scrollTop+document.documentElement.scrollTop,
                		bottom:''
                	}
                });
                
                setInterval(function(){
                	window.location.href = "identity/logout";
                },2000);
                
            }
        }
    });
}


