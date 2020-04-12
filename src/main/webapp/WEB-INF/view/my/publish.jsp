<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
		<link rel="stylesheet" href="/resource/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="/resource/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="/resource/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content1"]', {
				cssPath : '/resource/kindeditor/plugins/code/prettify.css',
				uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
	<%=htmlData%>
	<form id="publishForm">
		<div class="form-group">
   			 <label for="title">文章标题</label>
    		 <input id="title" class="form-control" type="text" name="title">
  		</div>
  		
  		<div class="form-group form-inline">
  			所属栏目:
  			<select class="form-control" id="channel" name="channelId">
  				 <option>---请选择---</option>
			</select>
  			所属分类:
  			<select class="form-control" id="category" name="categoryId">
  				 <option>---请选择---</option>
			</select>
  		</div>
  		
  		<div class="form-group">
  			<input type="file" name="file" class="form-control-file">
  		</div>
		<!-- 文章内容 -->
		<textarea name="content1" cols="100" rows="8" style="width:1236px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br />
		<input type="button" name="button" class="btn btn-info" value="提交内容" onclick="publish()" /> 
	</form>
</body>
<script type="text/javascript">
	//发布文章
	function publish() {
		//form表单中包含文件的，需要使用formData对象
		var formData = new FormData($("#publishForm")[0]);
		formData.set("content",editor1.html());
		$.ajax({
			type:"post",
			url:"/my/publish",
			data:formData,
			contentType:false,
			processData:false,
			success:function(flag){
				if(flag){
					alert("发表成功!");
					location="/my/";
				}else{
					alert("发表失败!");
				}
			}
		})
	}
	
	$(function() {
		//当页面加载时，去查询所有的栏目
		$.get("/channel/channels",function(list){
			for(var i in list){
				$("#channel").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
			}
		});
		//为栏目添加绑定事件
		$("#channel").change(function() {
			//获取当前的栏目id
			var channelId=$(this).val();
			$("#category").empty();
			//根据栏目id查询分类
			$.get("/channel/categories",{channelId:channelId},function(list){
				$("#category").append("<option>---请选择---</option>");
				for(var i in list){
					$("#category").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
				}
			});
		})
	});
	
	
</script>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>