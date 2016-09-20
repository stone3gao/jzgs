/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.finance.voucher.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.finance.voucher.entity.FinanceVoucher;

/**
 * 记账凭证管理DAO接口
 * @author gaolei
 * @version 2016-09-19
 */
@MyBatisDao
public interface FinanceVoucherDao extends CrudDao<FinanceVoucher> {

    public String selectCount(FinanceVoucher financeVoucher);
}