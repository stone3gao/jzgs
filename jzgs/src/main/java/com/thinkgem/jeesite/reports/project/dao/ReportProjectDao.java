/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.reports.project.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.reports.project.entity.ReportProject;

/**
 * 项目工程报表DAO接口
 * @author gaolei
 * @version 2016-09-09
 */
@MyBatisDao
public interface ReportProjectDao extends CrudDao<ReportProject> {

    /**
     * 
     * @param pcode
     * @return
     *
     * @author {高磊} 2016年9月18日 下午2:45:18
     */
    ReportProject getProjectByPcode(String pcode);

}