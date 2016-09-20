/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.finance.voucher.web;

import java.util.List;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.finance.caption.entity.FinanceAccountCaption;
import com.thinkgem.jeesite.finance.voucher.entity.FinanceVoucher;
import com.thinkgem.jeesite.finance.voucher.service.FinanceVoucherService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.reports.project.entity.ReportProject;
import com.thinkgem.jeesite.reports.project.service.ReportProjectService;

/**
 * 记账凭证管理Controller
 * @author gaolei
 * @version 2016-09-19
 */
@Controller
@RequestMapping(value = "${adminPath}/voucher/financeVoucher")
public class FinanceVoucherController extends BaseController {

    @Autowired
    private FinanceVoucherService financeVoucherService;

    @Autowired
    private ReportProjectService reportProjectService;

    @ModelAttribute
    public FinanceVoucher get(@RequestParam(required = false) String id) {
        FinanceVoucher entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = financeVoucherService.get(id);
        }
        if (entity == null) {
            entity = new FinanceVoucher();
        }
        return entity;
    }

    @RequiresPermissions("voucher:financeVoucher:view")
    @RequestMapping(value = { "list", "" })
    public String list(FinanceVoucher financeVoucher, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        Page<FinanceVoucher> page = financeVoucherService.findPage(new Page<FinanceVoucher>(request, response),
                financeVoucher);
        model.addAttribute("page", page);
        List<ReportProject> pList = reportProjectService.findList(new ReportProject());
        model.addAttribute("projectList", pList);
        if (pList.isEmpty()) {
            return "finance/voucher/financeVoucherList";
        }
        FinanceAccountCaption caption = financeVoucher.getCaption();
        if (caption == null) {
            caption = new FinanceAccountCaption();
            caption.setDcType(DictUtils.getDictValue("借", "finance_dc_type", "D"));
            financeVoucher.setCaption(caption);
        } else {
            financeVoucher.getCaption().setDcType(DictUtils.getDictValue("借", "finance_dc_type", "D"));
        }
        financeVoucher.setPage(null);
        String dcount = financeVoucherService.selectCount(financeVoucher);
        model.addAttribute("dcount", dcount);
        financeVoucher.getCaption().setDcType(DictUtils.getDictValue("贷", "finance_dc_type", "C"));
        String ccount = financeVoucherService.selectCount(financeVoucher);
        model.addAttribute("ccount", ccount);

        return "finance/voucher/financeVoucherList";
    }

    @RequiresPermissions("voucher:financeVoucher:view")
    @RequestMapping(value = "form")
    public String form(FinanceVoucher financeVoucher, Model model) {
        model.addAttribute("financeVoucher", financeVoucher);
        List<ReportProject> pList = reportProjectService.findList(new ReportProject());
        model.addAttribute("projectList", pList);
        return "finance/voucher/financeVoucherForm";
    }

    @RequiresPermissions("voucher:financeVoucher:edit")
    @RequestMapping(value = "save")
    public String save(FinanceVoucher financeVoucher, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, financeVoucher)) {
            return form(financeVoucher, model);
        }
        financeVoucherService.save(financeVoucher);
        addMessage(redirectAttributes, "保存记账凭证成功");
        return "redirect:" + Global.getAdminPath() + "/voucher/financeVoucher/?repage";
    }

    @RequiresPermissions("voucher:financeVoucher:edit")
    @RequestMapping(value = "delete")
    public String delete(FinanceVoucher financeVoucher, RedirectAttributes redirectAttributes) {
        financeVoucherService.delete(financeVoucher);
        addMessage(redirectAttributes, "删除记账凭证成功");
        return "redirect:" + Global.getAdminPath() + "/voucher/financeVoucher/?repage";
    }

}