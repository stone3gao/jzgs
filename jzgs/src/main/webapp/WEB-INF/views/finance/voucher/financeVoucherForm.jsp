<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>记账凭证管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
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
		<li><a href="${ctx}/voucher/financeVoucher/">记账凭证列表</a></li>
		<li class="active"><a href="${ctx}/voucher/financeVoucher/form?id=${financeVoucher.id}">记账凭证<shiro:hasPermission name="voucher:financeVoucher:edit">${not empty financeVoucher.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="voucher:financeVoucher:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="financeVoucher" action="${ctx}/voucher/financeVoucher/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人员：</label>
			<div class="controls">
				<form:input path="people" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额(单位元)：</label>
			<div class="controls">
				<form:input path="amount" htmlEscape="false" class="input-xlarge number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">用途描述：</label>
			<div class="controls">
				<form:textarea path="purpose" htmlEscape="false" rows="4" maxlength="400" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时间：</label>
			<div class="controls">
				<input name="voucherDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${financeVoucher.voucherDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status" class="input-medium required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('finance_voucher_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属项目：</label>
			<div class="controls">
				<form:select path="project.pcode" class="input-medium ">
					<form:option value="" label=""/>
					<form:options items="${projectList}" itemLabel="pname" itemValue="pcode" htmlEscape="false"/>
				</form:select>	
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会计科目：</label>
			<div class="controls">
				<sys:treeselect2 id="caption" name="caption.code" value="${financeVoucher.caption.code}" notAllowSelectRoot="true" labelName="caption.name" labelValue="${financeVoucher.caption.name}"
					title="会计科目" url="/caption/financeAccountCaption/treeData" cssClass="input-medium required"/>		
				<span class="help-inline"><font color="red">*</font> </span>							
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:hidden id="attach" path="attach" htmlEscape="false" maxlength="1000" class="input-xlarge"/>
				<sys:ckfinder input="attach" type="files" uploadPath="/voucher/financeVoucher" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="voucher:financeVoucher:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>