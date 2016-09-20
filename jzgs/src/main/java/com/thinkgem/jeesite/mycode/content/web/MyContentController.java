/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.mycode.content.web;

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
import com.thinkgem.jeesite.mycode.content.entity.MyContent;
import com.thinkgem.jeesite.mycode.content.service.MyContentService;

/**
 * 文档内容Controller
 * @author gaol
 * @version 2016-08-30
 */
@Controller
@RequestMapping(value = "${adminPath}/content/myContent")
public class MyContentController extends BaseController {

	@Autowired
	private MyContentService myContentService;
	
	@ModelAttribute
	public MyContent get(@RequestParam(required=false) String id) {
		MyContent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = myContentService.get(id);
		}
		if (entity == null){
			entity = new MyContent();
		}
		return entity;
	}
	
	@RequiresPermissions("content:myContent:view")
	@RequestMapping(value = {"list", ""})
	public String list(MyContent myContent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MyContent> page = myContentService.findPage(new Page<MyContent>(request, response), myContent); 
		model.addAttribute("page", page);
		return "mycode/content/myContentList";
	}

	@RequiresPermissions("content:myContent:view")
	@RequestMapping(value = "form")
	public String form(MyContent myContent, Model model) {
		model.addAttribute("myContent", myContent);
		return "mycode/content/myContentForm";
	}

	@RequiresPermissions("content:myContent:edit")
	@RequestMapping(value = "save")
	public String save(MyContent myContent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, myContent)){
			return form(myContent, model);
		}
		myContentService.save(myContent);
		addMessage(redirectAttributes, "保存内容成功");
		return "redirect:"+Global.getAdminPath()+"/content/myContent/?repage";
	}
	
	@RequiresPermissions("content:myContent:edit")
	@RequestMapping(value = "delete")
	public String delete(MyContent myContent, RedirectAttributes redirectAttributes) {
		myContentService.delete(myContent);
		addMessage(redirectAttributes, "删除内容成功");
		return "redirect:"+Global.getAdminPath()+"/content/myContent/?repage";
	}

}