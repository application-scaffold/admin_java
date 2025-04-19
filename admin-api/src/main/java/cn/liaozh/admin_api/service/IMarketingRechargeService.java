package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.marketing.MarketingRechargeValidate;
import cn.liaozh.admin_api.vo.marketing.MarketingRechargeVo;

/**
 * 营销充值服务接口类
 */
public interface IMarketingRechargeService {

    /**
     * 充值配置详情
     *
     * @author fzr
     * @return MarketingRechargeVo
     */
    MarketingRechargeVo detail();

    /**
     * 充值配置保存
     *
     * @author fzr
     * @param rechargeValidate 充值参数
     */
    void save(MarketingRechargeValidate rechargeValidate);

}
