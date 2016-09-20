/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.reports.steels.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.reports.steels.entity.ReportSteels;
import com.thinkgem.jeesite.reports.steels.dao.ReportSteelsDao;

/**
 * 报表钢铁Service
 * @author gaolei
 * @version 2016-09-07
 */
@Service
@Transactional(readOnly = true)
public class ReportSteelsService extends CrudService<ReportSteelsDao, ReportSteels> {

	public ReportSteels get(String id) {
		return super.get(id);
	}
	
	public List<ReportSteels> findList(ReportSteels reportSteels) {
		return super.findList(reportSteels);
	}
	
	public Page<ReportSteels> findPage(Page<ReportSteels> page, ReportSteels reportSteels) {
		return super.findPage(page, reportSteels);
	}
	
	@Transactional(readOnly = false)
	public void save(ReportSteels reportSteels) {
		super.save(reportSteels);
	}
	
	@Transactional(readOnly = false)
	public void delete(ReportSteels reportSteels) {
		super.delete(reportSteels);
	}
	
}