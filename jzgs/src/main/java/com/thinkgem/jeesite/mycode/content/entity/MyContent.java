/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.mycode.content.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 文档内容Entity
 * @author gaol
 * @version 2016-08-30
 */
public class MyContent extends DataEntity<MyContent> {

    private static final long serialVersionUID = 1L;
    private String contentType; // 内容类型
    private String content; // 文档内容
    private Date createTime; // 创建时间

    public MyContent() {
        super();
    }

    public MyContent(String id) {
        super(id);
    }

    @Length(min = 0, max = 20, message = "内容类型长度必须介于 0 和 20 之间")
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}