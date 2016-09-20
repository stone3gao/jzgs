<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
	$(function() {
		$('.carousel ul').carouFredSel({
			prev : '.prev',
			next : '.next',
			scroll : 1000,
			width : 980,
			height : 180
		});
	});
</script>

<div class="tail">
	<input type="hidden" id="cs" data-contact="${contacts}">
	<dl class="cs">
		<div id="contacts"></div>
	</dl>
	<script type="text/javascript">
		$('#contacts').html($('#cs').attr("data-contact"));
	</script>
	<dl class="link">
		<dt>友情链接</dt>

		<dd>
			<a href="http://www.scg.com.cn" target="_blank">上海建工集团</a>
		</dd>

		<dd>
			<a href="http://www.xfyouth.net" target="_blank">一建先锋青年网</a>
		</dd>

		<dd>
			<a href="http://www.mohurd.gov.cn/" target="_blank">中华人民共和国建设部</a>
		</dd>

		<dd>
			<a href="http://www.shgzw.gov.cn" target="_blank">上海国有资产监督管理</a>
		</dd>

		<dd>
			<a href="http://www.shjjw.gov.cn" target="_blank">上海城乡建设和管理</a>
		</dd>

		<dd>
			<a href="http://www.shsafety.gov.cn" target="_blank">上海安全生产监督局</a>
		</dd>

		<dd>
			<a href="http://www.shzj.gov.cn" target="_blank">上海市质量技术监督局</a>
		</dd>

		<dd>
			<a href="http://www.ciac.sh.cn/" target="_blank">上海建筑建材业</a>
		</dd>

		<dd>
			<a href="http://www.shanghai.gov.cn" target="_blank">中国上海</a>
		</dd>

		<dd>
			<a href="http://www.online.sh.cn" target="_blank">上海热线</a>
		</dd>

		<dd>
			<a href="http://www.eastday.com" target="_blank">东方网</a>
		</dd>

	</dl>
	<div class="inner" style="margin-top: 20px">
		<img src='/jeesite/shjp/upload/images/old/gsicon.gif' />
	</div>
	<div class="inner" style="margin-top: 25px">版权所有：上海建工一建集团有限公司
		沪ICP备12026382号</div>
</div>