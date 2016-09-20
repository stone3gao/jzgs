/*******************************************************************************
 * Created on 2016年9月6日 上午9:27:39
 * Copyright (c) 深圳市小牛在线互联网信息咨询有限公司版权所有. 粤ICP备13089339号
 * 注意：本内容仅限于深圳市小牛在线互联网信息咨询有限公司内部传阅，禁止外泄以及用于其他商业目的!
 ******************************************************************************/
package com.thinkgem.jeesite.modules.oa.web;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.Leave;

/**
 * 
 * @since 1.0.0
 * @version $Id$
 * @author {高磊} 2016年9月6日 上午9:27:39
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/bx")
public class BxController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;

    // @RequiresPermissions("oa:bx:view")
    @RequestMapping(value = { "form" })
    public String form(Leave leave, Model model) {
        model.addAttribute("leave", leave);
        return "modules/oa/bxForm";
    }

}
