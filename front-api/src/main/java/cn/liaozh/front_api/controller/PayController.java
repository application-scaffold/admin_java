package cn.liaozh.front_api.controller;

import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import cn.liaozh.common.aop.NotLogin;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.entity.RechargeOrder;
import cn.liaozh.common.enums.ClientEnum;
import cn.liaozh.common.enums.PaymentEnum;
import cn.liaozh.common.exception.OperateException;
import cn.liaozh.common.mapper.RechargeOrderMapper;
import cn.liaozh.common.plugin.wechat.WxPayDriver;
import cn.liaozh.common.util.StringUtils;
import cn.liaozh.front_api.LikeFrontThreadLocal;
import cn.liaozh.front_api.service.IPayService;
import cn.liaozh.front_api.validate.PaymentValidate;
import cn.liaozh.front_api.vo.pay.PayStatusVo;
import cn.liaozh.front_api.vo.pay.PayWayListVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pay")
@Schema(name = "支付管理")
public class PayController {

    @Resource
    RechargeOrderMapper rechargeOrderMapper;

    @Resource
    IPayService iPayService;

    @GetMapping("/payWay")
    @Operation(summary = "支付方式")
    public AjaxResult<PayWayListVo> payWay(@Validated @NotNull(message = "from参数丢失") @RequestParam String from,
                                           @Validated @NotNull(message = "orderId参数丢失") @RequestParam Integer order_id) {
        Integer terminal = LikeFrontThreadLocal.getTerminal();

        PayWayListVo list = iPayService.payWay(from, order_id, terminal);
        return AjaxResult.success(list);
    }

    @GetMapping("/payStatus")
    @Operation(summary = "支付状态")
    public AjaxResult<PayStatusVo> payStatus(@Validated @NotNull(message = "from参数丢失") @RequestParam String from,
                                             @Validated @NotNull(message = "orderId参数丢失") @RequestParam Integer order_id) {
        PayStatusVo vo = iPayService.payStatus(from, order_id);
        return AjaxResult.success(vo);
    }

    @PostMapping("/prepay")
    @Operation(summary = "发起支付")
    public AjaxResult<Object> prepay(@Validated @RequestBody PaymentValidate requestObj) {
        // 接收参数
        if (StringUtils.isNull(requestObj.getScene())) {
            requestObj.setScene("recharge");
        }
        String scene     = requestObj.getScene();
        Integer payWay   = requestObj.getPayWay();
        Integer orderId  = requestObj.getOrderId();
        Integer terminal = LikeFrontThreadLocal.getTerminal();
        String code  = requestObj.getCode();
        requestObj.setTerminal(terminal);

        // 订单处理
        int payStatus = 0;
        switch (scene) {
            case "recharge":
                RechargeOrder rechargeOrder = rechargeOrderMapper.selectById(orderId);

                Assert.notNull(rechargeOrder, "订单不存在");
                Assert.isTrue(!payWay.equals(PaymentEnum.WALLET_PAY.getCode()), "支付类型不被支持");

                requestObj.setUserId(rechargeOrder.getUserId());
                requestObj.setOutTradeNo(rechargeOrder.getSn());
                requestObj.setOrderAmount(rechargeOrder.getOrderAmount());
                requestObj.setDescription("余额充值");
                requestObj.setAttach("recharge");
                payStatus = rechargeOrder.getPayStatus();

                rechargeOrder.setPayWay(payWay);
                rechargeOrderMapper.updateById(rechargeOrder);
                break;
            case "order":
                break;
        }

        // 订单校验
        if (payStatus != 0) {
            throw new OperateException("订单已支付");
        }
        // 发起支付
        Object result = iPayService.prepay(requestObj, terminal, code);
        return AjaxResult.success(result);
    }

    @NotLogin
    @PostMapping("/notifyMnp")
    @Operation(summary = "微信支付回调")
    public AjaxResult<Object> notifyMnp(@RequestBody String jsonData, HttpServletRequest request) throws WxPayException {
        // 构建签名
        SignatureHeader signatureHeader = new SignatureHeader();
        signatureHeader.setSignature(request.getHeader("wechatpay-signature"));
        signatureHeader.setNonce(request.getHeader("wechatpay-nonce"));
        signatureHeader.setSerial(request.getHeader("wechatpay-serial"));
        signatureHeader.setTimeStamp(request.getHeader("wechatpay-timestamp"));

        // 解密数据
        WxPayService wxPayService = WxPayDriver.handler(ClientEnum.MNP.getCode());

//        WxPayOrderNotifyV3Result.DecryptNotifyResult notifyResult = wxPayService.parseOrderNotifyV3Result(jsonData, signatureHeader).getResult();
        // 使用 WxPayNotifyV3Result 来解析通知结果
        WxPayNotifyV3Result.DecryptNotifyResult notifyResult = wxPayService.parseOrderNotifyV3Result(jsonData, signatureHeader).getResult();

        // 取出数据
        String transactionId = notifyResult.getTransactionId();
        String outTradeNo = notifyResult.getOutTradeNo();
        String attach =  notifyResult.getAttach();

        // 处理回调
        iPayService.handlePaidNotify(attach, outTradeNo, transactionId);
        return AjaxResult.success();
    }

}
