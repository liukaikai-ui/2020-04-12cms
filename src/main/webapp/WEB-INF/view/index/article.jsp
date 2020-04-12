<%@page import="java.net.URLDecoder"%>
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
<title>${article.title }</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<div  class="container-fluid">
		<!-- 头 -->
		<div class="row" style="height:44px">
			<div class="col-md-12 bg-dark pt-2">
				<font color="white"></font>
			</div>
		</div>
		
		<div class="row">
		
			<div class="col-md-2">
			
			</div>
			<!-- 文章详情 -->
			<div class="col-md-7">
				<h3>${article.title }</h3>
				<p>${article.user.username }
					<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</p>
				${article.content }
				<hr>
				
				<!-- 收藏 -->
				<div align="right">
					<c:if test="${flag==0 }">
						<a href="javascript:collect()">❤收藏</a>
					</c:if>
					<c:if test="${flag==1 }">
						<span style="color: red">♥已收藏</span>
						<span style="color: grey;font-size: 12px" class="pl-2"><a href="javascript:delCollect()">☆取消收藏</a></span>
					</c:if>
				</div>
				<span>${article.commentNum }条评论</span><br>
				<c:if test="${null != sessionScope.user }">
				<!-- 增加评论 -->
				<div  class="pt-3">
					<textarea placeholder="请输入你的评论内容" rows="8" cols="105" name="content"></textarea>
					<button type="button" class="btn btn-warning" onclick="addComment()">评论</button>
				</div>
				<hr>
				</c:if>
				<!-- 显示评论 -->
				<div  class="pt-3">
					<c:forEach items="${info.list }" var="comment">
						<span style="font-size: 18px" class="pr-3">${comment.user.username }</span> 
						<span style="font-size: 14px"><fmt:formatDate value="${comment.created }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						<br>
						<p class="mt-3">${comment.content }</p>
						<hr>
					</c:forEach>
					<jsp:include page="/WEB-INF/view/comcom/pages.jsp"></jsp:include>
				</div>
			</div>
			<!-- 右侧 -->
			<div class="col-md-3">
				<!-- 评论排行榜 -->
				<div class="card" style="width: 18rem;">
					<div class="card-header">评论排行榜</div>
					<div class="card-body">
						<c:forEach items="${rank.list }" var="comment">
							<div class="media">
								${comment.user.username }
									<span class="mt-0">
										评论数:${comment.num }
									</span>
							</div>
							<hr>
						</c:forEach>
					</div>
				</div>
			</div>
		
		</div>
	
	</div>
</body>
<script type="text/javascript">
	

	//取消收藏
	function delCollect() {
		var articleId = '${article.id}';
		
		if(confirm("您确定要取消该收藏么?")){
			$.post("/delCollect",{articleId:articleId},function(flag){
				if(flag){
					alert("已取消!");
					location.reload();
				}else{
					alert("请登录后再试!");
				}
			})
		}
	}
	//收藏
	function collect() {
		var text = '${article.title}';
		var url = window.location.href;
		var articleId = '${article.id}';
		$.post("/collect",{text:text,url:url,articleId:articleId},function(flag){
			if(flag){
				alert("收藏成功!");
				location.reload();
			}else{
				alert("请登录后再试!");
			}
		})
	}
	//评论
	function addComment() {
		var content = $("[name='content']").val();
		var id= '${article.id}';
		$.post("/addComment",{content:content,articleId:id},function(flag){
			if(flag){
				alert("评论成功!");
				location.reload();
			}else{
				alert("请登录后重试");
			}
		})
	}
	
	function goPage(pageNum) {
		var url = window.location.href;
		location=url+"&pageNum="+pageNum;
	}
</script>
</html>