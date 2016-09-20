<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>钢材管理</title>
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
		<li><a href="${ctx}/steels/reportSteels/">钢材列表</a></li>
		<shiro:hasPermission name="steels:reportSteels:edit"><li><a href="${ctx}/steels/reportSteels/form">钢材添加</a></li></shiro:hasPermission>
		<shiro:hasPermission name="steels:reportSteels:edit"><li class="active"><a href="${ctx}/steels/reportSteels/import">钢材导入</a></li></shiro:hasPermission>
	</ul><br/>
	<form:form id="inputForm" enctype="multipart/form-data" action="${ctx}/steels/reportSteels/importExe" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label"></label>
			<div class="controls">
				<a class="input-xlarge" href="${ctxStatic}/reports/seets.xlsx">EXCEL模板下载</a>
			</div>
		</div>				
		<div class="control-group">
			<label class="control-label">EXCEL文件路径：</label>
			<div class="controls">
				<input type="file" name="steelFile" class="input-xlarge "/><br/>  
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="steels:reportSteels:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="提 交"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>