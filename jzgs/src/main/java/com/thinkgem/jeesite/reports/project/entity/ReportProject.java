/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.reports.project.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 项目工程报表Entity
 * @author gaolei
 * @version 2016-09-09
 */
public class ReportProject extends DataEntity<ReportProject> {

    private static final long serialVersionUID = 1L;
    private String pcode; // 项目编码
    private String pname; // 工程名称
    private String paddr; // 工程地址
    private String cost; // 成本
    private String people; // 投入人数
    private String period; // 项目周期
    private String process; // 项目进度
    private String type; // 项目类型
    private String leadinguserid; // 项目负责人
    private String status; // 项目状态
    private String profit; // 项目盈利
    private String attach; // 项目附件
    private Date startdate; // 开始时间
    private Date enddate; // 结束时间

    public ReportProject() {
        super();
    }

    public ReportProject(String id) {
        super(id);
    }

    @Length(min = 1, max = 64, message = "项目编码长度必须介于 1 和 64 之间")
    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    @Length(min = 1, max = 30, message = "工程名称长度必须介于 1 和 30 之间")
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Length(min = 1, max = 100, message = "工程地址长度必须介于 1 和 100 之间")
    public String getPaddr() {
        return paddr;
    }

    public void setPaddr(String paddr) {
        this.paddr = paddr;
    }

    @Length(min = 0, max = 100, message = "成本长度必须介于 0 和 100 之间")
    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Length(min = 0, max = 100, message = "投入人数长度必须介于 0 和 100 之间")
    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    @Length(min = 0, max = 100, message = "项目周期长度必须介于 0 和 100 之间")
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Length(min = 0, max = 100, message = "项目进度长度必须介于 0 和 100 之间")
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Length(min = 0, max = 30, message = "项目类型长度必须介于 0 和 30 之间")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Length(min = 0, max = 64, message = "项目负责人长度必须介于 0 和 64 之间")
    public String getLeadinguserid() {
        return leadinguserid;
    }

    public void setLeadinguserid(String leadinguserid) {
        this.leadinguserid = leadinguserid;
    }

    @Length(min = 0, max = 100, message = "项目状态长度必须介于 0 和 100 之间")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Length(min = 0, max = 100, message = "项目盈利长度必须介于 0 和 100 之间")
    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    @Length(min = 0, max = 1000, message = "项目附件长度必须介于 0 和 400 之间")
    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "开始时间不能为空")
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return pname;
    }

}