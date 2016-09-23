/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.reports.vend.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 供应商管理Entity
 * @author gaol
 * @version 2016-09-23
 */
public class ReportVend extends DataEntity<ReportVend> {

    private static final long serialVersionUID = 1L;
    private String code; // 编号
    private String name; // 名称
    private String address; // 地址
    private String phone; // 联系方式
    private String people; // 联系人
    private String attach; // 附件

    public ReportVend() {
        super();
    }

    public ReportVend(String id) {
        super(id);
    }

    @Length(min = 1, max = 64, message = "编号长度必须介于 1 和 64 之间")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Length(min = 1, max = 100, message = "名称长度必须介于 1 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 100, message = "地址长度必须介于 1 和 100 之间")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Length(min = 1, max = 100, message = "联系方式长度必须介于 1 和 100 之间")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Length(min = 0, max = 100, message = "联系人长度必须介于 0 和 100 之间")
    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    @Length(min = 0, max = 1000, message = "附件长度必须介于 0 和 1000 之间")
    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.name;
    }

}