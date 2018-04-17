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
								<li><a href="<c:url value='/jsps/student/main.jsp'/>">物品报修</a></li>
								<li><a
									href="<c:url value='/jsps/student/main_baodan.jsp'/>">我的报单</a></li>
								<li class="active"><a href="#">资料修改</a></li>
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
					<form class="form-horizontal">
						<div class="col-md-6">

							<div class="input-group input-group-lg">

								<span class="input-group-addon" id="sizing-addon1">用户账户</span> <input
									type="text" class="form-control" placeholder=""
									aria-describedby="sizing-addon1">
							</div>

							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">&nbsp&nbsp&nbsp姓名&nbsp&nbsp&nbsp&nbsp</span>
								<input type="text" class="form-control" placeholder=""
									aria-describedby="sizing-addon1">
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">&nbsp&nbsp&nbsp密码&nbsp&nbsp&nbsp&nbsp</span>
								<input type="text" class="form-control" placeholder=""
									aria-describedby="sizing-addon1">
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">所在学院</span> <input
									type="text" class="form-control" placeholder=""
									aria-describedby="sizing-addon1">
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">所在班级</span> <input
									type="text" class="form-control" placeholder=""
									aria-describedby="sizing-addon1">
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">联系电话</span> <input
									type="text" class="form-control" placeholder=""
									aria-describedby="sizing-addon1">
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon" id="sizing-addon1">电子邮箱</span> <input
									type="text" class="form-control" placeholder=""
									aria-describedby="sizing-addon1">
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
