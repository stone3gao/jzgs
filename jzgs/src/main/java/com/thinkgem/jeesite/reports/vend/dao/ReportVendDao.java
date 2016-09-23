/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.reports.vend.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.reports.vend.entity.ReportVend;

/**
 * 供应商管理DAO接口
 * @author gaol
 * @version 2016-09-23
 */
@MyBatisDao
public interface ReportVendDao extends CrudDao<ReportVend> {
	
}