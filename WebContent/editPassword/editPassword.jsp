<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html style="height: auto;">

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>CIST 修改密碼</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/metronic/plugins/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/login/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/metronic/plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/metronic/plugins/adminlte/css/adminlte.css">

</head>

<body style="height: auto;">

	<!--<div id="ORG_ADD_DIV_ID" class="card card-info" style="display:none">  -->
	<div id="POST_ADD_DIV_ID" class="card card-info">
		<input id="regUUID" type="hidden" value="" />
		<div class="form-horizontal">
			<div class="card-body">
				<div class="form-group">
					<label for="JI_JOB_NAME" class="col-sm-2 control-label">当前密码：</label>
					<div class="col-sm-10">
						<input value="" type="password" name="oldPassword"
							id="oldPassword" class="form-control" placeholder="请输入您的当前密码"
							style="width: 600px" onblur="checkPswd()" onchange="checkPswd()" oninput="checkPswd()">
					</div>
				</div>
				<div class="form-group">
					<label for="JI_JOB_NAME" class="col-sm-2 control-label">新的密码：</label>
					<div class="col-sm-10">
						<input value="" type="password" name="newPassword"
							id="newPassword" class="form-control" placeholder="请输入您的新密码"
							style="width: 600px" disabled="disabled">
					</div>
				</div>
				<div class="form-group">
					<label for="JI_JOB_NAME" class="col-sm-2 control-label">确认密码：</label>
					<div class="col-sm-10">
						<input value="" type="password" name="newPasswordCon"
							id="newPasswordCon" class="form-control" placeholder="请确认您的新密码"
							style="width: 600px" disabled="disabled">
					</div>
				</div>


				<!-- /.card-body -->
				<div class="card-footer col-md-3 col-md-offset-4"
					style="width: 100%" align="center">
					<button type="button" class="btn btn-info" onclick="subPassword()">提交</button>
				</div>
				<div class="card-footer col-md-3 col-md-offset-4" id="tishi"
					style="text-align: center; color: red; font-size: 15px"></div>
				<!-- /.card-footer -->
			</div>


		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/js/metronic/plugins/jquery/dist/jquery.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/metronic/plugins/jQuery-Storage-API/jquery.storageapi.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/metronic/plugins/jquery.form/jquery.form.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/metronic/plugins/jquery/plugins/scrollbar/perfect-scrollbar.jquery.min.js"></script>


	<script>
		function checkPswd() {
			var oldPassword = $("#oldPassword").val();
			if (typeof (oldPassword) == 'undefined' || oldPassword.trim() == "") {
				$("#tishi").html("当前密码不能为空");
				return false;
			}
			$.ajax({
						type : "POST",
						url : "${pageContext.request.contextPath}/checkPswd",
						async : true,

						data : {
							old_pswd : oldPassword.trim(),
						},
						dataType : "json",
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							alert("出错了，请于管理员联系");
						},
						success : function(json) {
							if (json.message == "ok") {
								//alert("密碼正確");
								document.getElementById("newPassword").disabled = "";
								document.getElementById("newPasswordCon").disabled = "";
								$("#tishi").html(json.message);
								newPassword
								return true;
							} else {

								document.getElementById("newPassword").disabled = "disabled";
								document.getElementById("newPasswordCon").disabled = "disabled";
								//alert("密碼錯誤");
								$("#tishi").html(json.message);
								return false;
							}
						}
					});
		}

		function subPassword() {
			var newPassword = $("#newPassword").val();
			var newPasswordCon = $("#newPasswordCon").val()
			var oldPassword = $("#oldPassword").val()
			var flag = '0';
			if (typeof (newPassword) == 'undefined' || newPassword.trim() == "") {
				$("#tishi").html("新密码不能为空");
				flag = '-1';
			}
			if (newPassword.trim().length < 6 || newPassword.trim().length > 20) {
				$("#tishi").html("新密码必须大于6位小于20位");
				flag = '-2';
			}
			if (typeof (newPasswordCon) == 'undefined'
					|| newPasswordCon.trim() == "") {
				$("#tishi").html("确认密码不能为空");
				flag = '-3';
			}
			if (newPassword.trim() != newPasswordCon.trim()) {
				$("#tishi").html("新密码与确认密码必须保持一致");
				flag = '-4';
			}

			if (typeof (oldPassword) == 'undefined' || oldPassword.trim() == "") {
				$("#tishi").html("当前密码不能为空");
				flag = '-5';
			}
			if (checkPswd() == false) {
				flag = '-6';
			}
			if (flag != '0') {
				alert("err" + flag)
				return flag;
			}

			// window.parent.location.replace("${pageContext.request.contextPath}/loginForm.jsp");

			$.ajax({
						type : "POST",
						url : "${pageContext.request.contextPath}/EditPswdSvl",
						async : true,
						data : {
							new_pswd : newPassword.trim(),
						},
						dataType : "json",
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							alert("出错了，请于管理员联系");
							return '-7';
						},
						success : function(json) {
							if (json.message == "ok") {
								alert("修改成功,請重新登陸");
								window.parent.location
										.replace("${pageContext.request.contextPath}/loginForm.jsp");
								return '0';
							} else {
								$("#tishi").html(json.message);
								return '-8';
							}

						}
					});

		}
	</script>

	<script
		src="${pageContext.request.contextPath}/stmadc/stma/dc/include/js/jcommon.js"></script>
	<script language="JavaScript"
		src="${pageContext.request.contextPath}/stmadc/jquery/jquery-ui-1.8.20.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/stmadc/static/comp/bootstrap/dist/js/bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath}/stmadc/static/comp/adminlte/js/adminlte.min.js"></script>
	<script language="JavaScript"
		src="${pageContext.request.contextPath}/stmadc/stma/dc/include/js/jcommon.js"></script>

</body>

</html>