/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.reports.vend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.reports.vend.entity.ReportVend;
import com.thinkgem.jeesite.reports.vend.dao.ReportVendDao;

/**
 * 供应商管理Service
 * @author gaol
 * @version 2016-09-23
 */
@Service
@Transactional(readOnly = true)
public class ReportVendService extends CrudService<ReportVendDao, ReportVend> {

	public ReportVend get(String id) {
		return super.get(id);
	}
	
	public List<ReportVend> findList(ReportVend reportVend) {
		return super.findList(reportVend);
	}
	
	public Page<ReportVend> findPage(Page<ReportVend> page, ReportVend reportVend) {
		return super.findPage(page, reportVend);
	}
	
	@Transactional(readOnly = false)
	public void save(ReportVend reportVend) {
		super.save(reportVend);
	}
	
	@Transactional(readOnly = false)
	public void delete(ReportVend reportVend) {
		super.delete(reportVend);
	}
	
}