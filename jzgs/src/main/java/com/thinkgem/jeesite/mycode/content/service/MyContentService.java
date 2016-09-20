/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.mycode.content.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.mycode.content.entity.MyContent;
import com.thinkgem.jeesite.mycode.content.dao.MyContentDao;

/**
 * 文档内容Service
 * @author gaol
 * @version 2016-08-30
 */
@Service
@Transactional(readOnly = true)
public class MyContentService extends CrudService<MyContentDao, MyContent> {

	public MyContent get(String id) {
		return super.get(id);
	}
	
	public List<MyContent> findList(MyContent myContent) {
		return super.findList(myContent);
	}
	
	public Page<MyContent> findPage(Page<MyContent> page, MyContent myContent) {
		return super.findPage(page, myContent);
	}
	
	@Transactional(readOnly = false)
	public void save(MyContent myContent) {
		super.save(myContent);
	}
	
	@Transactional(readOnly = false)
	public void delete(MyContent myContent) {
		super.delete(myContent);
	}
	
}