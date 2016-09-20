/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.mycode.pic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.mycode.pic.entity.MyPicinfo;
import com.thinkgem.jeesite.mycode.pic.service.MyPicinfoService;

/**
 * 图片管理器Controller
 * @author gaol
 * @version 2016-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/pic/myPicinfo")
public class MyPicinfoController extends BaseController {

	@Autowired
	private MyPicinfoService myPicinfoService;
	
	@ModelAttribute
	public MyPicinfo get(@RequestParam(required=false) String id) {
		MyPicinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = myPicinfoService.get(id);
		}
		if (entity == null){
			entity = new MyPicinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("pic:myPicinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MyPicinfo myPicinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MyPicinfo> page = myPicinfoService.findPage(new Page<MyPicinfo>(request, response), myPicinfo); 
		model.addAttribute("page", page);
		return "mycode/pic/myPicinfoList";
	}

	@RequiresPermissions("pic:myPicinfo:view")
	@RequestMapping(value = "form")
	public String form(MyPicinfo myPicinfo, Model model) {
		model.addAttribute("myPicinfo", myPicinfo);
		return "mycode/pic/myPicinfoForm";
	}

	@RequiresPermissions("pic:myPicinfo:edit")
	@RequestMapping(value = "save")
	public String save(MyPicinfo myPicinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, myPicinfo)){
			return form(myPicinfo, model);
		}
		myPicinfoService.save(myPicinfo);
		addMessage(redirectAttributes, "保存图片成功");
		return "redirect:"+Global.getAdminPath()+"/pic/myPicinfo/?repage";
	}
	
	@RequiresPermissions("pic:myPicinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(MyPicinfo myPicinfo, RedirectAttributes redirectAttributes) {
		myPicinfoService.delete(myPicinfo);
		addMessage(redirectAttributes, "删除图片成功");
		return "redirect:"+Global.getAdminPath()+"/pic/myPicinfo/?repage";
	}

}