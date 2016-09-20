<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="/jeesite/shjp/SiteFiles/bairong/jquery/jquery-1.8.3.min.js"></script>
<title>上海建工一建集团有限公司-首页</title>
<link href="/jeesite/shjp/css/style.css" rel="stylesheet"
	type="text/css" />
<script src="/jeesite/shjp/js/jquery.carouFredSel-6.0.4-packed.js"
	type="text/javascript"></script>

<script src="/jeesite/shjp/SiteFiles/bairong/scripts/mm_menu.js"></script>
<script>
	function siteserverLoadMenus() {
		if (window.mm_menu_1)
			return;

		window.mm_menu_1 = new Menu('root', 115, 24, '', 12, '#000000',
				'#FFFFFF', '#F2F2F2', '#CCCCCC', 'center', 'middle', 0, 0, 500,
				-5, 7, true, true, true, 0, true, true);
		mm_menu_1.addMenuItem("企业简介", "location='/contents/83/1311.html'");
		mm_menu_1.addMenuItem("企业领导", "location='/contents/151/1900.html'");
		mm_menu_1.addMenuItem("组织架构", "location='/contents/150/1899.html'");
		mm_menu_1.addMenuItem("历史沿革", "location='/contents/149/1847.html'");
		mm_menu_1.addMenuItem("企业文化", "location='/contents/85/1420.html'");
		mm_menu_1.addMenuItem("企业资质", "location='/contents/131/1389.html'");
		mm_menu_1.fontWeight = 'plain';
		mm_menu_1.fontStyle = '';
		mm_menu_1.hideOnMouseOut = true;
		mm_menu_1.bgColor = '#A8A8A8';
		mm_menu_1.menuBorder = 1;
		mm_menu_1.menuLiteBgColor = '#A8A8A8';
		mm_menu_1.menuBorderBgColor = '';
		mm_menu_1.childMenuIcon = "/jeesite/shjp/sitefiles/bairong/Icons/arrows.gif";

		if (window.mm_menu_2)
			return;

		window.mm_menu_2 = new Menu('root', 115, 24, '', 12, '#000000',
				'#FFFFFF', '#F2F2F2', '#CCCCCC', 'center', 'middle', 0, 0, 500,
				-5, 7, true, true, true, 0, true, true);
		mm_menu_2.addMenuItem("代表工程", "location='/channels/20.html'");
		mm_menu_2.addMenuItem("获奖工程", "location='/channels/21.html'");
		mm_menu_2.fontWeight = 'plain';
		mm_menu_2.fontStyle = '';
		mm_menu_2.hideOnMouseOut = true;
		mm_menu_2.bgColor = '#A8A8A8';
		mm_menu_2.menuBorder = 1;
		mm_menu_2.menuLiteBgColor = '#A8A8A8';
		mm_menu_2.menuBorderBgColor = '';
		mm_menu_2.childMenuIcon = "/jeesite/shjp/sitefiles/bairong/Icons/arrows.gif";

		if (window.mm_menu_3)
			return;

		window.mm_menu_3 = new Menu('root', 115, 24, '', 12, '#000000',
				'#FFFFFF', '#F2F2F2', '#CCCCCC', 'center', 'middle', 0, 0, 500,
				-5, 7, true, true, true, 0, true, true);
		mm_menu_3.addMenuItem("装饰施工", "location='/contents/87/1328.html'");
		mm_menu_3.addMenuItem("安装施工", "location='/contents/88/1327.html'");
		mm_menu_3.fontWeight = 'plain';
		mm_menu_3.fontStyle = '';
		mm_menu_3.hideOnMouseOut = true;
		mm_menu_3.bgColor = '#A8A8A8';
		mm_menu_3.menuBorder = 1;
		mm_menu_3.menuLiteBgColor = '#A8A8A8';
		mm_menu_3.menuBorderBgColor = '';
		mm_menu_3.childMenuIcon = "/jeesite/shjp/sitefiles/bairong/Icons/arrows.gif";

		if (window.mm_menu_4)
			return;

		window.mm_menu_4 = new Menu('root', 115, 24, '', 12, '#000000',
				'#FFFFFF', '#F2F2F2', '#CCCCCC', 'center', 'middle', 0, 0, 500,
				-5, 7, true, true, true, 0, true, true);
		mm_menu_4.addMenuItem("校园招聘", "location='/channels/125.html'");
		mm_menu_4.addMenuItem("社会招聘", "location='/channels/124.html'");
		mm_menu_4.fontWeight = 'plain';
		mm_menu_4.fontStyle = '';
		mm_menu_4.hideOnMouseOut = true;
		mm_menu_4.bgColor = '#A8A8A8';
		mm_menu_4.menuBorder = 1;
		mm_menu_4.menuLiteBgColor = '#A8A8A8';
		mm_menu_4.menuBorderBgColor = '';
		mm_menu_4.childMenuIcon = "/jeesite/shjp/sitefiles/bairong/Icons/arrows.gif";
		mm_menu_1.writeMenus();
		mm_menu_2.writeMenus();
		mm_menu_3.writeMenus();
		mm_menu_4.writeMenus();
	}

	siteserverLoadMenus();
</script>
