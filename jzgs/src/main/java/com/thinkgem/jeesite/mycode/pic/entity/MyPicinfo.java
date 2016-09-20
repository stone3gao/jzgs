/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.mycode.pic.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图片管理器Entity
 * @author gaol
 * @version 2016-08-29
 */
public class MyPicinfo extends DataEntity<MyPicinfo> {
	
	private static final long serialVersionUID = 1L;
	private String actionType;		// actiontype
	private String picUrl;		// picurl
	private String status;		// status
	private String ramark;		// ramark
	private Date createTime;		// createtime
	
	public MyPicinfo() {
		super();
	}

	public MyPicinfo(String id){
		super(id);
	}

	@Length(min=0, max=20, message="actiontype长度必须介于 0 和 20 之间")
	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	@Length(min=0, max=200, message="picurl长度必须介于 0 和 200 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@Length(min=0, max=1, message="status长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=100, message="ramark长度必须介于 0 和 100 之间")
	public String getRamark() {
		return ramark;
	}

	public void setRamark(String ramark) {
		this.ramark = ramark;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}