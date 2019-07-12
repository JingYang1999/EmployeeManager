<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>人事管理系统 ——员工管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="${pageContext.request.contextPath}/css/css.css"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" />
<link
	href="${pageContext.request.contextPath}/js/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.js"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDrag.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/pager.css"
	type="text/css" rel="stylesheet" />

<script type="text/javascript">
	$(function() {
		/** 获取上一次选中的部门数据 */
		var boxs = $("input[type='checkbox'][id^='box_']");

		/** 给全选按钮绑定点击事件  */
		$("#checkAll").click(function() {
			// this是checkAll  this.checked是true
			// 所有数据行的选中状态与全选的状态一致
			boxs.attr("checked", this.checked);
		})

		/** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
		$("tr[id^='data_']").hover(function() {
			$(this).css("backgroundColor", "#eeccff");
		}, function() {
			$(this).css("backgroundColor", "#ffffff");
		})

		/** 删除员工绑定点击事件 */
		$("#delete")
				.click(
						function() {
							/** 获取到用户选中的复选框  */
							var checkedBoxs = boxs.filter(":checked");
							if (checkedBoxs.length < 1) {
								$.ligerDialog.error("请选择一个需要删除的员工！");
							} else {
								/** 得到用户选中的所有的需要删除的ids */
								var ids = checkedBoxs.map(function() {
									return this.value;
								})

								$.ligerDialog
										.confirm(
												"确认要删除吗?",
												"删除员工",
												function(r) {
													if (r) {
														alert("删除：" + ids.get());
														// 发送请求
														window.location = "${pageContext.request.contextPath}/DelEmpSvl?empids="
																+ ids.get();
													}
												});
							}
						})
	})
</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td width="15" height="32"><img
				src="${pageContext.request.contextPath}/images/main_locleft.gif"
				width="15" height="32"></td>
			<td class="main_locbg font2"><img
				src="${pageContext.request.contextPath}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理
				&gt; 员工查询</td>
			<td width="15" height="32"><img
				src="${pageContext.request.contextPath}/images/main_locright.gif"
				width="15" height="32"></td>
		</tr>
	</table>

	<table width="100%" height="90%" border="0" cellpadding="5"
		cellspacing="0" class="main_tabbor">
		<!-- 查询区  -->
		<tr valign="top">
			<td height="30">
				<table width="100%" border="0" cellpadding="0" cellspacing="10"
					class="main_tab">
					<tr>
						<td class="fftd">
							<form action="SearchEmpSvl" methord="post">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="font3">职位： <select name="job_id"
											style="width: 143px;">
												<option value="0">--请选择职位--</option>
												<c:forEach items="${requestScope.jobs }" var="job">
													<option value="${job.jobid }">${job.jobname }</option>
												</c:forEach>
										</select> 姓名：<input type="text" name="name"> 身份证号码：<input
											type="text" name="cardId" maxlength="18">
										</td>
									</tr>
									<tr>
										<td class="font3">性别： <select name="sex"
											style="width: 143px;">
												<option value="0">--请选择性别--</option>
												<option value="1">男</option>
												<option value="2">女</option>
										</select> 手机：<input type="text" name="phone"> 所属部门：<select
											name="dept_id" style="width: 100px;">
												<option value="0">--部门选择--</option>
												<c:forEach items="${requestScope.deps }" var="dep">
													<option value="${dep.depid }">${dep.depname }</option>
												</c:forEach>
										</select>&nbsp; <input type="submit" value="搜索" /> <input id="delete"
											type="button" value="删除" />
										</td>
									</tr>
								</table>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<!-- 数据展示区 -->
		<tr valign="top">
			<td height="20">
				<table width="100%" border="1" cellpadding="5" cellspacing="0"
					style="border: #c2c6cc 1px solid; border-collapse: collapse;">
					<tbody>
						<tr class="main_trbg_tit" align="center">
							<td><input type="checkbox" name="checkAll" id="checkAll">


							</td>
							<td>姓名</td>
							<td>性别</td>
							<td>手机号码</td>
							<td>邮箱</td>
							<td>职位</td>
							<td>学历</td>
							<td>身份证号码</td>
							<td>部门</td>
							<td>联系地址</td>
							<td>建档日期</td>
							<td align="center">操作</td>
							<td align="center">离职</td>
						</tr>
						<c:forEach var="emp" items="${requestScope.emps}">
							<tr id="data_0" class="main_trbg" align="center"
								style="background-color: rgb(255, 255, 255);">


								<td><c:if test="${emp.empId eq sessionSope.empid }">
										<input type="checkbox" id="box_0" value="${emp.empId }"
											disabled="disabled">
									</c:if> <c:if test="${emp.empId ne sessionSope.empid }">
										<input type="checkbox" id="box_0" value="${emp.empId }">
									</c:if></td>
								<td>${emp.empname}</td>
								<td>${emp.sex}</td>
								<td>${emp.phone}</td>
								<td>${emp.email}</td>
								<td>${emp.jobname}</td>
								<td>${emp.education}</td>
								<td>${emp.qq}</td>
								<td>${emp.depname}</td>
								<td>${emp.address}</td>
								<td>${emp.getCreateTime()}</td>


								<td align="center" width="40px;"><a
									href="${pageContext.request.contextPath}/FindEmpDetailSvl?empid=${emp.empId}">
										<img title="修改" src="../images/update.gif" />
								</a></td>

								<td align="center" width="40px;"><c:if
										test="${emp.empId eq sessionSope.empid }">
										<a>辦不了</a>
									</c:if> <c:if test="${emp.empId ne sessionSope.empid }">
										<a
											href="${pageContext.request.contextPath}/DelEmpSvl?empids=${emp.empId}">
											辦理 </a>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</td>
		</tr>
		<!-- 分页标签 -->
		<tr valign="top">
			<td align="center" class="font3">
				<table width="100%" align="center" style="font-size: 13px;"
					class="digg">
					<tbody>
						<tr>
							<td
								style="COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none"><span
								class="disabled">上一页</span><span class="current">1</span><span
								class="disabled">下一页</span>&nbsp;跳转到&nbsp;&nbsp;<input
								style="text-align: center; BORDER-RIGHT: #aaaadd 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #aaaadd 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #aaaadd 1px solid; COLOR: #000099; PADDING-TOP: 2px; BORDER-BOTTOM: #aaaadd 1px solid; TEXT-DECORATION: none"
								type="text" size="2" id="pager_jump_page_size">&nbsp;<input
								type="button"
								style="text-align: center; BORDER-RIGHT: #dedfde 1px solid; PADDING-RIGHT: 6px; BACKGROUND-POSITION: 50% bottom; BORDER-TOP: #dedfde 1px solid; PADDING-LEFT: 6px; PADDING-BOTTOM: 2px; BORDER-LEFT: #dedfde 1px solid; COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; BORDER-BOTTOM: #dedfde 1px solid; TEXT-DECORATION: none"
								value="确定" id="pager_jump_btn"></td>
						</tr>
						<tr align="center">
							<td style="font-size: 13px;"></td>
						</tr>
						<tr>
							<td
								style="COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none">总共<font
								color="red">3</font>条记录，当前显示1-3条记录。
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	<div style="height: 10px;"></div>
</body>
</html>