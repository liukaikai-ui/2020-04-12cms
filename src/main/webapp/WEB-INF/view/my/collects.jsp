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
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="comtainer">
		<h1 class="text-warning">我的收藏夹</h1>
		<hr>
		<c:forEach items="${info.list }" var="collect">
			<li style="list-style-type: none;"><a href="${collect.url }">${collect.text }</a></li>
			<li style="list-style-type: none;">时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${collect.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
			<button class="btn btn-warning" onclick="delCollect(${collect.id})">删除</button></li>
			<hr>
		</c:forEach>
		<div>
			<jsp:include page="/WEB-INF/view/comcom/pages.jsp"></jsp:include>
		</div>
	</div>


</body>
<script type="text/javascript">
function goPage(pageNum) {
	$("#center").load("/my/collects?pageNum="+pageNum);
}
//取消收藏
function delCollect(id) {
	$.post("/my/delCollect",{id:id},function(flag){
		if(flag){
			alert("取消收藏成功");
			$("#center").load("/my/collects");
		}else{
			alert("取消失败");
		}
	})
}
</script>
</html>