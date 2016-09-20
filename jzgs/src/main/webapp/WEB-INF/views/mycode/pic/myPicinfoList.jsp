<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图片管理</title>
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
		<li class="active"><a href="${ctx}/pic/myPicinfo/">图片列表</a></li>
		<shiro:hasPermission name="pic:myPicinfo:edit"><li><a href="${ctx}/pic/myPicinfo/form">图片添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="myPicinfo" action="${ctx}/pic/myPicinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>actiontype：</label>
				<form:select path="actionType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pictype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>status：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('picstatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>createtime：</label>
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${myPicinfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>actiontype</th>
				<th>picurl</th>
				<th>status</th>
				<th>ramark</th>
				<th>createtime</th>
				<shiro:hasPermission name="pic:myPicinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="myPicinfo">
			<tr>
				<td><a href="${ctx}/pic/myPicinfo/form?id=${myPicinfo.id}">
					${fns:getDictLabel(myPicinfo.actionType, 'pictype', '')}
				</a></td>
				<td>
					${myPicinfo.picUrl}
				</td>
				<td>
					${fns:getDictLabel(myPicinfo.status, 'picstatus', '')}
				</td>
				<td>
					${myPicinfo.ramark}
				</td>
				<td>
					<fmt:formatDate value="${myPicinfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pic:myPicinfo:edit"><td>
    				<a href="${ctx}/pic/myPicinfo/form?id=${myPicinfo.id}">修改</a>
					<a href="${ctx}/pic/myPicinfo/delete?id=${myPicinfo.id}" onclick="return confirmx('确认要删除该图片吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>