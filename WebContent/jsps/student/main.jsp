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
<link href="<c:url value='/jsps/css/main_baoxiu.css'/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
   <script type="text/javascript">
   $(function () {
	   alert("go");
	   $.ajax({
		   url:"/Fix/RangeServlet",    
			data:{method:"ajaxFindZhonglei"},
			type:"POST",
			dataType:"json",
		   async:false,
			cache:false,
			success:function(arr){
				alert("succ");
				for(var i = 0; i< arr.length; i++){
					var option = $("<option>").val(arr[i].cid).text(arr[i].cname);
					$("#zl").append(option);
				}
			}
			
	   });
   });
   </script>
<title>报修系统</title>
</head>
<body>
	<img src="<c:url value='/jsps/images/back3.png'/>"
		class="img-responsive center-block" alt="Responsive image">
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
									<li class="active"><a href="#">物品报修</a></li>
									<li><a
										href="<c:url value='/jsps/student/main_baodan.jsp'/>">我的报单</a></li>
									<li><a
										href="<c:url value='/jsps/student/main_xiugai.jsp'/>">资料修改</a></li>
									<li><a href="<c:url value=''/>">学生：${username }个人中心</a></li>
									<li><a href="<c:url value='/UserServlet?method=quit'/>">点击注销</a>
									</li>
								</ul>

							</div>
							<!-- /.navbar-collapse -->
						</div>
						<!-- /.container-fluid -->
					</div>
					</nav>

					<div class="bs-example center-block"
						data-example-id="simple-responsive-table">
						<form class="form-horizontal"
							action="<c:url value='/AddBookServlet'/>"
							enctype="multipart/form-data" method="post" id="form">
							<div class="col-md-6">

								<div class="input-group input-group-lg">

									<span class="input-group-addon" id="sizing-addon1">报修种类</span>
									<select class="form-control" id="zl" >
										<option>选择种类</option>
										
										
										
									</select>
								</div>

								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">所在区域</span>
									<select class="form-control" id="qy" onchange="loadQuyu()">
										<option>选择区域</option>
										
										
										
									</select>
								</div>
								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">物品名称</span>
									<input type="text" class="form-control" placeholder=""
										aria-describedby="sizing-addon1" name="pname">
								</div>
								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">维修地址</span>
									<input type="text" class="form-control" placeholder=""
										aria-describedby="sizing-addon1" name="address">
								</div>
								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">故障描述</span>
									<input type="text" class="form-control" placeholder=""
										aria-describedby="sizing-addon1" name="details">
								</div>
								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">报修人员</span>
									<input type="text" class="form-control" placeholder=""
										aria-describedby="sizing-addon1" name="uname">
								</div>
								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">联系电话</span>
									<input type="text" class="form-control" placeholder=""
										aria-describedby="sizing-addon1" name="phone">
								</div>
								<div class="input-group input-group-lg">
									<span class="input-group-addon" id="sizing-addon1">提交图片</span>
									<input type="text" class="form-control" placeholder="在下面提交图片"
										aria-describedby="sizing-addon1">
								</div>
								<div class="ibox-content">
									<div class="row">
										<div class="col-sm-6" id="updatebox">
											<label for="file">
												<div class="panel updatepanel">
													<div class="addbox">
														<span class="icon-add-50"></span>
													</div>
													<input type="file" id="file" style="" />
												</div>
											</label>
										</div>
									</div>

								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-6 center-block">
									<button type="submit" class="btn btn-default">提交</button>
									<button type="submit" class="btn btn-default">重写</button>
								</div>
							</div>
							</form>
					</div>
					
				</div>

			</div>

			<nav aria-label="Page navigation">
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
			</nav>

			<blockquote class="blockquote-reverse"
				style="background-color: #2C4D38; margin: 0 auto;">
				<p style="color: #fff;">版权所有</p>
				<footer> <cite title="Source Title">姚棉贤</cite></footer>
			</blockquote>
		</div>
	</div>
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="<c:url value='/jsps/js/jquery.min.js'/>"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value='/jsps/js/bootstrap.min.js'/>"></script>
	<script>
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