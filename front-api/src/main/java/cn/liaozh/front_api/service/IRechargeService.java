package cn.liaozh.front_api.service;

import cn.liaozh.common.core.PageResult;
import cn.liaozh.front_api.validate.RechargeValidate;
import cn.liaozh.front_api.validate.common.PageValidate;
import cn.liaozh.front_api.vo.RechargeConfigVo;
import cn.liaozh.front_api.vo.RechargeRecordVo;

import java.util.Map;

/**
 * 充值余额接口服务类
 */
public interface IRechargeService {

    /**
     * 充值配置
     *
     * @author fzr
     * @param userId 用户ID
     * @return RechargeConfigVo
     */
    RechargeConfigVo config(Integer userId);

    /**
     * 充值记录
     *
     * @author fzr
     * @param userId 用户ID
     * @param pageValidate 分页参数
     * @return PageResult<RechargeRecordVo>
     */
    PageResult<RechargeRecordVo> record(Integer userId, PageValidate pageValidate);

    /**
     * 充值下单
     *
     * @param userId 用户ID
     * @param terminal 总端
     * @param rechargeValidate 充值参数
     * @return Map<String, Object>
     */
    Map<String, Object> placeOrder(Integer userId, Integer terminal, RechargeValidate rechargeValidate);

    /**
     * @notes 阿里支付后更新状态
     * @return array
     * @throws @\think\db\exception\DataNotFoundException
     * @throws @\think\db\exception\DbException
     * @throws @\think\db\exception\ModelNotFoundException
     * @author damonyuan
     */
    void updatePayOrderStatusToPaid(String outTradeNo, String tradeNo);
}
