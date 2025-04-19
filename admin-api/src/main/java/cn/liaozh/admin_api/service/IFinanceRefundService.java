package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.finance.FinanceRefundSearchValidate;
import cn.liaozh.admin_api.vo.finance.FinanceRefundListVo;
import cn.liaozh.admin_api.vo.finance.FinanceRefundLogVo;
import cn.liaozh.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 退款记录服务接口类
 */
public interface IFinanceRefundService {

    /**
     * 退款记录列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<FinanceRechargeListVo>
     */
    PageResult<FinanceRefundListVo> list(PageValidate pageValidate, FinanceRefundSearchValidate searchValidate);

    /**
     * 退款日志
     *
     * @param recordId 退款日志ID
     * @return  List<FinanceRefundLogVo>
     */
    List<FinanceRefundLogVo> log(Integer recordId);

    Map<String, Object> stat();
}
