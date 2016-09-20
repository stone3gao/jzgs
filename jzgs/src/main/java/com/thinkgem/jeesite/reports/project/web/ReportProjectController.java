/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.reports.project.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.reports.project.entity.ReportProject;
import com.thinkgem.jeesite.reports.project.service.ReportProjectService;

/**
 * 项目工程报表Controller
 * @author gaolei
 * @version 2016-09-09
 */
@Controller
@RequestMapping(value = "${adminPath}/project/reportProject")
public class ReportProjectController extends BaseController {

    @Autowired
    private ReportProjectService reportProjectService;

    @ModelAttribute
    public ReportProject get(@RequestParam(required = false) String id) {
        ReportProject entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = reportProjectService.get(id);
        }
        if (entity == null) {
            entity = new ReportProject();
        }
        return entity;
    }

    @RequiresPermissions("project:reportProject:view")
    @RequestMapping(value = { "list", "" })
    public String list(ReportProject reportProject, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        Page<ReportProject> page = reportProjectService.findPage(new Page<ReportProject>(request, response),
                reportProject);
        model.addAttribute("page", page);
        return "reports/project/reportProjectList";
    }

    @RequiresPermissions("project:reportProject:view")
    @RequestMapping(value = "form")
    public String form(ReportProject reportProject, Model model) {
        model.addAttribute("reportProject", reportProject);
        return "reports/project/reportProjectForm";
    }

    @RequiresPermissions("project:reportProject:edit")
    @RequestMapping(value = "save")
    public String save(ReportProject reportProject, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, reportProject)) {
            return form(reportProject, model);
        }
        ReportProject project = new ReportProject();
        project.setPcode(reportProject.getPcode());
        if (reportProjectService.get(project) != null) {
            addMessage(redirectAttributes, "项目编码已存在，请重新输入");
            return "reports/project/reportProjectForm";
        }
        reportProjectService.save(reportProject);
        addMessage(redirectAttributes, "保存项目工程成功");
        return "redirect:" + Global.getAdminPath() + "/project/reportProject/?repage";
    }

    /**
     * 验证PCode是否存在
     * @param oldPcode
     * @param pcode
     * @return
     */
    @ResponseBody
    @RequiresPermissions("project:reportProject:edit")
    @RequestMapping(value = "checkPcode")
    public String checkPcode(String oldPcode, String pcode) {
        if (pcode != null && pcode.equals(oldPcode)) {
            return "true";
        } else if (pcode != null && reportProjectService.getProjectByPcode(pcode) == null) {
            return "true";
        }
        return "false";
    }

    @RequiresPermissions("project:reportProject:edit")
    @RequestMapping(value = "delete")
    public String delete(ReportProject reportProject, RedirectAttributes redirectAttributes) {
        reportProjectService.delete(reportProject);
        addMessage(redirectAttributes, "删除项目工程成功");
        return "redirect:" + Global.getAdminPath() + "/project/reportProject/?repage";
    }

}