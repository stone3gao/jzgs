/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.finance.caption.web;

import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.finance.caption.entity.FinanceAccountCaption;
import com.thinkgem.jeesite.finance.caption.service.FinanceAccountCaptionService;

/**
 * 财务会计科目管理Controller
 * @author gaolei
 * @version 2016-09-19
 */
@Controller
@RequestMapping(value = "${adminPath}/caption/financeAccountCaption")
public class FinanceAccountCaptionController extends BaseController {

    @Autowired
    private FinanceAccountCaptionService financeAccountCaptionService;

    @ModelAttribute
    public FinanceAccountCaption get(@RequestParam(required = false) String id) {
        FinanceAccountCaption entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = financeAccountCaptionService.get(id);
        }
        if (entity == null) {
            entity = new FinanceAccountCaption();
        }
        return entity;
    }

    /**
     * 验证Code是否存在
     * @param oldPcode
     * @param pcode
     * @return
     */
    @ResponseBody
    @RequiresPermissions("caption:financeAccountCaption:edit")
    @RequestMapping(value = "checkCode")
    public String checkPcode(String oldCode, String code) {
        if (code != null && code.equals(oldCode)) {
            return "true";
        } else if (code != null && financeAccountCaptionService.getCaptionByCode(code) == null) {
            return "true";
        }
        return "false";
    }

    @RequiresPermissions("caption:financeAccountCaption:view")
    @RequestMapping(value = { "list", "" })
    public String list(FinanceAccountCaption financeAccountCaption, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        List<FinanceAccountCaption> list = financeAccountCaptionService.findList(financeAccountCaption);
        model.addAttribute("list", list);
        return "finance/caption/financeAccountCaptionList";
    }

    @RequiresPermissions("caption:financeAccountCaption:view")
    @RequestMapping(value = "form")
    public String form(FinanceAccountCaption financeAccountCaption, Model model) {
        if (financeAccountCaption.getParent() != null
                && StringUtils.isNotBlank(financeAccountCaption.getParent().getId())) {
            financeAccountCaption
                    .setParent(financeAccountCaptionService.get(financeAccountCaption.getParent().getId()));
            // 获取排序号，最末节点排序号+30
            if (StringUtils.isBlank(financeAccountCaption.getId())) {
                FinanceAccountCaption financeAccountCaptionChild = new FinanceAccountCaption();
                financeAccountCaptionChild
                        .setParent(new FinanceAccountCaption(financeAccountCaption.getParent().getId()));
                List<FinanceAccountCaption> list = financeAccountCaptionService.findList(financeAccountCaption);
                if (list.size() > 0) {
                    financeAccountCaption.setSort(list.get(list.size() - 1).getSort());
                    if (financeAccountCaption.getSort() != null) {
                        financeAccountCaption.setSort(financeAccountCaption.getSort() + 30);
                    }
                }
            }
        }
        if (financeAccountCaption.getSort() == null) {
            financeAccountCaption.setSort(30);
        }
        model.addAttribute("financeAccountCaption", financeAccountCaption);
        return "finance/caption/financeAccountCaptionForm";
    }

    @RequiresPermissions("caption:financeAccountCaption:edit")
    @RequestMapping(value = "save")
    public String save(FinanceAccountCaption financeAccountCaption, Model model,
            RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, financeAccountCaption)) {
            return form(financeAccountCaption, model);
        }
        financeAccountCaption.setIsNewRecord(true);
        financeAccountCaption.setId(financeAccountCaption.getCode());
        financeAccountCaptionService.save(financeAccountCaption);
        addMessage(redirectAttributes, "保存会计科目成功");
        return "redirect:" + Global.getAdminPath() + "/caption/financeAccountCaption/?repage";
    }

    @RequiresPermissions("caption:financeAccountCaption:edit")
    @RequestMapping(value = "delete")
    public String delete(FinanceAccountCaption financeAccountCaption, RedirectAttributes redirectAttributes) {
        financeAccountCaptionService.delete(financeAccountCaption);
        addMessage(redirectAttributes, "删除会计科目成功");
        return "redirect:" + Global.getAdminPath() + "/caption/financeAccountCaption/?repage";
    }

    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
            HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<FinanceAccountCaption> list = financeAccountCaptionService.findList(new FinanceAccountCaption());
        for (int i = 0; i < list.size(); i++) {
            FinanceAccountCaption e = list.get(i);
            if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId())
                    && e.getParentIds().indexOf("," + extId + ",") == -1)) {
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getId());
                map.put("pId", e.getParentId());
                map.put("name", e.getName());
                map.put("code", e.getCode());
                mapList.add(map);
            }
        }
        return mapList;
    }

}