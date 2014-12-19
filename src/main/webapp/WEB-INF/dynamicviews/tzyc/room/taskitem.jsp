<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %>
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>机房巡检任务</title>
    <cmstudio:htmlBase/>
       <!--Start importing the jquery files -->
	<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
	<!--End import the jquery files -->
	<!--Start importing the jeasyui files -->
	<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
	<!--End importing the jeasyui files -->
  </head>
<body>
    <table id="dg" title="机房巡检任务" class="easyui-datagrid" 
            url="room/findall"
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
            	<th field="createName" width="50">创建者</th> 
            	<th field="createTime" width="50">创建时间</th> 
            	<th field="itemName" width="50">机房检查对象</th> 
            	<th field="content" width="50">机房检查内容</th> 
                <th field="taskResult" width="50">提报结果</th>
             </tr>
        </thead>
    </table>

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
</body>
</html>