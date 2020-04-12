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
<style type="text/css">
.ex{
	width:250px;
	white-space:nowrap;/*不换行的*/
	overflow:hidden;/*超出范围隐藏*/
	text-overflow:ellipsis;/*超出用省略号*/
}
</style>
</head>
<body>
	<div class="form-group form-inline">
		<div class="form-group">
			<label for="title">文章标题</label>
			<input id="title" class="form-control" type="text" name="title" value="${article.title }">
		</div>
		<div class="form-check-inline">
			审核状态
			<input class="form-check" type="radio" name="status" id="check1" value="0" ${article.status==0?"checked":"" }>
			<label class="form-check-label" for="check1">待审</label>
			<input class="form-check" type="radio" name="status" id="check2" value="1" ${article.status==1?"checked":"" }>
			<label class="form-check-label" for="check2">已审</label>
			<input class="form-check" type="radio" name="status" id="check3" value="-1" ${article.status==-1?"checked":"" }>
			<label class="form-check-label" for="check3">驳回</label>
		</div>
		<div>
			<button class="btn btn-info" onclick="query()">查询</button>
		</div>
	</div>
	


	<table class="table table-bordered table-hover text-center">
		<tr>
			<td>序号</td>
			<td>文章标题</td>
			<td>所属栏目</td>
			<td>所属分类</td>
			<td>作者</td>
			<td>发布时间</td>
			<td>是否热门</td>
			<td>审核状态</td>
			<td>操作</td>
		</tr>

		<c:forEach items="${info.list}" var="article" varStatus="index">
			<tr>
				<td>${index.count }</td>
				<td><div class="ex">${article.title }</div></td>
				<td>${article.channel.name }</td>
				<td>${article.category.name }</td>
				<td>${article.user.username }</td>
				<td><fmt:formatDate value="${article.created }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<c:if test="${article.hot==0 }">
						<button class="btn btn-info btn-sm" type="button" onclick="hupd(${article.id},this)">否</button>
					</c:if>
					<c:if test="${article.hot==1 }">
						<button class="btn btn-danger btn-sm" type="button"  onclick="hupd(${article.id},this)">是</button>
					</c:if>
				</td>
				<td>${article.status==0?"待审":article.status==1?"已审":"驳回" }</td>
				<td>
					<button type="button" class="btn btn-link" data-toggle="modal"
						data-target="#exampleModalLong" onclick="detail(${article.id})">详情</button>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="10"><jsp:include page="/WEB-INF/view/comcom/pages.jsp"></jsp:include></td>
		</tr>
	</table>
	
	
	<!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle"><span id="mtitle"></span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="content">
        
      </div>
      <div class="modal-footer">
      <span id="msg" class="text-danger"></span>
      <button type="button" class="btn btn-success" onclick="check(1)">通过</button>
      <button type="button" class="btn btn-warning" onclick="check(-1)">驳回</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
        
      </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
var articleid;//文章的id
	function goPage(pageNum) {
		//标题
		var title=$("[name='title']").val();
		//审核状态
		var status=$("[name='status']:checked").val();
		//在中间区域加载分页
		$("#center").load("/admin/articles?title="+title+"&status="+status+"&pageNum="+pageNum);
	}
	
	function query() {
		//标题
		var title=$("[name='title']").val();
		//审核状态
		var status=$("[name='status']:checked").val();
		//模糊查询结束后 到center中加载
		$("#center").load("/admin/articles?title="+title+"&status="+status);
	}
	
	function detail(id) {
		$("#mtitle").empty();
		$("#content").empty();
		$("#msg").empty();
		articleid=id;//为全局变量赋值
		//根据文章id查询文章内容
		$.get("/admin/article",{id:id},function(article){
			$("#mtitle").append(article.title);
			$("#content").append(article.content);
		})
	}
	
	function check(status) {
		$.post("/admin/upd",{id:articleid,status:status},function(flag){
			if(flag>0){
				$("#msg").text("操作成功!");
				location.reload();
			}else{
				$("#msg").text("操作失败!");
			}
		})
	}
	
	function hupd(id,obj) {
		var hot = $(obj).text()=='否'?1:0;;
		$.post("/admin/upd",{id:id,hot:hot},function(flag){
			if(flag){
				//改变按钮的内容
				hot==1?$(obj).text("是"):$(obj).text("否");
				if($(obj).text()=="是"){
					$(obj).attr("class","btn btn-danger btn-sm");
				}else{
					$(obj).attr("class","btn btn-info btn-sm");
				}
			}
		})
	}
</script>
</html>