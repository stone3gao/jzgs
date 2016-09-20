/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.finance.voucher.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.finance.caption.entity.FinanceAccountCaption;
import com.thinkgem.jeesite.reports.project.entity.ReportProject;

/**
 * 记账凭证管理Entity
 * @author gaolei
 * @version 2016-09-19
 */
public class FinanceVoucher extends DataEntity<FinanceVoucher> {

    private static final long serialVersionUID = 1L;
    private String name; // 名称
    private String amount; // 金额
    private Date voucherDate; // 时间
    private String people; // 人员
    private String purpose; // 用途描述
    private String status; // 状态
    private ReportProject project; // 归属项目
    private FinanceAccountCaption caption; // 会计科目ID
    private String attach; // 附件
    private Date beginVoucherDate; // 开始 时间
    private Date endVoucherDate; // 结束 时间

    public FinanceVoucher() {
        super();
    }

    public FinanceVoucher(String id) {
        super(id);
    }

    public FinanceAccountCaption getCaption() {
        return caption;
    }

    public void setCaption(FinanceAccountCaption caption) {
        this.caption = caption;
    }

    @JsonIgnore
    @ExcelField(title = "归属项目", align = 2, sort = 10)
    public ReportProject getProject() {
        return project;
    }

    public void setProject(ReportProject project) {
        this.project = project;
    }

    @Length(min = 1, max = 100, message = "名称长度必须介于 1 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "时间不能为空")
    public Date getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(Date voucherDate) {
        this.voucherDate = voucherDate;
    }

    @Length(min = 1, max = 100, message = "人员长度必须介于 1 和 100 之间")
    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    @Length(min = 1, max = 400, message = "用途描述长度必须介于 1 和 400 之间")
    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Length(min = 1, max = 1, message = "状态长度必须介于 1 和 1 之间")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Length(min = 0, max = 1000, message = "附件长度必须介于 0 和 1000 之间")
    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Date getBeginVoucherDate() {
        return beginVoucherDate;
    }

    public void setBeginVoucherDate(Date beginVoucherDate) {
        this.beginVoucherDate = beginVoucherDate;
    }

    public Date getEndVoucherDate() {
        return endVoucherDate;
    }

    public void setEndVoucherDate(Date endVoucherDate) {
        this.endVoucherDate = endVoucherDate;
    }

}