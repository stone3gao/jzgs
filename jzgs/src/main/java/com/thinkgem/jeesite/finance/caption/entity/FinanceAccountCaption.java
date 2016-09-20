/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.finance.caption.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 财务会计科目管理Entity
 * @author gaolei
 * @version 2016-09-19
 */
public class FinanceAccountCaption extends TreeEntity<FinanceAccountCaption> {

    private static final long serialVersionUID = 1L;
    private FinanceAccountCaption parent; // 父级编号
    private String parentIds; // 所有父级编号
    private String name; // 名称
    private Integer sort; // 排序
    private String code; // 会计科目编码
    private String type; // 会计科目类型
    private String dcType; // 借贷类型

    public FinanceAccountCaption() {
        super();
    }

    public FinanceAccountCaption(String id) {
        super(id);
    }

    @JsonBackReference
    @NotNull(message = "父级编号不能为空")
    public FinanceAccountCaption getParent() {
        return parent;
    }

    public void setParent(FinanceAccountCaption parent) {
        this.parent = parent;
    }

    @Length(min = 1, max = 2000, message = "所有父级编号长度必须介于 1 和 2000 之间")
    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    @Length(min = 1, max = 100, message = "名称长度必须介于 1 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "排序不能为空")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Length(min = 1, max = 100, message = "会计科目编码长度必须介于 1 和 100 之间")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Length(min = 1, max = 1, message = "会计科目类型长度必须介于 1 和 1 之间")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Length(min = 1, max = 1, message = "借贷类型长度必须介于 1 和 1 之间")
    public String getDcType() {
        return dcType;
    }

    public void setDcType(String dcType) {
        this.dcType = dcType;
    }

    public String getParentId() {
        return parent != null && parent.getId() != null ? parent.getId() : "0";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name;
    }
}