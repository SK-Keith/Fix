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

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<title>报修系统</title>

</head>
<body>

	<img src="<c:url value='/jsps/images/back3.png'/>"
		class="img-responsive center-block" alt="Responsive image">
	<div class="bs-example center-block"
		data-example-id="simple-responsive-table">
		<form class="form-horizontal" action="<c:url value='/UserServlet'/>" method="post" target="_top">
			<input type="hidden" name="method" value="login"/>
			<div class="col-md-6">
				<div class="input-group input-group-lg">
					<span class="input-group-addon" id="sizing-addon1">用户名</span> <input
						type="text" class="form-control" placeholder=""
						aria-describedby="sizing-addon1" name="loginname" id="loginname" value="${form.loginname }"/>
				</div>
				<div class="input-group input-group-lg">
					<span class="input-group-addon" id="sizing-addon1">&nbsp密码&nbsp&nbsp</span>
					<input type="password" class="form-control" placeholder=""
						aria-describedby="sizing-addon1" name="loginpass" id="loginpass" value="" />
				</div>
				<div class="input-group input-group-lg">
					<span class="input-group-addon" id="sizing-addon1">验证码</span> <input
						type="text" class="form-control" placeholder=""
						aria-describedby="sizing-addon1" name="verifyCode" id="verifyCode" value="${form.verifyCode }">
				</div>
				<img src="<c:url value='/VerifyCodeServlet'/>" style="margin-left: 100px;" id="vCode"/>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6 center-block">
						<button type="submit" class="btn btn-default">提交</button>
						<button type="submit" class="btn btn-default">重写</button>
					</div>
				</div>
			</div>
		</form>
	</div>



	<blockquote class="blockquote-reverse"
		style="background-color: #2C4D38; margin: 0 auto;">
		<p style="color: #fff;">版权所有</p>
		<footer> <cite title="Source Title">姚棉贤</cite> </footer>
	</blockquote>
	</div>
	</div>
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="<c:url value='/jsps/js/jquery.min.js'/>"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value='/jsps/js/bootstrap.min.js'/>"></script>
</body>
</html>
