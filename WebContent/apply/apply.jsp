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
<script type="text/javascript">
        $(function () {
            /** 获取上一次选中的部门数据 */
            var boxs = $("input[type='checkbox'][id^='box_']");

            /** 给全选按钮绑定点击事件  */
            $("#checkAll").click(function () {
                // this是checkAll  this.checked是true
                // 所有数据行的选中状态与全选的状态一致
                boxs.attr("checked", this.checked);
            })

            /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
            $("tr[id^='data_']").hover(function () {
                $(this).css("backgroundColor", "#eeccff");
            }, function () {
                $(this).css("backgroundColor", "#ffffff");
            })


            /** 删除员工绑定点击事件 */
            $("#delete").click(function () {
                /** 获取到用户选中的复选框  */
                var checkedBoxs = boxs.filter(":checked");
                if (checkedBoxs.length < 1) {
                    $.ligerDialog.error("请选择一个需要删除的部门！");
                } else {
                    /** 得到用户选中的所有的需要删除的ids */
                    var ids = checkedBoxs.map(function () {
                        return this.value;
                    })
                    //alert(id.get());
                    $.ligerDialog.confirm("确认要删除吗?", "删除部门", function (r) {
                        if (r) {
                            // alert("删除："+ids.get());
                            // 发送请求
                            window.location.href = "DelDepSvl?ids=" + ids.get();
                        }
                    });
                }
            })
        })
        function gotoPage(totalPage) {
            var num = $("#pager_jump_page_size").val();
            var type = $("#applytype").val();
            var stat = $("#applystatus").val();
            if (num > 0 && num <= totalPage) {
                window.location.href = "GetApplyInfo?current=" + num + "&findapplytype=" + type+"&findapplystatus="+stat;
            } else {
                alert("跳转页数只能是1-" + totalPage + "之间");
            }
        }
    </script>
<script type="text/javascript">
        function doSearch() {
            var type = $("#applytype").val();
            var stat = $("#applystatus").val();
            var curn = $("#currentPage").val();
            window.location.href = "GetApplyInfo?current=" + curn + "&findapplytype=" + type+"&findapplystatus="+stat;
        }
        
        function gopage(n){
            var type = $("#applytype").val();
            var stat = $("#applystatus").val();

            window.location.href = "GetApplyInfo?current=" + n + "&findapplytype=" + type+"&findapplystatus="+stat;
        }

        function approve(flag,appid)
		{
			$.ajax({
		type : "POST",
		url : "${pageContext.request.contextPath}/ApproveApply",
		async : true,

		data : {
			approve_flag : flag,
			apply_id:appid,
		},
		dataType : "json",
		error : function(XMLHttpRequest, textStatus,
				errorThrown) {
			alert("出错啦，请于管理员联系");
		},
		success : function(json) {
			if (json.message == "ok") {
				doSearch()
			} else {
				alert("出错了，请于管理员联系")
				return "false";
			}
		}
	});
	}
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
				src="/EmployeeManager/images/main_locleft.gif" width="15"
				height="32"></td>
			<td class="main_locbg font2"><img
				src="/EmployeeManager/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：申请管理
				&gt; 申请查询</td>
			<td width="15" height="32"><img
				src="/EmployeeManager/images/main_locright.gif" width="15"
				height="32"></td>
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
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="font3">申请类型： <select id="applytype"
										onchange="doSearch()">
											<c:if test="${requestScope.findapplytype eq 'all'}">
												<option value="all" selected="selected">all</option>
												<option value="请假">请假</option>
												<option value="外出">外出</option>
												<option value="加班">加班</option>
											</c:if>

											<c:if test="${requestScope.findapplytype eq '请假'}">
												<option value="all">all</option>
												<option value="请假" selected="selected">请假</option>
												<option value="外出">外出</option>
												<option value="加班">加班</option>
											</c:if>
											<c:if test="${requestScope.findapplytype eq '外出'}">
												<option value="all">all</option>
												<option value="请假">请假</option>
												<option value="外出" selected="selected">外出</option>
												<option value="加班">加班</option>
											</c:if>
											<c:if test="${requestScope.findapplytype eq '加班'}">
												<option value="all">all</option>
												<option value="请假">请假</option>
												<option value="外出">外出</option>
												<option value="加班" selected="selected">加班</option>
											</c:if>
									</select>

									</td>
									<td class="font3">申请状态： <select id="applystatus"
										onchange="doSearch()">
											<c:if test="${requestScope.findapplystatus eq 'all'}">
												<option value="all" selected="selected">all</option>
												<option value="待审核">待审核</option>
												<option value="已批准">已批准</option>
												<option value="已拒绝">已拒绝</option>
											</c:if>
											<c:if test="${requestScope.findapplystatus eq '待审核'}">
												<option value="all">all</option>
												<option value="待审核" selected="selected">待审核</option>
												<option value="已批准">已批准</option>
												<option value="已拒绝">已拒绝</option>
											</c:if>
											<c:if test="${requestScope.findapplystatus eq '已批准'}">
												<option value="all">all</option>
												<option value="待审核">待审核</option>
												<option value="已批准" selected="selected">已批准</option>
												<option value="已拒绝">已拒绝</option>
											</c:if>
											<c:if test="${requestScope.findapplystatus eq '已拒绝'}">
												<option value="all">all</option>
												<option value="已拒绝">待审核</option>
												<option value="已批准">已批准</option>
												<option value="已拒绝" selected="selected">已拒绝</option>
											</c:if>
									</select>

									</td>

									<input type="hidden" id="currentPage" value="1" />
									</td>
								</tr>
							</table>

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
							<td>申请类型</td>
							<td>申请人</td>
							<td>申请原因</td>
							<td>状态</td>
							<td>申请时间</td>

							<td>起始时间</td>
							<td>终止时间</td>

							<td>审批人</td>

							<td>审批时间</td>

							<td>备注</td>
							<c:if test="${sessionScope.emprole eq '2'}">
								<td align="center">批准</td>
								<td align="center">拒绝</td>
							</c:if>
						</tr>

						<c:forEach var="apply" items="${requestScope.pageinfo.lists}">
							<tr id="data_0" align="center" class="main_trbg"
								style="background-color: rgb(255, 255, 255);">
								<td>${apply.applytype}</td>
								<td>${apply.empname}</td>
								<td>${apply.applyreason}</td>
								<td>${apply.applystatus}</td>
								<td>${apply.applytime}</td>

								<td>${apply.starttime}</td>
								<td>${apply.endtime}</td>

								<td>${apply.approvername}</td>

								<td>${apply.approvertime}</td>

								<td>${apply.remark}</td>
								<c:if test="${sessionScope.emprole eq '2'}">
									<c:if
										test="${apply.applystatus ne '已批准' and  apply.applystatus ne '已拒绝'}">
										<td align="center"
											onclick="approve('true','${apply.applyid}')">批准</td>
										<td align="center"
											onclick="approve('false','${apply.applyid}')">拒绝</td>
									</c:if>
									<c:if
										test="${apply.applystatus eq '已批准'  or  apply.applystatus eq '已拒绝'}">
										<td align="center"></td>
										<td align="center"></td>
									</c:if>
								</c:if>
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
								style="COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none">

								<c:if test="${pageinfo.currentPage ne 1}">
									<span class="disabled"> <a onclick="gopage(${pageinfo.currentPage-1})">上一页</a></span>
								</c:if> <c:if test="${pageinfo.currentPage != pageinfo.totalPage }">
									<a onclick="gopage(${pageinfo.currentPage+1})">下一页</a>
								</c:if>&nbsp;跳转到&nbsp;&nbsp; <input
								style="text-align: center; BORDER-RIGHT: #aaaadd 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #aaaadd 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #aaaadd 1px solid; COLOR: #000099; PADDING-TOP: 2px; BORDER-BOTTOM: #aaaadd 1px solid; TEXT-DECORATION: none"
								type="text" size="2" id="pager_jump_page_size">&nbsp;<input
								type="button"
								style="text-align: center; BORDER-RIGHT: #dedfde 1px solid; PADDING-RIGHT: 6px; BACKGROUND-POSITION: 50% bottom; BORDER-TOP: #dedfde 1px solid; PADDING-LEFT: 6px; PADDING-BOTTOM: 2px; BORDER-LEFT: #dedfde 1px solid; COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; BORDER-BOTTOM: #dedfde 1px solid; TEXT-DECORATION: none"
								value="确定" id="pager_jump_btn"
								onclick="gotoPage(${pageinfo.totalPage})">
							</td>
						</tr>
						<tr align="center">
							<td style="font-size: 13px;"></td>
						</tr>
						<tr>
							<td
								style="COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none">总共
								<font color="red">${pageinfo.totalCount}</font>条记录，当前显示第${pageinfo.currentPage}页数据。
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