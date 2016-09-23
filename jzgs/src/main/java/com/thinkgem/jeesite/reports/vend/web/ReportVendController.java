/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.reports.vend.web;

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
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.reports.vend.entity.ReportVend;
import com.thinkgem.jeesite.reports.vend.service.ReportVendService;

/**
 * 供应商管理Controller
 * @author gaol
 * @version 2016-09-23
 */
@Controller
@RequestMapping(value = "${adminPath}/vend/reportVend")
public class ReportVendController extends BaseController {

    @Autowired
    private ReportVendService reportVendService;

    @ModelAttribute
    public ReportVend get(@RequestParam(required = false) String id) {
        ReportVend entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = reportVendService.get(id);
        }
        if (entity == null) {
            entity = new ReportVend();
        }
        return entity;
    }

    @RequiresPermissions("vend:reportVend:view")
    @RequestMapping(value = { "list", "" })
    public String list(ReportVend reportVend, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<ReportVend> page = reportVendService.findPage(new Page<ReportVend>(request, response), reportVend);
        model.addAttribute("page", page);
        return "reports/vend/reportVendList";
    }

    @RequiresPermissions("vend:reportVend:view")
    @RequestMapping(value = "form")
    public String form(ReportVend reportVend, Model model) {
        model.addAttribute("reportVend", reportVend);
        return "reports/vend/reportVendForm";
    }

    @RequiresPermissions("vend:reportVend:edit")
    @RequestMapping(value = "save")
    public String save(ReportVend reportVend, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, reportVend)) {
            return form(reportVend, model);
        }
        if (StringUtils.isEmpty(reportVend.getCode())) {
            reportVend.setCode(IdGen.randomBase62(6));
        }
        reportVendService.save(reportVend);
        addMessage(redirectAttributes, "保存供应商成功");
        return "redirect:" + Global.getAdminPath() + "/vend/reportVend/?repage";
    }

    @RequiresPermissions("vend:reportVend:edit")
    @RequestMapping(value = "delete")
    public String delete(ReportVend reportVend, RedirectAttributes redirectAttributes) {
        reportVendService.delete(reportVend);
        addMessage(redirectAttributes, "删除供应商成功");
        return "redirect:" + Global.getAdminPath() + "/vend/reportVend/?repage";
    }

}