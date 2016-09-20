<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图片管理</title>
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
		<li><a href="${ctx}/pic/myPicinfo/">图片列表</a></li>
		<li class="active"><a href="${ctx}/pic/myPicinfo/form?id=${myPicinfo.id}">图片<shiro:hasPermission name="pic:myPicinfo:edit">${not empty myPicinfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pic:myPicinfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="myPicinfo" action="${ctx}/pic/myPicinfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">actiontype：</label>
			<div class="controls">
				<form:select path="actionType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pictype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">picurl：</label>
			<div class="controls">
				<form:hidden id="picUrl" path="picUrl" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="picUrl" type="files" uploadPath="/pic/myPicinfo" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">status：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('picstatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ramark：</label>
			<div class="controls">
				<form:input path="ramark" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">createtime：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${myPicinfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pic:myPicinfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>