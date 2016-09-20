/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.mycode.content.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.mycode.content.entity.MyContent;

/**
 * 文档内容DAO接口
 * @author gaol
 * @version 2016-08-30
 */
@MyBatisDao
public interface MyContentDao extends CrudDao<MyContent> {
	
}