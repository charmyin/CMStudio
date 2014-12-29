<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
	<cmstudio:htmlBase/>
	
	<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
	<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
	
	<script src="resources/vendor/fileupload/jquery.ui.widget.js" type="text/javascript"></script>
	<script src="resources/vendor/fileupload/jquery.iframe-transport.js" type="text/javascript"></script>
	<script src="resources/vendor/fileupload/jquery.fileupload.js" type="text/javascript"></script>
</head>
<body>
	<fieldset style=" border:1px solid red;">
	 <legend>Simple single upload</legend>	
		<form method="POST" enctype="multipart/form-data" action="fileupload/uploadSingleFile">
			File to upload: <input type="file" name="file"><br /> Name: <input type="text" name="name">
			  <br /> <br /> <input type="submit" value="Upload"> Press here to upload the file!
		</form>
	</fieldset>
	
	<fieldset style="border:1px solid red;">
	 	<legend>Ajax single upload: <a href="https://blueimp.github.io/jQuery-File-Upload/">Official Demos</a></legend>	
		<span>&nbsp;&nbsp;文件上传:</span>
        <input id="fileupload" type="file" name="file" data-url="fileupload/uploadSingleFile" style="width:70px;">
        <span id="buttonUpload"></span>
	</fieldset>
	<script type="text/javascript">
		 function endsWith(str, suffix) {
		        return str.indexOf(suffix, str.length - suffix.length) !== -1;
		    }
		    
		    $(function () {
		        $('#fileupload').fileupload({
		            dataType: 'json',
		            add: function (e, data) {
		            	if(data.files[0].name && endsWith(data.files[0].name, ".jpg")){
		            		$("#buttonUpload").text(data.files[0].name);
		                	$("#uploadBtn").remove();
		                    data.context = $('<button id="uploadBtn"/>').text('上传')
		                        .appendTo($("#buttonUpload"))
		                        .click(function () {
		                        	$("#uploadSpan").remove();
		                            data.context = $('<span id="uploadSpan"/>').text('上传中').replaceAll($(this));
		                            data.submit();
		                        });
		            	}else{
		            		$.messager.show({    // show error message
		                        title: '<span style="color:red;">提示</span>',
		                        msg: '必须上传后缀名为:<span style="color:red;">.jpg</span>的图片!',
		                        style:{
		                    		right:'',
		                    		top:document.body.scrollTop+document.documentElement.scrollTop,
		                    		bottom:''
		                    	}
		                    });
		            	
		            	}
		            	
		            },
		            done: function (e, data) {      
		            	if(data.result.success){
		            		$.messager.show({    // show error message
		                        title: '<span style="color:red;">提示</span>',
		                        msg: '上传成功!',
		                        style:{
		                    		right:'',
		                    		top:document.body.scrollTop+document.documentElement.scrollTop,
		                    		bottom:''
		                    	}
		                    });
		            	}else{
		            		$.messager.show({    // show error message
		                        title: '<span style="color:red;">提示</span>',
		                        msg: data.result.message,
		                        style:{
		                    		right:'',
		                    		top:document.body.scrollTop+document.documentElement.scrollTop,
		                    		bottom:''
		                    	}
		                    });
		            	}
		            }
		        });
		    });  
	</script>
</body>
</html>