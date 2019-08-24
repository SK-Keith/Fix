<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
<link href="<c:url value='/jsps/css/bootstrap.min.css'/>"
	rel="stylesheet">
<link href="<c:url value='/jsps/css/main.css'/>" rel="stylesheet">
<link href="<c:url value='/jsps/css/main_xiugai.css'/>" rel="stylesheet">
<script type="text/javascript"
	src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jsps/js/student/main_xiugai.js'/>"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
	.navbar-inverse {
    background-color: #009688;
    border-color: #009688;
}
.navbar-inverse .navbar-nav>.active>a, .navbar-inverse .navbar-nav>.active>a:focus, .navbar-inverse .navbar-nav>.active>a:hover {
    background-color: #4caf50!important;
}
</style>


<title>报修系统</title>
</head>
<body>

	<!-- header -->
	<div class="header-nav">
		<div class="container">
			<nav class="navbar navbar-default">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<div class="logo" data-wow-delay=".5s">
						<img  style="float:left;" src="<c:url value='/jsps/images/xy.jpg'/>">
						<a class="navbar-brand" href="#" >惠州学院报修系统</a>
					</div>
				</div>				
			</nav>
		</div>
	</div>
<!-- //header -->


	<div class="container bs-docs-container">

		<div class="row">
			<div class="col-md-9" role="main">
				<nav class="navbar navbar-inverse">
				<div class="container">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse"
								data-target="#bs-example-navbar-collapse-1"
								aria-expanded="false">
								<span class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="#">资料修改</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
								<li><a href="<c:url value='/Users/RangeServlet?method=findRange'/>">物品报修</a></li>
								<li><a
									href="<c:url value='/Users/RecordAddServlet?method=loadTable&uid=${sessionUser.uid }'/>">我的报单</a></li>
								<li class="active"><a href="<c:url value='/Users/FloorServlet?method=findName'/>">资料修改</a></li>
								<li><a href="<c:url value='/UserServlet?method=exit'/>">注销</a></li>
							</ul>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</div>
				</nav>
				<!--资料修改-->
				<div class="bs-example center-block"
					data-example-id="simple-responsive-table">
					<form class="form-horizontal"
						action="<c:url value='/UserServlet'/>" id="form" method="post">
						<input type="hidden" name="method" value="edit"/> 
						<input type="hidden" name="uid" value="${sessionUser.uid }"/> 
						<input type="hidden" name="loginname" value="${sessionUser.loginname }"/>
						<div class="col-md-6">
							<div class="input-group input-group-lg input" id="msg">
								<c:if test="${empty sessionUname}">
									<span style="font-size: 18px;line-height: 30px;color: red;">请填全所有信息，否则不能报修！</span>
								</c:if>
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">用户姓名</span> <input
									type="text" class="form-control input" placeholder=""
									aria-describedby="sizing-addon1" id="uname" name="uname"
									value="">
							</div>
							<div class="input-group input-group-lg input" id="unameError" style="font-size: 18px;line-height: 30px;color: red;">
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">&nbsp&nbsp性别&nbsp&nbsp</span>
								<select class="form-control" id="sex" name="sex" value="">
									<option disabled="disabled">选择性别</option>
									<option>男</option>
									<option>女</option>
								</select>
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">修改密码</span> <input
									type="password" class="form-control input" placeholder=""
									aria-describedby="sizing-addon1" id="loginpass"
									name="loginpass">
							</div>
							<div class="input-group input-group-lg input" id="loginpassError" style="font-size: 18px;line-height: 30px;color: red;">
								<span></span>
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">确认密码</span> <input
									type="password" class="form-control input" placeholder=""
									aria-describedby="sizing-addon1" id="reloginpass"
									name="reloginpass">
							</div>
							<div class="input-group input-group-lg input" id="reloginpassError" style="font-size: 18px;line-height: 30px;color: red;">
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">所在楼栋</span>
									<select class="form-control" name="fname" id="fname">
										<option disabled="disabled">选择楼栋</option>
										<c:forEach items="${fname }" var="f">
											<option value="${f.fname }">${f.fname }</option>
										</c:forEach>
									</select>
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">所在宿舍</span> <input
									type="text" class="form-control input" placeholder=""
									aria-describedby="sizing-addon1" id="address" name="address">
							</div>
							<div class="input-group input-group-lg input" id="addressError" style="font-size: 18px;line-height: 30px;color: red;">
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">联系电话</span> <input
									type="text" class="form-control input" placeholder=""
									aria-describedby="sizing-addon1" id="phone" name="phone">
							</div>
							<div class="input-group input-group-lg input" id="phoneError" style="font-size: 18px;line-height: 30px;color: red;">
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">电子邮箱</span> <input
									type="text" class="form-control input" placeholder=""
									aria-describedby="sizing-addon1" id="email" name="email">
							</div>
							<div class="input-group input-group-lg input" id="emailError" style="font-size: 18px;line-height: 30px;color: red;">
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-6 center-block">
									<button type="submit" class="btn btn-default">修改</button>
									<button type="submit" class="btn btn-default">重置</button>
								</div>
							</div>
						</div>
					</form>
				</div>


				<blockquote class="blockquote-reverse"
				style="background-color: #009688; margin: 0 auto;">
				<p style="color: #fff;">版权所有</p>
				<footer> <cite title="Source Title" style="color:#fff">SK-Keith</cite> 
					<a href="https://github.com/SK-Keith/Fix" style="color:#000;" target="_blank" >点击访问</a> </footer>
			</blockquote>
			</div>
		</div>
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="<c:url value='/jsps/js/jquery.min.js'/>"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value='/jsps/js/bootstrap.min.js'/>"></script>
	<script type="text/javascript">
	$.ajax({
		async:true,
		cache:false,
		url:"/Fix/Users/RecordAddServlet",
		data:{method:"ajaxJudgeTime"},
		type:"POST",
		dataType:"json",
		success:function(result){
		}
	});
	</script>
	
</body>
</html>
