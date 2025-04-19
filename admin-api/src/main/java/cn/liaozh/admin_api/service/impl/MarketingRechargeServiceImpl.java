package cn.liaozh.admin_api.service.impl;

import cn.liaozh.admin_api.service.IMarketingRechargeService;
import cn.liaozh.admin_api.validate.marketing.MarketingRechargeValidate;
import cn.liaozh.admin_api.vo.marketing.MarketingRechargeVo;
import cn.liaozh.common.util.ConfigUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 营销充值服务实现类
 */
@Service
public class MarketingRechargeServiceImpl implements IMarketingRechargeService {

    /**
     * 充值配置详情
     *
     * @author fzr
     * @return MarketingRechargeVo
     */
    @Override
    public MarketingRechargeVo detail() {
        Map<String, String> config = ConfigUtils.get("recharge");

        MarketingRechargeVo vo = new MarketingRechargeVo();
        vo.setStatus(Integer.parseInt(config.getOrDefault("status", "0")));
        vo.setMinAmount(new BigDecimal(config.getOrDefault("min_amount", "0")));
        return vo;
    }

    /**
     * 充值配置保存
     *
     * @author fzr
     * @param rechargeValidate 充值参数
     */
    @Override
    public void save(MarketingRechargeValidate rechargeValidate) {
        ConfigUtils.set("recharge", "status", rechargeValidate.getStatus().toString());
        ConfigUtils.set("recharge", "min_amount", rechargeValidate.getMinAmount().toString());
    }

}
