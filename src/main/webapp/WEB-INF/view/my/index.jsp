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
<title>文章列表</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/popper.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<!-- 头 -->
		<div class="row">
			<!-- 上面标题框 -->
			<div class="row bg-info" style="height: 60px;width: 100%">
				<a href="/index/"><img alt="" src="/resource/images/logo.jpg"
					class="pl-4 rounded-circle" style="height: 60px"></a> <font class="pl-3 pt-3" style="font-size: 20px; font-family: 楷体"><b>今日头条--个人中心</b></font>
				<div class="float-sm-right mt-3" >
					<div class="btn-group dropleft">
						<button type="button" class="btn btn-dark btn-sm dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${sessionScope.user.username }</button>
						<div class="dropdown-menu">
							<ul>
								<li><a href="/my">个人中心</a></li>
								<li><a href="/passport/logout">注销</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- body -->
		<div class="row pt-2" style="height: 670px">
			<div class="col-md-2 bg-light pt-2 rounded" >
				<div class="list-group text-center" style="padding-left: 10px">
					<a href="#" data="/my/articles" class="list-group-item list-group-item-action active">我的文章</a>
					<a href="#" data="/my/publish" class="list-group-item list-group-item-action">发布文章</a> 
					<a href="#" data="/my/collects" class="list-group-item list-group-item-action">我的收藏</a>
					<a href="#" class="list-group-item list-group-item-action">我的评论</a> 
					<a href="#" class="list-group-item list-group-item-action disabled" tabindex="-1" aria-disabled="true">个人设置</a>
				</div>
			</div>
              <!-- 内容显示区域 -->
			<div class="col-md-10 mt-2" id="center">
				<div style="display: none">
					<!-- 包含富文本编辑器的内容 -->
					<jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
		
</body>
<script type="text/javascript">
 //为菜单添加绑定点击事件
 $(function(){
	//默认加载我的文章
	$("#center").load("/my/articles");
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