<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>钢材管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		jQuery.validator.addMethod("checkValue", function(value,element) {
			var number = $("#number").val();
	      	return this.optional(element) || eval(value) <= eval(number);   
	    }, $.validator.format(" * 消耗数量必须小于总数量"));
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
					"useredNumber": {
	                    digits:true,
	                    checkValue:true
	                }
				},
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
		<li><a href="${ctx}/steels/reportSteels/">钢材列表</a></li>
		<li class="active"><a href="${ctx}/steels/reportSteels/form?id=${reportSteels.id}">钢材<shiro:hasPermission name="steels:reportSteels:edit">${not empty reportSteels.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="steels:reportSteels:edit">查看</shiro:lacksPermission></a></li>
		<shiro:hasPermission name="steels:reportSteels:edit"><li><a href="${ctx}/steels/reportSteels/import">钢材导入</a></li></shiro:hasPermission>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="reportSteels" action="${ctx}/steels/reportSteels/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">归属项目：</label>
			<div class="controls">
				<form:select path="project.pcode" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${projectList}" itemLabel="pname" itemValue="pcode" htmlEscape="false"/>
				</form:select>			
				<span class="help-inline"><font color="red">*</font> </span>	
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商：</label>
			<div class="controls">
				<form:select path="vend.code" class="input-xlarge">
					<form:option value="" label=""/>
					<form:options items="${vendList}" itemLabel="name" itemValue="code" htmlEscape="false"/>
				</form:select>			
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('report_type_steels')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">型号：</label>
			<div class="controls">
				<form:select path="model" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('report_model_steels')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格：</label>
			<div class="controls">
				<form:select path="standard" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('report_standard_steels')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总数量：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="10" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已消耗数量：</label>
			<div class="controls">
				<form:input path="useredNumber" htmlEscape="false" maxlength="10" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>				
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">总价格(单位元)：</label>
			<div class="controls">
				<form:input path="amount" htmlEscape="false" class="input-xlarge number"/>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">单据附件：</label>
			<div class="controls">
				<form:hidden id="nameAttach" path="attach" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameAttach" type="files" uploadPath="/attach" selectMultiple="true" maxWidth="100" maxHeight="100"/>				
			</div>
		</div>				
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<c:if test="${reportSteels != null && not empty reportSteels.createBy.name}">
			<div class="control-group">
				<label class="control-label">创建人:</label>
				<div class="controls">
					<label class="lbl">${reportSteels.createBy.name}</label>
				</div>
			</div>			
			<div class="control-group">
				<label class="control-label">创建时间:</label>
				<div class="controls">
					<label class="lbl"><fmt:formatDate value="${reportSteels.createDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
			<c:if test="${not empty reportSteels.updateBy.name}">
				<div class="control-group">
					<label class="control-label">更新人员:</label>
					<div class="controls">
						<label class="lbl"> ${reportSteels.updateBy.name}</label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">更新时间:</label>
					<div class="controls">
						<label class="lbl"><fmt:formatDate value="${reportSteels.updateDate}" type="both" dateStyle="full"/></label>
					</div>
				</div>				
			</c:if>	
		</c:if>			
		<div class="form-actions">
			<shiro:hasPermission name="steels:reportSteels:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>