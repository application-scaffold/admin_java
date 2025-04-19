package cn.liaozh.front_api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.entity.RechargeOrder;
import cn.liaozh.common.entity.user.User;
import cn.liaozh.common.enums.AccountLogEnum;
import cn.liaozh.common.enums.PaymentEnum;
import cn.liaozh.common.exception.OperateException;
import cn.liaozh.common.mapper.RechargeOrderMapper;
import cn.liaozh.common.mapper.log.UserAccountLogMapper;
import cn.liaozh.common.mapper.user.UserMapper;
import cn.liaozh.common.util.ConfigUtils;
import cn.liaozh.common.util.StringUtils;
import cn.liaozh.common.util.TimeUtils;
import cn.liaozh.front_api.service.IRechargeService;
import cn.liaozh.front_api.validate.RechargeValidate;
import cn.liaozh.front_api.validate.common.PageValidate;
import cn.liaozh.front_api.vo.RechargeConfigVo;
import cn.liaozh.front_api.vo.RechargeRecordVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 充值余额服务实现类
 */
@Service
public class RechargeServiceImpl implements IRechargeService {

    @Resource
    UserAccountLogMapper userAccountLogMapper;
    @Resource
    RechargeOrderMapper rechargeOrderMapper;

    @Resource
    UserMapper userMapper;

    /**
     * 充值配置
     *
     * @author fzr
     *  @param userId 用户ID
     * @return RechargeConfigVo
     */
    @Override
    public RechargeConfigVo config(Integer userId) {
        User user = userMapper.selectById(userId);
        Map<String, String> config = ConfigUtils.get("recharge");

        RechargeConfigVo vo = new RechargeConfigVo();
        vo.setStatus(Integer.parseInt(config.getOrDefault("status", "0")));
        vo.setMinAmount(new BigDecimal(config.getOrDefault("min_amount", "0")));
        vo.setUserMoney(user.getUserMoney());
        return vo;
    }

    /**
     * 充值记录
     *
     * @author fzr
     * @param userId 用户ID
     * @param pageValidate 分页参数
     * @return PageResult<RechargeRecordVo>
     */
    @Override
    public PageResult<RechargeRecordVo> record(Integer userId, PageValidate pageValidate) {
        Integer pageNo   = pageValidate.getPage_no();
        Integer pageSize = pageValidate.getPage_size();

        QueryWrapper<RechargeOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("pay_status", PaymentEnum.OK_PAID.getCode());
        queryWrapper.orderByDesc("id");

        IPage<RechargeOrder> iPage = rechargeOrderMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);

        List<RechargeRecordVo> list = new LinkedList<>();
        for (RechargeOrder rechargeOrder : iPage.getRecords()) {
            RechargeRecordVo vo = new RechargeRecordVo();
            vo.setId(rechargeOrder.getId());
            vo.setAction(1);
            vo.setOrderAmount(rechargeOrder.getOrderAmount());
            vo.setCreateTime(TimeUtils.timestampToDate(rechargeOrder.getPayTime()));
            vo.setTips("充值" + vo.getOrderAmount() + "元");
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 创建充值订单
     *
     * @author fzr
     * @param userId 用户ID
     * @param terminal 设备端
     * @param rechargeValidate 参数
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> placeOrder(Integer userId, Integer terminal, RechargeValidate rechargeValidate) {
        RechargeConfigVo config = this.config(userId);
        if (config.getStatus().equals(0)) {
            throw new OperateException("充值功能已关闭");
        }

        if (rechargeValidate.getMoney().compareTo(config.getMinAmount()) < 0) {
            throw new OperateException("充值金额不能少于" + config.getMinAmount());
        }

        RechargeOrder order = new RechargeOrder();
        order.setUserId(userId);
        order.setOrderTerminal(terminal);
        order.setSn(rechargeOrderMapper.randMakeOrderSn("sn"));
        order.setPayStatus(0);
        order.setRefundStatus(0);
        order.setOrderAmount(rechargeValidate.getMoney());
        order.setCreateTime(System.currentTimeMillis() / 1000);
        order.setUpdateTime(System.currentTimeMillis() / 1000);
        rechargeOrderMapper.insert(order);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("order_id", order.getId());
        response.put("from", "recharge");
        return response;
    }

    @Override
    @Transactional
    public void updatePayOrderStatusToPaid(String outTradeNo, String tradeNo) {
        RechargeOrder order = rechargeOrderMapper.selectOne(new QueryWrapper<RechargeOrder>().eq("sn", outTradeNo).isNull("delete_time"));
        if (StringUtils.isNull(order)) {
            return;
        }

        if (order.getPayStatus().equals(PaymentEnum.OK_PAID.getCode())) { //如果是已支付的状态，则不会再更新
            return;
        }
        Integer userId = order.getUserId();
        String orderSn = outTradeNo;

        User user = userMapper.selectById(order.getUserId());
        user.setUserMoney(user.getUserMoney().add(order.getOrderAmount()));
        user.setTotalRechargeAmount(user.getTotalRechargeAmount().add(order.getOrderAmount()));
        userMapper.updateById(user);

        //add account log
        userAccountLogMapper.add(userId, AccountLogEnum.UM_INC_RECHARGE.getCode(), order.getOrderAmount(), order.getId(), order.getSn(), "", "");

        order.setTransactionId(tradeNo);
        order.setPayStatus(PaymentEnum.OK_PAID.getCode());
        order.setPayTime(System.currentTimeMillis() / 1000);
        rechargeOrderMapper.updateById(order);
    }
}
