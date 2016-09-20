/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.reports.steels.entity;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.reports.project.entity.ReportProject;

/**
 * 报表钢铁Entity
 * @author gaolei
 * @version 2016-09-07
 */
public class ReportSteels extends DataEntity<ReportSteels> {

    private static final long serialVersionUID = 1L;
    private ReportProject project; // 归属项目
    private String type; // 类型
    private String model; // 型号
    private String standard; // 规格
    private String weight; // 理论重量
    private Integer number; // 数量
    private Integer useredNumber; // 已使用数量
    private Integer unUseredNumber; // 剩余数量
    private String attach;

    public ReportSteels() {
        super();
    }

    public ReportSteels(String id) {
        super(id);
    }

    @JsonIgnore
    @NotNull(message = "归属项目不能为空")
    @ExcelField(title = "归属项目", align = 2, sort = 10)
    public ReportProject getProject() {
        return project;
    }

    @Length(min = 0, max = 1000, message = "单据附件长度必须介于 0 和 400 之间")
    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public void setProject(ReportProject project) {
        this.project = project;
    }

    @Length(min = 0, max = 10, message = "类型长度必须介于 0 和 10 之间")
    @ExcelField(title = "类型", align = 2, sort = 20, dictType = "report_type_steels")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Length(min = 0, max = 10, message = "型号长度必须介于 0 和 10 之间")
    @ExcelField(title = "型号", align = 2, sort = 30, dictType = "report_model_steels")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Length(min = 0, max = 10, message = "规格长度必须介于 0 和 10 之间")
    @ExcelField(title = "规格", align = 2, sort = 40, dictType = "report_standard_steels")
    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Length(min = 0, max = 10, message = "理论重量长度必须介于 0 和 10 之间")
    @ExcelField(title = "理论重量", align = 2, sort = 50)
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Min(value = 0, message = "数量的最小值为0")
    @ExcelField(title = "数量", align = 2, sort = 60)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Min(value = 0, message = "已使用数量的最小值为0")
    @ExcelField(title = "已使用数量", align = 2, sort = 70)
    public Integer getUseredNumber() {
        return useredNumber;
    }

    public void setUseredNumber(Integer useredNumber) {
        this.useredNumber = useredNumber;
    }

    @ExcelField(title = "剩余数量", align = 2, sort = 80)
    public Integer getUnUseredNumber() {
        unUseredNumber = number - useredNumber;
        return unUseredNumber;
    }

    public void setUnUseredNumber(Integer unUseredNumber) {
        this.unUseredNumber = number - useredNumber;
    }

    @ExcelField(title = "创建时间", type = 0, align = 1, sort = 90)
    public Date getCreateDate() {
        return createDate;
    }

}