/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.mycode.pic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.mycode.pic.entity.MyPicinfo;
import com.thinkgem.jeesite.mycode.pic.dao.MyPicinfoDao;

/**
 * 图片管理器Service
 * @author gaol
 * @version 2016-08-29
 */
@Service
@Transactional(readOnly = true)
public class MyPicinfoService extends CrudService<MyPicinfoDao, MyPicinfo> {

	public MyPicinfo get(String id) {
		return super.get(id);
	}
	
	public List<MyPicinfo> findList(MyPicinfo myPicinfo) {
		return super.findList(myPicinfo);
	}
	
	public Page<MyPicinfo> findPage(Page<MyPicinfo> page, MyPicinfo myPicinfo) {
		return super.findPage(page, myPicinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(MyPicinfo myPicinfo) {
		super.save(myPicinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MyPicinfo myPicinfo) {
		super.delete(myPicinfo);
	}
	
}