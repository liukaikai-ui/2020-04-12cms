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
		<ul class="list-unstyled">
			<c:forEach items="${info.list }" var="article">
				<li class="media"><img style="width: 60px;height: 60px" src="/pic/${article.picture }" class="mr-3" alt="...">
					<div class="media-body">
						<h5 class="mt-0 mb-1">${article.title }</h5>
						<p class="pt-1"><fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
							<button type="button" class="btn btn-link" data-toggle="modal"
							data-target="#exampleModalLong" onclick="detail(${article.id })">详情
							</button>
						 </p>
						
					</div>
				</li>
				<hr>
			</c:forEach>
		</ul>
		<div>
			<jsp:include page="/WEB-INF/view/comcom/pages.jsp"></jsp:include>
		</div>
	</div>

	<!-- 模态框 -->
	<div class="modal fade" id="exampleModalLong" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLongTitle"
		aria-hidden="true">
		<div class="modal-dialog  modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle"><span id="title"></span></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="content"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function goPage(pageNum) {
	$("#center").load("/my/articles?pageNum="+pageNum);
}
//在模态框中显示文章的详情
function detail(id) {
	//先清空原有的值
	$("#title").empty();
	$("#content").empty();
	$.get("/my/article",{id:id},function(article){
		$("#title").append(article.title);
		$("#content").append(article.content);
	})
}
</script>
</html>