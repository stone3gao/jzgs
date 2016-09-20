/*******************************************************************************
 * Created on 2016年8月30日 上午9:55:07
 * Copyright (c) 深圳市小牛在线互联网信息咨询有限公司版权所有. 粤ICP备13089339号
 * 注意：本内容仅限于深圳市小牛在线互联网信息咨询有限公司内部传阅，禁止外泄以及用于其他商业目的!
 ******************************************************************************/
package com.thinkgem.jeesite.mycode.pic;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.mycode.pic.dao.MyPicinfoDao;
import com.thinkgem.jeesite.mycode.pic.entity.MyPicinfo;

/**
 * 
 * @since 1.0.0
 * @version $Id$
 * @author {高磊} 2016年8月30日 上午9:55:07
 */
public class PicUtil {
    private static MyPicinfoDao picinfoDao = SpringContextHolder.getBean(MyPicinfoDao.class);

    public static List<MyPicinfo> getPics(String type) {
        MyPicinfo entity = new MyPicinfo();
        entity.setStatus("1");
        entity.setActionType(type);
        List<MyPicinfo> list = picinfoDao.findList(entity);
        for (MyPicinfo pic : list) {
            if (StringUtils.isNotBlank(pic.getPicUrl())) {
                pic.setPicUrl(pic.getPicUrl().replace("|", ""));
            }
        }
        return list;
    }

    public static String getPicsToString(String type) {
        MyPicinfo entity = new MyPicinfo();
        entity.setStatus("1");
        entity.setActionType(type);
        StringBuffer str = new StringBuffer(100);
        List<MyPicinfo> list = picinfoDao.findList(entity);
        for (MyPicinfo pic : list) {
            if (StringUtils.isNotBlank(pic.getPicUrl())) {
                str.append(pic.getPicUrl());
            }
        }
        return str.toString().substring(1);
    }

}
