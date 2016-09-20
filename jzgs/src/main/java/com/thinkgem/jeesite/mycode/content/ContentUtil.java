/*******************************************************************************
 * Created on 2016年8月30日 上午11:37:50
 * Copyright (c) 深圳市小牛在线互联网信息咨询有限公司版权所有. 粤ICP备13089339号
 * 注意：本内容仅限于深圳市小牛在线互联网信息咨询有限公司内部传阅，禁止外泄以及用于其他商业目的!
 ******************************************************************************/
package com.thinkgem.jeesite.mycode.content;

import java.util.List;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.mycode.content.dao.MyContentDao;
import com.thinkgem.jeesite.mycode.content.entity.MyContent;

/**
 * 
 * @since 1.0.0
 * @version $Id$
 * @author {高磊} 2016年8月30日 上午11:37:50
 */
public class ContentUtil {
    private static MyContentDao contentDao = SpringContextHolder.getBean(MyContentDao.class);

    public static String getContent(String type) {
        MyContent entity = new MyContent();
        entity.setContentType(type);
        List<MyContent> list = contentDao.findList(entity);
        if (list.isEmpty()) {
            return "";
        }
        return list.get(0).getContent();
    }
}
