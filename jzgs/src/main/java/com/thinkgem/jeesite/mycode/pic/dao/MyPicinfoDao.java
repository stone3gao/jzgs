/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.mycode.pic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.mycode.pic.entity.MyPicinfo;

/**
 * 图片管理器DAO接口
 * @author gaol
 * @version 2016-08-29
 */
@MyBatisDao
public interface MyPicinfoDao extends CrudDao<MyPicinfo> {
	
}