<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<!-- //for-mobile-apps -->
<link href="<c:url value='/jsps/css/bootstrap.css'/>" rel="stylesheet"
	type="text/css" media="all" />
<link href="<c:url value='/jsps/css/style.css'/>" rel="stylesheet"
	type="text/css" media="all" />
<link href="<c:url value='/jsps/css/main.css'/>" rel="stylesheet">
<link href="<c:url value='/jsps/css/main_xiugai.css'/>" rel="stylesheet">
<!-- js -->
<script src="<c:url value='/jsps/js/jquery-1.11.1.min.js'/>"></script>
<!-- //js -->
<!-- login-pop-up -->
<script src="<c:url value='/jsps/js/menu_jquery.js'/>"></script>
<!-- //login-pop-up -->
<!-- animation-effect -->
<link href="<c:url value='/jsps/css/animate.min.css'/>" rel="stylesheet">
<script src="<c:url value='/jsps/js/wow.min.js'/>"></script>
<script src="<c:url value='/jsps/js/user/login.js'/>"></script>
<style type="text/css">
#button1 {
	margin: 1.8em 0 1.7em;
	width: 100%;
	outline: none;
	border: none;
	background: rgb(2, 178, 181);
	font-size: 16px;
	padding: 13px 0;
	text-align: center;
	color: #fff;
	font-family: 'Oswald', sans-serif;
	font-weight: 300;
	letter-spacing: 1px;
}

#regist1 {
	margin: -10px 0 1.7em;
	width: 100%;
	outline: none;
	border: none;
	background: rgb(115, 216, 218);;
	font-size: 16px;
	padding: 13px 0;
	text-align: center;
	color: #fff;
	font-family: 'Oswald', sans-serif;
	font-weight: 300;
	letter-spacing: 1px;
}

@media ( min-width : 992px) {
	.col-md-6 {
		width: 100% !important;
	}
}

@media ( max-width : 768px) {
	.footer-copy {
		padding-top: 100px !important;
	}
}
</style>
<script type="text/javascript">
	function windowsDialog1(){
		window.location.href = "<c:url value='/jsps/user/register.jsp'/>"; 
		window.event.returnValue=false;
	}
</script>
<!-- //animation-effect -->
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oswald:400,300,700'
	rel='stylesheet' type='text/css'>
</head>

<title>报修系统</title>

</head>
<body>
	<!-- header -->
	<div class="header-nav">
		<div class="container">
			<nav class="navbar navbar-default"> <!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<div class="logo" data-wow-delay=".5s">
					<img style="float: left;"
						src="<c:url value='/jsps/images/xy.jpg'/>"> <a
						class="navbar-brand" href="#">惠州学院报修系统</a>
				</div>
			</div>
			</nav>
		</div>
	</div>
	<!-- //header -->
	<!-- banner -->
	<div class="banner">
		<div class="banner-pos banner1">
			<div class="container"></div>
			<div class="banner-posit animated wow zoomIn" data-wow-delay=".5s"
				style="margin: 0 auto;">
				<h2>登录</h2>
				<form class="form-horizontal" action="/servlet/UserServlet"
					method="post" target="_top">
					<input type="hidden" name="method" value="login" />
					<div class="col-md-6">
						<div class="input-group input-group-lg"
							style="padding-top: 30px; color: red;">
							<label class="error" id="msg">${msg }</label>
							</td>
						</div>
						<div class="reservation">
							<h5>用户名</h5>
							<div class="book_date">
								<input class="date" id="loginname" type="text" value="输入用户名"
									onfocus="this.value = '';"
									onblur="if (this.value == '') 
					 	 {this.value = '输入用户名';}"
									required="" name="loginname">
							</div>
							<h5>密码</h5>
							<div class="book_date">
								<input class="date" id="loginpass" type="password"
									value="" onfocus="this.value = '';"
									onblur="if (this.value == '') 
						{this.value = '';}"
									required="" name="loginpass">
							</div>
						</div>
						<!-- 
				<div class="input-group input-group-lg">
					<span class="input-group-addon" id="sizing-addon1">验证码</span> <input
						type="text" class="form-control" placeholder=""
						aria-describedby="sizing-addon1" name="verifyCode" id="verifyCode" value="${form.verifyCode }">
				</div>
				<img src="<c:url value='/VerifyCodeServlet'/>" style="margin-left: 100px;" id="vCode"/>
				 -->
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-12 center-block">
								<input type="submit" value="登录" id="button1"> 
							</div>
							<div class="col-sm-offset-2 col-sm-12 center-block" onclick="windowsDialog1()">
								<input class="zhuce" type="submit" value="注册" id="regist1">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>


	<!---strat-date-piker---->
	<link rel="stylesheet" href="css/jquery-ui.css" />
	<script src="js/jquery-ui.js"></script>
	<script>
		$(function() {
			$("#datepicker,#datepicker1").datepicker();
		});
		
	</script>
	<!---/End-date-piker---->



	<div class="footer-copy">
		<div class="container">
			<div class="footer-left animated wow slideInUp" data-wow-delay=".5s">
				<p>By:SK-Keith</p>
				<a href="https://github.com/SK-Keith/Fix" target="_blank">开始访问</a>
			</div>

		</div>
	</div>
	<!-- //footer -->
	<script src="<c:url value='/jsps/js/bootstrap1.js'/>"></script>

</body>
</html>
