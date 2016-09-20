/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.finance.voucher.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.finance.voucher.dao.FinanceVoucherDao;
import com.thinkgem.jeesite.finance.voucher.entity.FinanceVoucher;

/**
 * 记账凭证管理Service
 * @author gaolei
 * @version 2016-09-19
 */
@Service
@Transactional(readOnly = true)
public class FinanceVoucherService extends CrudService<FinanceVoucherDao, FinanceVoucher> {

    public FinanceVoucher get(String id) {
        return super.get(id);
    }

    public List<FinanceVoucher> findList(FinanceVoucher financeVoucher) {
        return super.findList(financeVoucher);
    }

    public String selectCount(FinanceVoucher financeVoucher) {
        return dao.selectCount(financeVoucher);
    }

    public Page<FinanceVoucher> findPage(Page<FinanceVoucher> page, FinanceVoucher financeVoucher) {
        return super.findPage(page, financeVoucher);
    }

    @Transactional(readOnly = false)
    public void save(FinanceVoucher financeVoucher) {
        super.save(financeVoucher);
    }

    @Transactional(readOnly = false)
    public void delete(FinanceVoucher financeVoucher) {
        super.delete(financeVoucher);
    }

}