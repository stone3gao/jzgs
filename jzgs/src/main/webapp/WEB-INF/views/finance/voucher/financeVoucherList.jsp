<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>记账凭证管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function() {
				top.$.jBox.confirm("确认要导出记账凭证数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action", "${ctx}/voucher/financeVoucher/export");
						$("#searchForm").submit();
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
		<li class="active"><a href="${ctx}/voucher/financeVoucher/">记账凭证列表</a></li>
		<shiro:hasPermission name="voucher:financeVoucher:edit"><li><a href="${ctx}/voucher/financeVoucher/form">记账凭证添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="financeVoucher" action="${ctx}/voucher/financeVoucher/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>时间：</label>
				<input name="beginVoucherDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeVoucher.beginVoucherDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endVoucherDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeVoucher.endVoucherDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>人员：</label>
				<form:input path="people" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('finance_voucher_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>归属项目：</label>
				<form:select path="project.pcode" class="input-medium ">
					<form:option value="" label=""/>
					<form:options items="${projectList}" itemLabel="pname" itemValue="pcode" htmlEscape="false"/>
				</form:select>					
			</li>
			<li><label>会计科目：</label>
				<sys:treeselect2 id="caption" name="caption.code" value="${financeVoucher.caption.code}" notAllowSelectRoot="false" labelName="caption.name" labelValue="${financeVoucher.caption.name}"
					title="会计科目" url="/caption/financeAccountCaption/treeData" cssClass="input-medium"/>	
			</li>
			
			<li><label>借总额：</label>
				<input name="AllDAmont" type="text" value="${dcount}" readonly="readonly" maxlength="20" class="input-medium "/>
			</li>	
			<li><label>贷总额：</label>
				<input name="AllCAmont" type="text" value="${ccount}" readonly="readonly" maxlength="20" class="input-medium "/>
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
				<th>名称</th>
				<th>金额</th>
				<th>时间</th>
				<th>人员</th>
				<th>用途描述</th>
				<th>状态</th>
				<th>归属项目</th>
				<th>会计科目</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="voucher:financeVoucher:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="financeVoucher">
			<tr>
				<td><a href="${ctx}/voucher/financeVoucher/form?id=${financeVoucher.id}">
					${financeVoucher.name}
				</a></td>
				<td>
					${financeVoucher.amount}(元)
				</td>
				<td>
					<fmt:formatDate value="${financeVoucher.voucherDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${financeVoucher.people}
				</td>
				<td>
					${financeVoucher.purpose}
				</td>
				<td>
					${fns:getDictLabel(financeVoucher.status, 'finance_voucher_status', '')}
				</td>
				<td>
					${financeVoucher.project.pname}
				</td>
				<td>
					${financeVoucher.caption.name}
				</td>
				<td>
					<fmt:formatDate value="${financeVoucher.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${financeVoucher.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${financeVoucher.remarks}
				</td>
				<shiro:hasPermission name="voucher:financeVoucher:edit"><td>
    				<a href="${ctx}/voucher/financeVoucher/form?id=${financeVoucher.id}">修改</a>
					<a href="${ctx}/voucher/financeVoucher/delete?id=${financeVoucher.id}" onclick="return confirmx('确认要删除该记账凭证吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>