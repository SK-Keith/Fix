<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<link href="<c:url value='/jsps/css/bootstrap.min.css'/>"
	rel="stylesheet">
<link href="<c:url value='/jsps/css/main.css'/>" rel="stylesheet">
<link href="<c:url value='/jsps/css/upload.css'/>" rel="stylesheet">
<style type="text/css">
	.navbar-inverse {
    background-color: #009688;
    border-color: #009688;
}
.navbar-inverse .navbar-nav>.active>a, .navbar-inverse .navbar-nav>.active>a:focus, .navbar-inverse .navbar-nav>.active>a:hover {
    background-color: #4caf50!important;
}
@media (min-width: 768px){
.col-sm-4 {
    width: 100%!important;
}
}
</style>
<script type="text/javascript"
	src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jsps/js/student/main.js'/>"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript">
	function loadFname(){
		/*
		1.获取trange
		2.发出异步请求，功能如下：
		  3.得到一个数组
		  4.获取fname元素（<select>）,把内部的<option> 全部删除
		  5.添加一个头(<option>) 请选择2级分类(</option>)
		  6.循环数组，把数组中的每个对象转换成一个<option>添加到fname中
		*/
		//1.获取pid
		var trange = $("#trange").val();
		//发出异步请求
		$.ajax({
			async:true,
			cache:false,
			url:"/Fix/Users/RangeServlet",
			data:{method:"ajaxFindChildren",trange:trange},
			type:"POST",
			dataType:"json",
			success:function(arr){
				//3.得到cid，删除它的内容
				$("#fname").empty();//删除元素的子元素
				$("#fname").append($("<option>选择楼栋</option>"));//4.添加头
				//循环遍历数组，把每个对象转换成<option>添加到cid中
				for(var i = 0; i< arr.length; i++){
					var option = $("<option>").val(arr[i].fname).text(arr[i].fname);
					$("#fname").append(option);
				}
			}
		});
	}
</script>
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
				<div class="bs-example" data-example-id="simple-responsive-table">
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
								<a class="navbar-brand" href="#">物品报修</a>
							</div>

							<!-- Collect the nav links, forms, and other content for toggling -->
							<div class="collapse navbar-collapse"
								id="bs-example-navbar-collapse-1">
								<ul class="nav navbar-nav">
									<li class="active"><a href="/Users/RangeServlet?method=findRange">物品报修</a></li>
									<li><a href="<c:url value='/Users/RecordAddServlet?method=loadTable&uid=${sessionUser.uid }'/>">我的报单</a></li>
									<li><a href="<c:url value='/Users/FloorServlet?method=findName'/>">资料修改</a></li>
									<li><a href="<c:url value='/UserServlet?method=exit'/>">注销</a></li>
								</ul>

							</div>
							<!-- /.navbar-collapse -->
						</div>
						<!-- /.container-fluid -->
					</div>
					</nav>

					<div class="bs-example center-block"
						data-example-id="simple-responsive-table">
						<p
							style="font-weight: 900; color: red; font-size: 20px; text-align: center;">${msg }</p>
						<form class="form-horizontal" action="<c:url value='/Users/RecordServlet'/>"
							enctype="multipart/form-data" method="post" id="form">
							<div class="col-md-6">
								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">报修种类</span>
									<select class="form-control" name="tname" id="tname"
										value="${record.range.wname }">
										<option disabled="disabled">选择种类</option>
										<c:forEach items="${range }" var="range">
											<option value="${range.tname }">${range.tname }</option>
										</c:forEach>
									</select>
								</div>
				
								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">所在区域</span>
									<select class="form-control" id="trange" name="trange"
										value="${record.getRange().getTrange()}" onchange="loadFname()">
										<option>选择区域</option>
										<c:forEach items="${range }" var="range">
											<option value="${range.trange }">${range.trange }</option>
										</c:forEach>
									</select>
								</div>
								
								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">选择楼栋</span>
									<select class="form-control" id="fname" name="fname"
										value="">
										<option>选择楼栋</option>
									</select>
								</div>
								
								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">具体地址</span>
									<input type="text" class="form-control" placeholder="门牌号，如101，A101"
										aria-describedby="sizing-addon1" name="address" id="address"
										value="${record.address }">
								</div>
								<div class="input-group input-group-lg input" id="addressError" style="font-size: 18px;line-height: 30px;color: red;">
								</div>
								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">故障描述</span>
									<input type="text" class="form-control" placeholder=""
										aria-describedby="sizing-addon1" name="details" id="details"
										value="${record.details }">
								</div>
								<div class="input-group input-group-lg input" id="detailsError" style="font-size: 18px;line-height: 30px;color: red;">
							</div>
								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">报修人员</span>
									<input type="text" class="form-control" placeholder=""
										aria-describedby="sizing-addon1" name="uname" id="card" 
										value="${sessionUser.uname }" readonly="readonly">
								</div>
								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">联系电话</span>
									<input type="text" class="form-control" placeholder=""
										aria-describedby="sizing-addon1" name="phone" id="phone"
										value="${sessionUser.phone}" readonly="readonly">
								</div>
								<div class="wrapper wrapper-content animated fadeIn" >
									<input type="text" name="uid" id="uid"
										value="${sessionUser.uid }" style="display: none;">
									<div class="row">
										<div class="col-sm-12">
											<div class="ibox">
												<div class="ibox-content">
													<div class="row">
														<div class="col-sm-4" id="updatebox">
															<label for="file">
																<div class="panel updatepanel">
																	<div class="addbox">
																		<span class="icon-add-50"></span>
																	</div>
																	<input type="file" id="file" name="image"
																		style="display: none;" />
																</div>
										  					</label>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-6 center-block" style="margin-right:80px;">
									<button type="submit" class="btn btn-default">提交</button>
									<button type="submit" class="btn btn-default">重写</button>
								</div>
							</div>
						</form>
					</div>


				</div>

			</div>

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
		$(".updatepanel").height($(".panel-info").height());
		document.getElementById('file').onchange = function() {
			add();
			var imgFile = this.files[0];
			var fr = new FileReader();
			fr.onload = function() {
				var imgs = document.getElementsByClassName('updateimg');
				imgs[imgs.length - 1].src = fr.result;
				/*document.getElementById('image').getElementsByTagName('img')[0].src = fr.result;*/
			};
			fr.readAsDataURL(imgFile);
		};
		function add() {
			var html = "<div class='col-sm-6'><div class='panel panel-info'><div class='panel-heading'><i class='fa fa-times'></i></div><div class='panel-body' style='text-align: center;'><div class='row'><div class='col-sm-12 col-md-12'><img class='updateimg img-responsive' src='img/p_big3.jpg' style='width: inherit;height: 210px;'/></div></div></div></div></div>";
			$("#updatebox").before(html);
		}
		$(".fa-times").click(function() {
			alert("111");
			/*alert($(this).parent().parent().parent().html());*/
			$(this).parent().parent().parent().remove();
		});
		/*$(".panel").on("click","i",function(){
		  alert("111");
		  alert($(this).parent().parent().parent().html());
		  $(this).parent().parent().parent().remove();
		});*/
		/*function delete(){
		  
		}*/
	</script>
</body>
</html>