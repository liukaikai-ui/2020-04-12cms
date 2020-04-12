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
<title>用户注册</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container col-md-5">
	<h1>管理员登陆</h1>
	<hr>
	<form id="form2">
	 <span id="msg" class="text-danger">${msg } </span>
  <div class="form-group">
    <label for="username">用户名</label>
    <input type="text" class="form-control" id="username" name="username">
  </div>
  <div class="form-group">
    <label for="password">密码</label>
    <input type="password" class="form-control" id="password" name="password">
  </div>
  <div class="form-check form-check-inline">
    <input type="radio" class="form-check-input" id="boy" name="gender" value="0" checked="checked">
    <label for="boy">男</label>
    
  </div>
  <div class="form-check form-check-inline">
    <input type="radio" class="form-check-input" id="girl" name="gender" value="1">
    <label for="girl">女</label>
    
  </div>
 	 <div class="modal-group">
        <button type="submit" class="btn btn-info" >登陆</button>
        <button type="reset" class="btn btn-primary">重置</button>
      </div>
  
</form>
</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#form2").validate({
			rules:{
				username:{
					required:true,
				},
				password:{
					required:true,
				}
			},
			messages:{
				username:{
					required:"用户名不能为空",
				},
				password:{
					required:"密码不能为空",
				}
			},
			submitHandler:function(){
				$.post("/passport/login_admin",$("#form2").serialize(),function(result){
					if(result.code==200){//如果登录成功
						location.href="/admin/";//进入管理员页面
					}else{//登录失败
						$("#msg").text(result.msg)
					}
				});
			}
			
		})
	})
</script>
</html>