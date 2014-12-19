<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %>    
    
<!DOCTYPE html>
<html>
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
  	<title>日志</title>
   
    <cmstudio:htmlBase/>
	
	<!--Start importing the jquery files -->
	<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
	<!--End import the jquery files -->
	<!--Start importing the jeasyui files -->
	<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
	
</head>
<body>  <table id="dg" title="日志" class="easyui-datagrid"
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="operationType" width="50"  data-options='formatter:function(value,row,index){
                				switch(row.operationType){
                					case 0 : 
                						return "登录";
                					case 1 :
                						return "添加";
                					case 2 :
                						return "删除";
                					case 3 :
                						return "查询";
                					case 4 :
                						return "修改";
                					case 5 :
                						return "退出";
                					default :
                						break;
                				};	
        		  			}'>操作类型</th>
                <th field="descption" width="50">描述</th>
                <th field="logTime" width="50">记录时间</th>
                <th field="userName" width="50">操作用户</th>
                </tr>
        </thead>
    </table>
    <div id="toolbar">
    
  	   公司:<input id="company" class="easyui-combobox" name="company">
 	   部门:<input id="depart" class="easyui-combobox" name="depart">
	操作人:<input id="userName" name="userName" >
	操作类型:<select class="easyui-combobox" id = "operationType" panelHeight="auto" style="width:100px" name = "operationType">
                <option value="0" selected>登录</option>
                <option value="1">添加</option>
                <option value="2">删除</option>
                <option value="3">查询</option>
                <option value="4">修改</option>
                <option value="5">退出</option>
              </select>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="selectLog()">查询</a>
     </div>
    
	<script type="text/javascript">
	
	$(function() {
		$('#company').combobox({
			url : 'log/company',
			editable : false,
			valueField : 'id',
			textField : 'name',
			panelHeight:'auto',
			onSelect : function(record) {
				$('#depart').combobox({
					url : 'log/depart?parentId='+record.id,
					editable : false,
					valueField : 'id',
					textField : 'name',
					panelHeight:'auto',
					onLoadSuccess: function () { //加载完成后,设置选中第一项
						 var val = $(this).combobox("getData");
			             $(this).combobox("select", val[0]['id']);
			             selectLog();
			        }   
					
				});
			},
			onLoadSuccess: function () { //加载完成后,设置选中第一项
				 var val = $(this).combobox("getData");
	             $(this).combobox("select", val[0]['id']);
	        }
		});
	});
	
	function selectLog (){
		var operationType = $('#operationType').combobox('getValue');
		var company = $('#company').combobox('getValue');
		var depart = $('#depart').combobox('getValue');
		var userName = $('#userName').val();
		$('#dg').datagrid({
		    url:'log/all?operationType='+operationType+"&company="+company+"&depart="+depart+"&userName="+userName
		});
		$('#dg').datagrid('reload'); // reload the user data
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
            width:80px;
        }
        
        .fitem textarea{
            width:150px;
        }
    </style>
</body>
</html>
   
