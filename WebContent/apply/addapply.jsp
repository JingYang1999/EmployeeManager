<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="/EmployeeManager/css/css.css" type="text/css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="/EmployeeManager/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" />
<link href="/EmployeeManager/js/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="/EmployeeManager/js/jquery-1.11.0.js"></script>
<script type="text/javascript"
	src="/EmployeeManager/js/jquery-migrate-1.2.1.js"></script>
<script src="/EmployeeManager/js/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script src="/EmployeeManager/js/ligerUI/js/plugins/ligerDrag.js"
	type="text/javascript"></script>
<script src="/EmployeeManager/js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="/EmployeeManager/js/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<link href="/EmployeeManager/css/pager.css" type="text/css"
	rel="stylesheet" />
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<link
	href="${pageContext.request.contextPath}/js/My97DatePicker/skin/WdatePicker.css"
	rel="stylesheet" type="text/css">
<script>
	function app_submit() {
		var applyreason = $("#reason").val();
		var begintime = $("#starttime").val();
		var endtime = $("#endtime").val();
		var remark = $("#applyremark").val();
		var applytype = $("#applytype").val();

		alert(applyreason);
		alert(remark);
		alert(applytype);
		$
				.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/addApply",
					async : true,
					data : {
						applyreason : applyreason,
						starttime : begintime,
						endtime : endtime,
						remark : remark,
						applytype : applytype,
					},
					dataType : "json",
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("出错了，请于管理员联系");
					},
					success : function(json) {
						if (json.message == "ok") {
							alert("申請成功。");
							window.location.href = "${pageContext.request.contextPath}/GetApplyInfo";
						} else {
							alert("申請失敗。");
							return;
						}
					}
				});
	}
</script>
</head>
<body>
</body>



<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="10"></td>
	</tr>
	<tr>
		<td width="15" height="32"><img
			src="/EmployeeManager/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img
			src="/EmployeeManager/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：申请管理
			&gt; 提交申请</td>
		<td width="15" height="32"><img
			src="/EmployeeManager/images/main_locright.gif" width="15"
			height="32"></td>
	</tr>
</table>
<!-- 导航 -->
<table width="100%" height="90%" border="0" cellspacing="0"
	class="main_tabbor">
	<tr>
		<td><font>申請類型：</font></td>
		<td><select id="applytype">
				<option value="请假">请假</option>
				<option value="外出">外出</option>
				<option value="加班">加班</option>
		</select></td>
	</tr>
	<tr>
		<td><font>申請人：</font></td>
		<td><input value="${sessionScope.empentity.empname}"
			disabled="disabled"></input></td>
	</tr>
	<tr>
		<td><font>申請原因：</font></td>
		<td><textarea id="reason" value=""> </textarea></td>
	</tr>
	<tr>
		<td><font>開始時間：</font></td>
		<td><input class="Wdate"
			onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});"
			name="starttime" id="starttime" size="40" value=""></td>
	</tr>
	<tr>
		<td><font>終止時間：</font></td>
		<td><input class="Wdate"
			onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});"
			name="endtime" id="endtime" size="40" value=""></td>
	</tr>
	<tr>
		<td><font>備注：</font></td>
		<td><input id="applyremark" value=""></input></td>
	</tr>
	<tr>
		<td><input type="button" onclick="app_submit()" value="提交"></input></td>
	</tr>


</table>

</html>