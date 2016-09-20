<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目工程管理</title>
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
		<li class="active"><a href="${ctx}/project/reportProject/">项目工程列表</a></li>
		<shiro:hasPermission name="project:reportProject:edit"><li><a href="${ctx}/project/reportProject/form">项目工程添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="reportProject" action="${ctx}/project/reportProject/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目编码：</label>
				<form:input path="pcode" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>工程名称：</label>
				<form:input path="pname" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>项目类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('report_project_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>项目状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('report_project_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开始时间：</label>
				<input name="startdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportProject.startdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="enddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportProject.enddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目编码</th>
				<th>工程名称</th>
				<th>工程地址</th>
				<th>成本</th>
				<th>投入人数</th>
				<th>项目周期</th>
				<th>项目进度</th>
				<th>项目类型</th>
				<th>项目负责人</th>
				<th>项目状态</th>
				<th>项目盈利</th>
				<shiro:hasPermission name="project:reportProject:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reportProject">
			<tr>
				<td><a href="${ctx}/project/reportProject/form?id=${reportProject.id}">
					${reportProject.pcode}
				</a></td>
				<td>
					${reportProject.pname}
				</td>
				<td>
					${reportProject.paddr}
				</td>
				<td>
					${reportProject.cost}
				</td>
				<td>
					${reportProject.people}
				</td>
				<td>
					${reportProject.period}
				</td>
				<td>
					${reportProject.process}
				</td>
				<td>
					${fns:getDictLabel(reportProject.type, 'report_project_type', '')}
				</td>
				<td>
					${reportProject.leadinguserid}
				</td>
				<td>
					${fns:getDictLabel(reportProject.status, 'report_project_status', '')}
				</td>
				<td>
					${reportProject.profit}
				</td>
				<shiro:hasPermission name="project:reportProject:edit"><td>
    				<a href="${ctx}/project/reportProject/form?id=${reportProject.id}">修改</a>
					<a href="${ctx}/project/reportProject/delete?id=${reportProject.id}" onclick="return confirmx('确认要删除该项目工程吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>