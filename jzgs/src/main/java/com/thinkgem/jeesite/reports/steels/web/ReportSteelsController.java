/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.reports.steels.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.reports.project.entity.ReportProject;
import com.thinkgem.jeesite.reports.project.service.ReportProjectService;
import com.thinkgem.jeesite.reports.steels.entity.ReportSteels;
import com.thinkgem.jeesite.reports.steels.service.ReportSteelsService;
import com.thinkgem.jeesite.reports.util.ImportExecl;

/**
 * 报表钢铁Controller
 * @author gaolei
 * @version 2016-09-07
 */
@Controller
@RequestMapping(value = "${adminPath}/steels/reportSteels")
public class ReportSteelsController extends BaseController {

    @Autowired
    private ReportSteelsService reportSteelsService;

    @Autowired
    private ReportProjectService reportProjectService;

    @Resource
    ImportExecl execl;

    @ModelAttribute
    public ReportSteels get(@RequestParam(required = false) String id) {
        ReportSteels entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = reportSteelsService.get(id);
        }
        if (entity == null) {
            entity = new ReportSteels();
        }
        return entity;
    }

    @RequiresPermissions("steels:reportSteels:view")
    @RequestMapping(value = { "list", "" })
    public String list(ReportSteels reportSteels, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        Page<ReportSteels> page = reportSteelsService.findPage(new Page<ReportSteels>(request, response), reportSteels);
        model.addAttribute("page", page);
        List<ReportProject> pList = reportProjectService.findList(new ReportProject());
        model.addAttribute("projectList", pList);
        return "reports/steels/reportSteelsList";
    }

    @RequiresPermissions("steels:reportSteels:view")
    @RequestMapping(value = "form")
    public String form(ReportSteels reportSteels, Model model) {
        model.addAttribute("reportSteels", reportSteels);
        List<ReportProject> pList = reportProjectService.findList(new ReportProject());
        model.addAttribute("projectList", pList);
        return "reports/steels/reportSteelsForm";
    }

    /**
     * 导出数据
     * @param user
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("steels:reportSteels:view")
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(ReportSteels reportSteels, HttpServletRequest request, HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        try {
            String fileName = "钢材数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Page<ReportSteels> page = reportSteelsService.findPage(new Page<ReportSteels>(request, response),
                    reportSteels);
            new ExportExcel("钢材数据", ReportSteels.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/steels/reportSteels/?repage";
    }

    @RequiresPermissions("steels:reportSteels:edit")
    @RequestMapping(value = "import")
    public String importx(ReportSteels reportSteels, Model model) {
        model.addAttribute("reportSteels", reportSteels);
        return "reports/steels/reportSteelsImport";
    }

    @RequiresPermissions("steels:reportSteels:edit")
    @RequestMapping(value = "importExe")
    public String importExe(@RequestParam("steelFile") MultipartFile steelFile, Model model,
            RedirectAttributes redirectAttributes) {
        StringBuffer result = new StringBuffer();
        String msg = "";
        if (!steelFile.isEmpty()) {
            logger.info(steelFile.getName());
            try {
                List<List<String>> list = execl.read(steelFile.getInputStream(), false);
                if (list != null) {
                    ReportSteels info = null;
                    for (int i = 1; i < list.size(); i++) {
                        try {
                            System.err.print("第" + (i) + "行");
                            List<String> cellList = list.get(i);
                            if (StringUtils.isEmpty(cellList.get(0))) {
                                result.append(i + 1).append(",");
                                continue;
                            }
                            info = new ReportSteels();
                            info.setId(null);
                            ReportProject project = new ReportProject();
                            project.setPcode(cellList.get(0));
                            info.setProject(project);
                            info.setModel(cellList.get(1));
                            info.setStandard(cellList.get(2));
                            info.setType(cellList.get(3));
                            info.setNumber(Integer.parseInt(cellList.get(4)));
                            info.setUseredNumber(Integer.parseInt(cellList.get(4)));
                            info.setWeight(cellList.get(5));
                            info.setRemarks(cellList.get(6));
                            reportSteelsService.save(info);
                        } catch (Exception e) {
                            result.append(i + 1).append(",");
                            logger.error(e.getMessage(), e);
                        }
                        // }
                    }
                } else {
                    msg = "文件为空";
                }
            } catch (Exception e) {
                if (e.getMessage().contains("[M1.13]")) {
                    msg = "导入的文件格式错误:" + e.getMessage();
                } else {
                    msg = "解析文件内容异常:" + e.getMessage();
                }
                logger.error(e.getMessage(), e);
            }
        }

        if (StringUtils.isEmpty(msg)) {
            if (StringUtils.isEmpty(result.toString())) {
                msg = "导入成功！";
            } else {
                msg = "部分导入成功，未成功的有：" + result.toString();
            }
        }
        addMessage(redirectAttributes, msg);
        return "redirect:" + Global.getAdminPath() + "/steels/reportSteels/?repage";
    }

    @RequiresPermissions("steels:reportSteels:edit")
    @RequestMapping(value = "save")
    public String save(ReportSteels reportSteels, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, reportSteels)) {
            return form(reportSteels, model);
        }
        reportSteelsService.save(reportSteels);
        addMessage(redirectAttributes, "保存钢材成功");
        return "redirect:" + Global.getAdminPath() + "/steels/reportSteels/?repage";
    }

    @RequiresPermissions("steels:reportSteels:edit")
    @RequestMapping(value = "delete")
    public String delete(ReportSteels reportSteels, RedirectAttributes redirectAttributes) {
        reportSteelsService.delete(reportSteels);
        addMessage(redirectAttributes, "删除钢材成功");
        return "redirect:" + Global.getAdminPath() + "/steels/reportSteels/?repage";
    }

}