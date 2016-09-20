/*******************************************************************************
 * Created on 2016年8月26日 下午5:35:05
 * Copyright (c) 深圳市小牛在线互联网信息咨询有限公司版权所有. 粤ICP备13089339号
 * 注意：本内容仅限于深圳市小牛在线互联网信息咨询有限公司内部传阅，禁止外泄以及用于其他商业目的!
 ******************************************************************************/
package com.thinkgem.jeesite.site.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thinkgem.jeesite.common.security.shiro.session.SessionDAO;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.mycode.content.ContentUtil;

/**
 * 
 * @since 1.0.0
 * @version $Id$
 * @author {高磊} 2016年8月26日 下午5:35:05
 */
@Controller
public class IndexController extends BaseController {
    @Autowired
    private SessionDAO sessionDAO;

    /**
     * 管理登录
     */
    @RequestMapping(value = "${frontPath}/index", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("contacts", ContentUtil.getContent("contacts"));
        return "site/index";
    }
}
