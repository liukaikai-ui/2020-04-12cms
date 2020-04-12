<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>今日头条管理员中心</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		
		<div class="row">
			<!-- 上面标题框 -->
		<div class="row bg-info" style="height: 60px;width: 100%">
			<img alt="" src="/resource/images/logo.jpg" class="pl-4 rounded-circle" style="height: 60px">
			<font class="pl-3 pt-3" style="font-size: 20px;font-family: 楷体"><b>今日头条--管理员中心</b></font>
		</div>
		</div>
		
		<div class="row" style="height: 500px; padding-top: 5px">
			<div class="col-md-2 bg-light pt-2 rounded" >
				<div class="list-group text-center" style="padding-left: 10px">
					<a href="#" data="/admin/articles" class="list-group-item list-group-item-action active">审核文章</a>
					<a href="#" data="/admin/users" class="list-group-item list-group-item-action">管理用户</a> 
					<a href="#" class="list-group-item list-group-item-action">系统设置</a>
				</div>
			</div>
              <!-- 内容显示区域 -->
			<div class="col-md-10 pt-2" id="center">
				
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	//默认加载我的文章
	$("#center").load("/admin/articles");
	$("a").click(function(){
		//获取a标签的url
		var url =$(this).attr("data");
		//先把所有的选中（激活）的蓝色 去除
		$("a").removeClass("active");
		//为当前点击的a标签 添加active
		$(this).addClass("active");
		//在中间区域加载url
		$("#center").load(url);
	}) 
 })
</script>
 
</html>