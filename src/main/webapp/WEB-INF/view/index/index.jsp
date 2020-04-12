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
<title>今日头条表</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="/resource/css/index.css" rel="stylesheet">
<link href="/resource/css/jquery/screen.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/popper.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<style type="text/css">
.ex {
	width: 250px;
	overflow: hidden; /*超出范围隐藏*/
	text-overflow: ellipsis; /*超出用省略号 */
	display:-webkit-box; /*弹性伸缩盒子显示*/
	-webkit-box-orient:vertical; /*垂直排列子元素*/
	-webkit-line-clamp:2; /*显示的行数*/
}
.fixed-div{
	position:fixed;
	z-index:99;
}
.fli{
	float:left
}
</style>
</head>
<body>
	<div class="container-fluid">
		<!-- 头 -->
		<div class="row" style="height:44px">
			<div class="col-md-12 bg-dark pt-2">
				<font color="white">下载APP</font>
				<div class="float-sm-right">
					<!-- 如果没有登录 -->
					<c:if test="${sessionScope.user==null }">
					<button class="btn btn-link text-decoration-none" onclick="login()" data-toggle="modal" data-target="#exampleModalLong"><font color="white">登录</font></button>
					<button class="btn btn-link text-decoration-none" onclick="reg()" data-toggle="modal" data-target="#exampleModalLong">><font color="white">注册头条号</font></button>
					</c:if>
					
					<!-- 如果登录 -->
					<c:if test="${sessionScope.user!=null }">
						<div class="btn-group dropleft">
							<button type="button" class="btn btn-dark btn-sm dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">${sessionScope.user.username }</button>
							<div class="dropdown-menu">
								<!-- Dropdown menu links -->
								<ul> 
									<li><a href="/my">个人中心</a></li>
									<li><a href="/passport/logout">注销</a></li>
								</ul>
							</div>
						</div>
					</c:if>
				</div>
			</div>
			
		</div>
		<!-- 内容 -->
		<div class="row pt-2">
			<!-- 左 -->
				<div class="col-md-2">
				<div class="fixed-div pl-5">
				<li class="mb-2"><a href="/"><img alt="" src="/resource/images/1.png"></a></li>
				<li><a class="channel-item ${article.channelId==null?"active":"" }" href="/">热点</a></li>
				<c:forEach items="${channels }" var="channel">
					<li><a class="channel-item ${article.channelId==channel.id?"active":"" }"" href="/?channelId=${channel.id }">${channel.name }</a></li>
				</c:forEach>
				</div>
			</div>
			
			<!-- 中间 -->
			<div class="col-md-7">
				<!-- 默认热点 -->
				<c:if test="${article.channelId==null }">
					
					<div>
						<!-- 广告 -->
						<div id="carouselExampleCaptions" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<c:forEach items="${slides }" var="slide" varStatus="i">
								<li data-target="#carouselExampleCaptions" data-slide-to="${i.index }"
									class="active"></li>
								</c:forEach>
							</ol>
							<div class="carousel-inner">
								<c:forEach items="${slides }" var="slide" varStatus="i">
								<div class="carousel-item ${i.index==0?"active":"" }">
									<img style="height: 350px" src="/pic/${slide.picture }" class="d-block w-100" alt="...">
									<div class="carousel-caption d-none d-md-block">
										<h5>${slide.title }</h5>
									</div>
								</div>
								</c:forEach>
								</div>
							<a class="carousel-control-prev" href="#carouselExampleCaptions"
								role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span> <span
								class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
								role="button" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
						</div>
						<!-- 热点文章 -->
						<div>
						<c:forEach items="${info.list }" var="hotarticle">
							<div class="media mt-3">
								<a  href="/detail?id=${hotarticle.id }" target="_blank"><img src="/pic/${hotarticle.picture }" class="mr-3 rounded" alt="..." width="156px" height="101.8px"></a>
								<div class="media-body ml-2">
									<h5 class="mt-0"><a href="/detail?id=${hotarticle.id }" target="_blank">${hotarticle.title }</a></h5>
									<p class="mt-4">${hotarticle.user.username } 
										<fmt:formatDate value="${hotarticle.created }" pattern="yyyy-MM-dd"/>
										${hotarticle.commentNum }条评论&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${hotarticle.hits }点击量
									</p>
								</div>
							</div>
							<hr>
						</c:forEach>
						<jsp:include page="/WEB-INF/view/comcom/pages.jsp"></jsp:include>
						</div>
					</div>
				
				</c:if>
				
				
				<!-- 栏目不为空 显示栏目下的广告和热点文章 -->
				<c:if test="${article.channelId!=null }">
				<!-- 文章的分类 -->
				<div>
					<ul class="subchannel">
						<li class="sub-item ${article.categoryId==null?"sub-selected":"" }"><a href="/?channelId=${article.channelId }">全部</a></li>
						<c:forEach items="${categories }" var="category">
							<li class="sub-item ${article.categoryId==category.id?"sub-selected":"" }""><a href="/?channelId=${article.channelId}&categoryId=${category.id }">${category.name }</a></li>
						</c:forEach>
					</ul>
					
					<!-- 分类下的文章 -->
					<div>
						<c:forEach items="${info.list }" var="article">
							<div class="media">
								<a  href="/detail?id=${article.id }" target="_blank"><img src="/pic/${article.picture }" class="mr-3 rounded" alt="..." width="156px" height="101.8px"></a>
								<div class="media-body ml-2">
									<h5 class="mt-0"><a  href="/detail?id=${article.id }" target="_blank">${article.title }</a></h5>
									<p class="mt-4">${article.user.username }
										<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd"/>
										${article.commentNum }条评论
									</p>
								</div>
							</div>
							<hr>
						</c:forEach>
					</div>
				</div>
				</c:if>
				<hr>
				<span style="font-size: 20px">友情链接:</span>
				<hr>
				<!-- 友情链接 -->
				<div>
					<c:forEach items="${flist }" var="friend">
						<li class="fli"><a href="#" style="font-size: 20px;margin-left: 50px">${friend.text }</a></li>
					</c:forEach>
				</div>
				<hr>
				<br><br><br><br>	
			</div>
			<!-- 右 -->
			<div class="col-md-3">
				<!-- 最新文章 -->
				<div class="card" style="width: 18rem;">
					<div class="card-header">最新文章</div>
					<div class="card-body">
						<c:forEach items="${lastArticle.list }" var="lastArticle">
							<div class="media">
							<a  href="/detail?id=${lastArticle.id }" target="_blank"><img src="/pic/${lastArticle.picture }" class="mr-3 rounded" alt="..." width="60px" height="60px"></a>
							<div class="media-body ex">
								<h5 class="mt-0"><a  href="/detail?id=${lastArticle.id }" target="_blank">${lastArticle.title }</a></h5>
							</div>
						 	</div>
						 	<hr>
						</c:forEach>
					</div>
				</div>
				<!-- 24小时热文 -->
				<div class="card" style="width: 18rem;">
					<div class="card-header">24小时热文</div>
					<div class="card-body">
						<c:forEach items="${rwArticle.list }" var="rwArticle">
							<div class="media">
							<a  href="/detail?id=${rwArticle.id }" target="_blank"><img src="/pic/${rwArticle.picture }" class="mr-3 rounded" alt="..." width="60px" height="60px"></a>
							<div class="media-body ex">
								<h5 class="mt-0"><a  href="/detail?id=${rwArticle.id }" target="_blank">${rwArticle.title }</a></h5>
							</div>
						 	</div>
						 	<hr>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 注册的 -->
	<!-- Modal -->
	<div class="modal fade" id="exampleModalLong" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLongTitle"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">
						<span id="title"></span>
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="passport"></div>

			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript">

	//注册
	function reg() {
		$("#title").text("用户注册");
		$("#passport").load("/passport/reg");
	}
	//登陆
	function login() {
		$("#title").text("用户登录");
		$("#passport").load("/passport/login");
	}

	//分页
	function goPage(pageNum) {
		var channelId='${article.channelId}';
		var categoryId='${article.categoryId}';
		var url="/?pageNum="+pageNum;
		if(channelId!=""){
			url+="&channelId="+channelId;
		}
		if(categoryId!=""){
			url+="&categoryId="+categoryId;
		}
		location=url;
	}

</script>
</html>