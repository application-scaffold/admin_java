package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.finance.FinanceWalletSearchValidate;
import cn.liaozh.admin_api.vo.finance.FinanceWalletListVo;
import cn.liaozh.common.core.PageResult;

import java.util.Map;

/**
 * 用户余额记录服务接口类
 */
public interface IFinanceWalletService {

    /**
     * 余额明细列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<FinanceWalletListVo>
     */
    PageResult<FinanceWalletListVo> list(PageValidate pageValidate, FinanceWalletSearchValidate searchValidate);

    Map<Integer, String> getUmChangeType();
}
