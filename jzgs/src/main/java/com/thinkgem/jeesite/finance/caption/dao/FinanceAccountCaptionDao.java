/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.finance.caption.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.finance.caption.entity.FinanceAccountCaption;

/**
 * 财务会计科目管理DAO接口
 * @author gaolei
 * @version 2016-09-19
 */
@MyBatisDao
public interface FinanceAccountCaptionDao extends TreeDao<FinanceAccountCaption> {

    /**
     * 
     * @param code
     * @return
     *
     * @author {高磊} 2016年9月20日 上午10:14:07
     */
    FinanceAccountCaption getCaptionByCode(String code);

}