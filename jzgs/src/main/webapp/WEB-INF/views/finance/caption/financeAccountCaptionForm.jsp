<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会计科目管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					code: {remote: "${ctx}/caption/financeAccountCaption/checkCode?oldCode=" + encodeURIComponent('${financeAccountCaption.code}')}
				},
				messages: {
					code: {remote: "科目编码已存在"}
				},					
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/caption/financeAccountCaption/">会计科目列表</a></li>
		<li class="active"><a href="${ctx}/caption/financeAccountCaption/form?id=${financeAccountCaption.id}&parent.id=${financeAccountCaptionparent.id}">会计科目<shiro:hasPermission name="caption:financeAccountCaption:edit">${not empty financeAccountCaption.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="caption:financeAccountCaption:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="financeAccountCaption" action="${ctx}/caption/financeAccountCaption/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">上级父级编号:</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id" value="${financeAccountCaption.parent.id}" labelName="parent.name" labelValue="${financeAccountCaption.parent.name}"
					title="父级编号" url="/caption/financeAccountCaption/treeData" extId="${financeAccountCaption.id}" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会计科目编码：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<input id="oldCode" name="oldCode" type="hidden" value="${financeAccountCaption.code}">				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会计科目类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('finance_account_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借贷类型：</label>
			<div class="controls">
				<form:select path="dcType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('finance_dc_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<c:if test="${financeAccountCaption != null && not empty financeAccountCaption.createBy.name}">
			<div class="control-group">
				<label class="control-label">创建人:</label>
				<div class="controls">
					<label class="lbl">${financeAccountCaption.createBy.name}</label>
				</div>
			</div>			
			<div class="control-group">
				<label class="control-label">创建时间:</label>
				<div class="controls">
					<label class="lbl"><fmt:formatDate value="${financeAccountCaption.createDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
		</c:if>			
		<div class="form-actions">
			<shiro:hasPermission name="caption:financeAccountCaption:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>