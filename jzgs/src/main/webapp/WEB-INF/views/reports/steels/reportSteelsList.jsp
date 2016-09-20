<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>钢材管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function() {
				top.$.jBox.confirm("确认要导出钢材数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action", "${ctx}/steels/reportSteels/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action", "${ctx}/steels/reportSteels/");
					}
				}, {
					buttonsFocus : 1
				});
				top.$('.jbox-body .jbox-icon').css('top', '55px');
			});
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
		<li class="active"><a href="${ctx}/steels/reportSteels/">钢材列表</a></li>
		<shiro:hasPermission name="steels:reportSteels:edit"><li><a href="${ctx}/steels/reportSteels/form">钢材添加</a></li><li><a href="${ctx}/steels/reportSteels/import">钢材导入</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="reportSteels" action="${ctx}/steels/reportSteels/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>归属项目：</label>
				<form:select path="project.pcode" class="input-medium ">
					<form:option value="" label=""/>
					<form:options items="${projectList}" itemLabel="pname" itemValue="pcode" htmlEscape="false"/>
				</form:select>				
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('report_type_steels')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>型号：</label>
				<form:select path="model" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('report_model_steels')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>规格：</label>
				<form:select path="standard" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('report_standard_steels')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>归属项目</th>
				<th>类型</th>
				<th>型号</th>
				<th>规格</th>
				<th>理论重量</th>
				<th>数量</th>
				<th>已消耗数量</th>
				<th>剩余数量</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="steels:reportSteels:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reportSteels">
			<tr>
				<td><a href="${ctx}/steels/reportSteels/form?id=${reportSteels.id}">
					${reportSteels.id}
				</a></td>
				<td>
					${reportSteels.project.pname}
				</td>
				<td>
					${fns:getDictLabel(reportSteels.type, 'report_type_steels', '')}
				</td>
				<td>
					${fns:getDictLabel(reportSteels.model, 'report_model_steels', '')}
				</td>
				<td>
					${fns:getDictLabel(reportSteels.standard, 'report_standard_steels', '')}
				</td>
				<td>
					${reportSteels.weight}
				</td>
				<td>
					${reportSteels.number}
				</td>
				<td>
					<span style="color: red;">${reportSteels.useredNumber}</span>
				</td>
				<td>
					<span style="color: red;">${reportSteels.unUseredNumber}</span>
				</td>								
				<td>
					${reportSteels.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${reportSteels.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${reportSteels.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${reportSteels.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${reportSteels.remarks}
				</td>
				<shiro:hasPermission name="steels:reportSteels:edit"><td>
    				<a href="${ctx}/steels/reportSteels/form?id=${reportSteels.id}">修改</a>
					<a href="${ctx}/steels/reportSteels/delete?id=${reportSteels.id}" onclick="return confirmx('确认要删除该钢材吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>