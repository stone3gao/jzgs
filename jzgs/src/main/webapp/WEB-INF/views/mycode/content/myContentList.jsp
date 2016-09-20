<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>内容管理</title>
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
		<li class="active"><a href="${ctx}/content/myContent/">内容列表</a></li>
		<shiro:hasPermission name="content:myContent:edit"><li><a href="${ctx}/content/myContent/form">内容添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="myContent" action="${ctx}/content/myContent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>内容类型：</label>
				<form:select path="contentType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('contentType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>内容类型</th>
				<th>文档内容</th>
				<th>创建时间</th>
				<shiro:hasPermission name="content:myContent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="myContent">
			<tr>
				<td><a href="${ctx}/content/myContent/form?id=${myContent.id}">
					${fns:getDictLabel(myContent.contentType, 'contentType', '')}
				</a></td>
				<td>
					${myContent.content}
				</td>
				<td>
					<fmt:formatDate value="${myContent.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="content:myContent:edit"><td>
    				<a href="${ctx}/content/myContent/form?id=${myContent.id}">修改</a>
					<a href="${ctx}/content/myContent/delete?id=${myContent.id}" onclick="return confirmx('确认要删除该内容吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>