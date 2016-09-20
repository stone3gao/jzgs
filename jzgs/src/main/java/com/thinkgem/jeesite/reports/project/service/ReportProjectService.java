/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.reports.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.reports.project.dao.ReportProjectDao;
import com.thinkgem.jeesite.reports.project.entity.ReportProject;

/**
 * 项目工程报表Service
 * @author gaolei
 * @version 2016-09-09
 */
@Service
@Transactional(readOnly = true)
public class ReportProjectService extends CrudService<ReportProjectDao, ReportProject> {

    public ReportProject get(String id) {
        return super.get(id);
    }

    public List<ReportProject> findList(ReportProject reportProject) {
        return super.findList(reportProject);
    }

    public Page<ReportProject> findPage(Page<ReportProject> page, ReportProject reportProject) {
        return super.findPage(page, reportProject);
    }

    @Transactional(readOnly = false)
    public void save(ReportProject reportProject) {
        super.save(reportProject);
    }

    @Transactional(readOnly = false)
    public void delete(ReportProject reportProject) {
        super.delete(reportProject);
    }

    /**
     * 
     * @param pcode
     * @return
     *
     * @author {高磊} 2016年9月18日 下午2:44:08
     */
    public Object getProjectByPcode(String pcode) {
        return dao.getProjectByPcode(pcode);
    }

}