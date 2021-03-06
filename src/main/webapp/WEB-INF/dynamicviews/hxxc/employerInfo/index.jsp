<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %>    
<!DOCTYPE html>
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
      <title>${application_name_cn}</title>
      <cmstudio:htmlBase/>
      <!--Start importing the jquery files -->
      <cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
      <!--End import the jquery files -->
      <!--Start importing the jeasyui files -->
      <cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
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
             text-align:center;
         }
         .fitem label{
              text-align:right;
             display:inline-block;
             width:100px;
             font-weight:bold;
         }

         #dlg-buttons{
          text-align:right;
          margin-right:10px 30px 0 0 ;
         }

         .fitem textarea{
            margin:5px 0 0 17%;
            width:200px;
            height:60px;
            display:block;
         }

         #dlg{
          width:350px;
          height:480px;
         }

         #dlg .ftitle{
          margin:5px 10px 5px 10px;
          padding:4px 0px 4px 14px;
         }
      </style>

    <script type="text/javascript">
      /***
       * author: charmyin
       * datetime: 2015-6-22
       * title: Cameras manage ~
       ***/
      /********************************************************Initial the page*****************************************************/
      $(function(){
        //Disable cache
        jQuery.ajaxSetup({ cache: false });
        //Load grid
        $("#dataGrid").datagrid({
            url:'hxxc/employerInfo/findAll',
            method:'POST',
            toolbar:'#toolbar',
            pagination:true,
            collapsible:true,
            title:"公司信息列表",
            rownumbers:true,
            //queryParams:{userrole:1},
            loadFilter:function(data){
              //if data is wrong or failed, show error message
              if(!data.success){
                $.messager.show({
                        title: '提示<span style="color:red;">!</span>',
                          msg: "<div style='text-align:center;margin-top:10px;'>"+data.msg+"</div>",
                          style:{
                          right:'',
                          top:document.body.scrollTop+document.documentElement.scrollTop,
                          bottom:''
                        }
                      });
                return {
                  page:1,
                  total:0,
                  rowsCount:10,
                  rows:[]
                };
              }else{
                return data;
              }
            },
            singleSelect:true,
            pageSize:10,
            pageList:[10,20,30,40,50],
            columns:[[
                      //{field:'ck', checkbox:true},
                        {field:'id', title:'', width:120},
                        {field:'companyId', title:'公司ID(废弃)', width:120},
                        {field:'companyName', title:'公司名称', width:120},
                        {field:'companyRole', title:'公司性质', width:120},
                        {field:'companySize', title:'公司规模', width:120},
                        {field:'companyIntroduction', title:'公司简介', width:120},
                        {field:'companyLogo', title:'公司商标', width:120},
                        {field:'time', title:'更新时间', width:120},
                        {field:'userId', title:'用户ID', width:120},
                      {field:'id', title:'编号', hidden:true}
            ]],
            onLoadError: function(msge){
                $.messager.alert('错误信息','服务器连接已断开或服务器内部错误！','error');
            }
        });
      });
/********************************************************Initial the page end*****************************************************/

      /**Do search submit**/
      function submitSearchForm(){
        $('#searchfm').form('submit',{
            onSubmit: function(param){
              if(!$(this).form('validate')){
                return false;
              }
              //Validate the form
              var arrayData = $('#searchfm').serializeArray();
              var i = 0;
              for(; i < arrayData.length; i++) {
                  param[arrayData[i].name] = arrayData[i].value;
              }
              $('#dataGrid').datagrid('load', param);
              return false;
            },
            success: function(resultString){
            }
        });
      }

      //Save or update url
      var url;
      //Open new form
      function newForm(){
          $('#dlg').dialog('open').dialog('setTitle','公司信息新增');
          $('#fm').form('clear');
          url = 'hxxc/employerInfo/save';
      }
      /**Open edit form **/
      function editForm(){
          var selectedRow = $('#dataGrid').datagrid('getSelections');
          var rowsLength = selectedRow.length;
          if (rowsLength>0){
              $('#dlg').dialog('open').dialog('setTitle','公司信息修改');
              $('#fm').form('load',selectedRow[0]);
              url = 'hxxc/employerInfo/update';
          }else{
            $.messager.show({    // show error message
                  title: '提示<span style="color:red;">!</span>',
                  msg: "<div style='text-align:center;margin-top:10px;'>请选择要修改的行！</div>",
                  style:{
                  right:'',
                  top:document.body.scrollTop+document.documentElement.scrollTop,
                  bottom:''
                }
              });
          }
      }
      /**Save edit form**/
      function saveForm(){
          $('#fm').form('submit',{
              url: url,
              onSubmit: function(param){
                //Validate the form
                return $(this).form('validate');
              },
              success: function(resultString){
                var result = eval("("+resultString+")");
                  if (!result.success){
                      $.messager.show({
                        title: '提示<span style="color:red;">!</span>',
                          msg: "<div style='text-align:center;margin-top:10px;'>"+result.msg+"</div>",
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
                    $('#dataGrid').datagrid('reload');
                  }
              }
          });
      }

      /**Delete item**/
      function deleteSelectedItems(){
          var rows = $('#dataGrid').datagrid('getSelections');
          var rowsLength = rows.length;
          if (rowsLength>0){
              $.messager.confirm('提示信息','确定删除该行？',function(r){
                  if (r){
                    $.post('hxxc/employerInfo/remove', {_id:rows[0]._id}, function(result){
                          if(result.success){
                              $.messager.show({
                                  title: '提示<span style="color:red;">!</span>',
                                  msg: "<div style='text-align:center;margin-top:10px;'>成功！</div>",
                                  style:{
                                      right:'',
                                      top:document.body.scrollTop+document.documentElement.scrollTop,
                                      bottom:''
                                  }
                              });
                          }else{
                              $.messager.alert('错误信息','服务器连接已断开或服务器内部错误！','error');
                          }
                          $("#dataGrid").datagrid("reload");
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
    </script>
  </head>

  <body>
      <!--Grid table-->
      <table id="dataGrid">
      </table>
      <div id="toolbar">
        <form id="searchfm" method="post" style="display:inline;">
          设备CPU序列号
          <input id="search_eq_code" name="search_eq_code" required="true" class="easyui-validatebox" />
          状态:
          <input class="easyui-combobox" id="disbale"  data-options="
              panelHeight:'auto',
              valueField: 'value',
              value:'',
              editable:false,
              textField: 'label',
              data: [{
                label: '全部',
                value: ''
              },{
                label: '已启用',
                value: 'false'
              },{
                label: '已禁用',
                value: 'true'
              }]" />
        </form>
        <a href="javascript:void(0)" onclick="submitSearchForm()" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        <span style="float:right;">
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newForm()">新建</a>
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editForm()">修改</a>
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteSelectedItems()">删除</a>
        </span>
      </div>

      <div id="dlg" class="easyui-dialog" data-options="closed:'true',modal:true,buttons:'#dlg-buttons'">
          <form id="fm" method="post" >
                <div class="fitem">
                    <label>公司名称</label>
                    <input name="companyName" id="companyName_input" class="easyui-validatebox" required="true">
                </div>
                <div class="fitem">
                    <label>公司性质</label>
                    <input name="companyRole" id="companyRole_input" class="easyui-validatebox" required="true">
                </div>
                <div class="fitem">
                    <label>公司规模</label>
                    <input name="companySize" id="companySize_input" class="easyui-validatebox" required="true">
                </div>
                <div class="fitem">
                    <label>公司简介</label>
                    <input name="companyIntroduction" id="companyIntroduction_input" class="easyui-validatebox" required="true">
                </div>
                <div class="fitem">
                    <label>公司商标</label>
                    <input name="companyLogo" id="companyLogo_input" class="easyui-validatebox" required="true">
                </div>
                <div class="fitem">
                    <label>更新时间</label>
                    <input name="time" id="time_input" class="easyui-validatebox" required="true">
                </div>
                <div class="fitem">
                    <label>用户ID</label>
                    <input name="userId" id="userId_input" class="easyui-validatebox" required="true">
                </div>
              <input type="hidden" name="id" id="id">
          </form>
      </div>
      <div id="dlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveForm()">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>

  </body>
</html>
