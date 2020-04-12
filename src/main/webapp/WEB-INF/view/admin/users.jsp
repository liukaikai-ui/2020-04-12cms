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
	<div class="form-group form-inline">
		<div class="form-group">
			<label for="username">用户名称</label>
			<input id="username" class="form-control" type="text" name="username" value="${user.username }">
		</div>
		<div class="form-check-inline">
			用户状态
			<input class="form-check" type="radio" name="locked" id="check1" value="0" ${user.locked==0?"checked":"" }>
			<label class="form-check-label" for="check1">正常</label>
			<input class="form-check" type="radio" name="locked" id="check2" value="1" ${user.locked==1?"checked":"" }>
			<label class="form-check-label" for="check2">停用</label>
		</div>
		<div>
			<button class="btn btn-info" onclick="query()">查询</button>
		</div>
	</div>
	


	<table class="table table-bordered table-hover text-center">
		<tr>
			<td>序号</td>
			<td>用户名称</td>
			<td>昵称</td>
			<td>注册日期</td>
			<td>生日</td>
			<td>状态</td>
		</tr>

		<c:forEach items="${info.list}" var="user" varStatus="index">
			<tr>
				<td>${index.count }</td>
				<td>${user.username }</td>
				<td>${user.nickname }</td>
				<td><fmt:formatDate value="${user.created }"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td><fmt:formatDate value="${user.birthday }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<c:if test="${user.locked==0 }">
						<button class="btn btn-info btn-sm" type="button" onclick="lupd(${user.id},this)">正常</button>
					</c:if>
					<c:if test="${user.locked==1 }">
						<button class="btn btn-danger btn-sm" type="button"  onclick="lupd(${user.id},this)">禁用</button>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="10"><jsp:include page="/WEB-INF/view/comcom/pages.jsp"></jsp:include></td>
		</tr>
	</table>
	
	
</body>
<script type="text/javascript">
var articleid;//文章的id
	function goPage(pageNum) {
		//标题
		var username=$("[name='username']").val();
		//审核状态
		var locked=$("[name='locked']:checked").val();
		//在中间区域加载分页
		$("#center").load("/admin/users?username="+username+"&locked="+locked+"&pageNum="+pageNum);
	}
	
	function query() {
		//标题
		var username=$("[name='username']").val();
		//审核状态
		var locked=$("[name='locked']:checked").val();
		//模糊查询结束后 到center中加载
		$("#center").load("/admin/users?username="+username+"&locked="+locked);
	}
	
	function lupd(id,obj) {
		var locked = $(obj).text()=='正常'?1:0;;
		$.post("/admin/lupd",{id:id,locked:locked},function(flag){
			if(flag){
				//改变按钮的内容
				locked==1?$(obj).text("禁用"):$(obj).text("正常");
				if($(obj).text()=="禁用"){
					$(obj).attr("class","btn btn-danger btn-sm");
				}else{
					$(obj).attr("class","btn btn-info btn-sm");
				}
			}
		})
	}
</script>
</html>