package cn.liaozh.front_api.service;

import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;
import com.github.binarywang.wxpay.exception.WxPayException;
import cn.liaozh.front_api.validate.PaymentValidate;
import cn.liaozh.front_api.vo.pay.PayStatusVo;
import cn.liaozh.front_api.vo.pay.PayWayListVo;


/**
 * 支付接口服务类
 */
public interface IPayService {

    /**
     * 支付方式
     *
     * @author fzr
     * @param from 场景
     * @param orderId 订单ID
     * @param terminal 终端
     * @return List<PayWayListedVo>
     */
    PayWayListVo payWay(String from, Integer orderId, Integer terminal);

    /**
     * 支付状态
     *
     * @author fzr
     * @param from 场景
     * @param orderId 订单ID
     * @return PayStatusVo
     */
    PayStatusVo payStatus(String from, Integer orderId);

    /**
     * 发起支付
     *
     * @param params 参数
     * @param terminal 终端
     * @return Object
     */
    Object prepay(PaymentValidate params, Integer terminal, String code);

    /**
     * 支付回调处理
     *
     * @param attach 场景码
     * @param outTradeNo 订单编号
     * @param transactionId 流水号
     */
    void handlePaidNotify(String attach, String outTradeNo, String transactionId) throws WxPayException;

    JSONObject createAliH5Order(PaymentValidate params) throws AlipayApiException;

}
