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
<link href="<c:url value='/jsps/css/main_baodan.css'/>" rel="stylesheet">

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
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="#">我的报单</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
								<li><a
									href="<c:url value='/Users/RangeServlet?method=findRange'/>">物品报修</a></li>
								<li class="active"><a
									href="<c:url value='/Users/RecordAddServlet?method=loadTable&uid=${sessionUser.uid }'/>">我的报单</a></li>
								<li><a
									href="<c:url value='/Users/FloorServlet?method=findName'/>">资料修改</a></li>
									<li><a href="<c:url value='/UserServlet?method=exit'/>">注销</a></li>
							</ul>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</div>
				</nav>

				<!--报修名单-->
				<div class="table-responsive">
					<table class="table table-bordered" style="text-align:center;">
						<thead>
							<tr>
								<th class="active" style="text-align:center;">编号</th>
								<th class="danger" style="text-align:center;">故障描述</th>
								<th class="warning" style="text-align:center;">报修时间</th>
								<th class="danger" style="text-align:center;">物品状态</th>
								<th class="success" style="text-align:center;">用户操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pb.beanList }" var="r" varStatus="status"
										begin="0">
								<tr>
									<td>${status.index + 1}</td>
									<td>${r.details }</td>
									<td>${r.date1 }</td>
									<td>
										<c:choose>
											<c:when test="${r.status eq 1}">未修复</c:when>
											<c:when test="${r.status eq 2}">已修复</c:when>
											<c:when test="${r.status eq 4}">已催修</c:when>
										</c:choose></td>
									<td>
										<span style="margin-right: 40px;"><a href="<c:url value='/Users/RecordAddServlet?method=findAdmin&rid=${r.rid }'/>">详情</a></span>
										<c:if test="${r.status eq 1 }">
											<span style="margin-right: 40px;"><a href="<c:url value='/Users/RecordAddServlet?method=cancel&rid=${r.rid }&uid=${sessionUid }'/>">取消</a></span>
										</c:if>
										<c:if test="${r.status eq 2 }">
											<c:if test="${empty r.evaluation}">
												<span style="margin-right: 40px;"><a href="<c:url value='/Users/RecordAddServlet?method=loadRid&rid=${r.rid }'/>">评价</a></span>
											</c:if>
											<c:if test="${not empty r.evaluation}">
												<span style="margin-right: 40px;"><a href="<c:url value='/Users/RecordAddServlet?method=loadRid&rid=${r.rid }'/>">修改评价</a></span>
											</c:if>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div>
			<%@include file="/jsps/pager/pager.jsp"%>


			<blockquote class="blockquote-reverse"
				style="background-color: #009688; margin: 0 auto;">
				<p style="color: #fff;">版权所有</p>
				<footer> <cite title="Source Title" style="color:#fff">SK-Keith</cite> 
					<a href="https://github.com/SK-Keith/Fix" style="color:#000;" target="_blank" >点击访问</a> </footer>
			</blockquote>
		</div>
	</div>
	</div>
</body>

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
</html>
