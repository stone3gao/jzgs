<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/vend/reportVend/">供应商列表</a></li>
		<shiro:hasPermission name="vend:reportVend:edit"><li><a href="${ctx}/vend/reportVend/form">供应商添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="reportVend" action="${ctx}/vend/reportVend/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>名称</th>
				<th>地址</th>
				<th>联系方式</th>
				<th>联系人</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="vend:reportVend:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reportVend">
			<tr>
				<td><a href="${ctx}/vend/reportVend/form?id=${reportVend.id}">
					${reportVend.code}
				</a></td>
				<td>
					${reportVend.name}
				</td>
				<td>
					${reportVend.address}
				</td>
				<td>
					${reportVend.phone}
				</td>
				<td>
					${reportVend.people}
				</td>
				<td>
					${reportVend.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${reportVend.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${reportVend.remarks}
				</td>
				<shiro:hasPermission name="vend:reportVend:edit"><td>
    				<a href="${ctx}/vend/reportVend/form?id=${reportVend.id}">修改</a>
					<a href="${ctx}/vend/reportVend/delete?id=${reportVend.id}" onclick="return confirmx('确认要删除该供应商吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>