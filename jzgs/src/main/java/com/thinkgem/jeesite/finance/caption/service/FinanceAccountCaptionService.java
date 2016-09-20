/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.finance.caption.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.finance.caption.dao.FinanceAccountCaptionDao;
import com.thinkgem.jeesite.finance.caption.entity.FinanceAccountCaption;

/**
 * 财务会计科目管理Service
 * @author gaolei
 * @version 2016-09-19
 */
@Service
@Transactional(readOnly = true)
public class FinanceAccountCaptionService extends TreeService<FinanceAccountCaptionDao, FinanceAccountCaption> {

    public FinanceAccountCaption get(String id) {
        return super.get(id);
    }

    public List<FinanceAccountCaption> findList(FinanceAccountCaption financeAccountCaption) {
        if (StringUtils.isNotBlank(financeAccountCaption.getParentIds())) {
            financeAccountCaption.setParentIds("," + financeAccountCaption.getParentIds() + ",");
        }
        return super.findList(financeAccountCaption);
    }

    @Transactional(readOnly = false)
    public void save(FinanceAccountCaption financeAccountCaption) {
        super.save(financeAccountCaption);
    }

    @Transactional(readOnly = false)
    public void delete(FinanceAccountCaption financeAccountCaption) {
        super.delete(financeAccountCaption);
    }

    /**
     * 
     * @param code
     * @return
     *
     * @author {高磊} 2016年9月20日 上午10:13:40
     */
    public Object getCaptionByCode(String code) {
        return dao.getCaptionByCode(code);
    }

}