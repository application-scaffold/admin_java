package cn.liaozh.admin_api.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.binarywang.wxpay.bean.result.WxPayRefundV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.yulichang.query.MPJQueryWrapper;
import cn.liaozh.admin_api.service.IFinanceRechargerService;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.finance.FinanceRechargeSearchValidate;
import cn.liaozh.admin_api.vo.finance.FinanceRechargeListExportVo;
import cn.liaozh.admin_api.vo.finance.FinanceRechargeListVo;
import cn.liaozh.common.config.AlipayConfig;
import cn.liaozh.common.config.GlobalConfig;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.entity.RechargeOrder;
import cn.liaozh.common.entity.RefundLog;
import cn.liaozh.common.entity.RefundRecord;
import cn.liaozh.common.entity.user.User;
import cn.liaozh.common.enums.LogMoneyEnum;
import cn.liaozh.common.enums.PaymentEnum;
import cn.liaozh.common.enums.RefundEnum;
import cn.liaozh.common.exception.OperateException;
import cn.liaozh.common.mapper.RechargeOrderMapper;
import cn.liaozh.common.mapper.RefundLogMapper;
import cn.liaozh.common.mapper.RefundRecordMapper;
import cn.liaozh.common.mapper.log.UserAccountLogMapper;
import cn.liaozh.common.mapper.user.UserMapper;
import cn.liaozh.common.plugin.wechat.WxPayDriver;
import cn.liaozh.common.plugin.wechat.request.RefundRequestV3;
import cn.liaozh.common.util.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.Assert;

import jakarta.annotation.Resource;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 充值记录服务实现类
 */
@Service
public class FinanceRechargerServiceImpl implements IFinanceRechargerService {

    @Resource
    RechargeOrderMapper rechargeOrderMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    UserAccountLogMapper userAccountLogMapper;

    @Resource
    RefundRecordMapper refundRecordMapper;

    @Resource
    RefundLogMapper refundLogMapper;

    @Resource
    DataSourceTransactionManager transactionManager ;

    @Resource
    TransactionDefinition transactionDefinition;

    /**
     * 充值记录
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<FinanceRechargeListVo>
     */
    @Override
    public PageResult<FinanceRechargeListVo> list(PageValidate pageValidate, FinanceRechargeSearchValidate searchValidate) {
        Integer pageNo = pageValidate.getPage_no();
        Integer pageSize = pageValidate.getPage_size();

        MPJQueryWrapper<RechargeOrder> mpjQueryWrapper = new MPJQueryWrapper<>();
        mpjQueryWrapper.selectAll(RechargeOrder.class)
                .select("U.id as user_id,U.account ,U.nickname,U.avatar, t.sn AS sn")
                .leftJoin("?_user U ON U.id=t.user_id".replace("?_", GlobalConfig.tablePrefix))
                .orderByDesc("id");

        rechargeOrderMapper.setSearch(mpjQueryWrapper, searchValidate, new String[]{
                "like:sn@t.sn:str",
                "=:pay_way@t.pay_way:int",
                "=:pay_status@t.pay_status:int",
                "datetime:start_time-end_time@t.create_time:str",
        });

        if (StringUtils.isNotEmpty(searchValidate.getUser_info())) {
            String keyword = searchValidate.getUser_info();
            mpjQueryWrapper.nested(wq->wq
                    .like("U.nickname", keyword).or()
                    .like("U.account", keyword).or()
                    .like("U.sn", keyword).or()
                    .like("U.mobile", keyword));
        }

        IPage<FinanceRechargeListVo> iPage = rechargeOrderMapper.selectJoinPage(
                new Page<>(pageNo, pageSize),
                FinanceRechargeListVo.class,
                mpjQueryWrapper);

        for (FinanceRechargeListVo vo : iPage.getRecords()) {
            vo.setCreateTime(TimeUtils.timestampToDate(vo.getCreateTime()));
            vo.setPayTime(StringUtils.isNull(vo.getPayTime()) ? "-" : TimeUtils.timestampToDate(vo.getPayTime()));
            vo.setAvatar(UrlUtils.toAdminAbsoluteUrl(vo.getAvatar()));
            vo.setPayWay(vo.getPayWay());
            vo.setPayWayText(PaymentEnum.getPayWayMsg(Integer.parseInt(vo.getPayWay())));
            vo.setPayStatusText(PaymentEnum.getPayStatusMsg(vo.getPayStatus()));
        }

        return PageResult.iPageHandle(iPage);
    }

    /**
     * 发起退款
     *
     * @author fzr
     * @param orderId 订单ID
     * @param adminId 管理员ID
     */
    @Override
    public void refund(Integer orderId, Integer adminId) {
        RechargeOrder rechargeOrder = rechargeOrderMapper.selectById(orderId);

        Assert.notNull(rechargeOrder, "充值订单不存在!");
        if (!rechargeOrder.getPayStatus().equals(PaymentEnum.OK_PAID.getCode())) {
            throw new OperateException("当前订单不可退款!");
        }

        if (rechargeOrder.getRefundStatus().equals(1)) {
            throw new OperateException("订单已发起退款,退款失败请到退款记录重新退款!");
        }

        User user = userMapper.selectById(rechargeOrder.getUserId());
        if (user.getUserMoney().compareTo(rechargeOrder.getOrderAmount()) < 0) {
            throw new OperateException("退款失败:用户余额已不足退款金额!");
        }

        // 开启事务
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        RefundRecord refundRecord = null;
        RefundLog log = null;
        try {
            // 标记退款状态
            rechargeOrder.setRefundStatus(1);
            rechargeOrderMapper.updateById(rechargeOrder);

            // 记录余额日志
            userAccountLogMapper.dec(
                    user.getId(),
                    LogMoneyEnum.UM_DEC_RECHARGE.getCode(),
                    rechargeOrder.getOrderAmount(),
                    rechargeOrder.getId(),
                    rechargeOrder.getSn(),
                    "充值订单退款",
                    null
            );


            // 更新用户余额
            user.setUserMoney(user.getUserMoney().subtract(rechargeOrder.getOrderAmount()));
            userMapper.updateById(user);

            // 生成退款记录
            String refundSn = refundRecordMapper.randMakeOrderSn("sn");
            refundRecord = new RefundRecord();
            refundRecord.setSn(refundSn);
            refundRecord.setUserId(rechargeOrder.getUserId());
            refundRecord.setOrderId(rechargeOrder.getId());
            refundRecord.setOrderSn(rechargeOrder.getSn());
            refundRecord.setOrderType(RefundEnum.getOrderType(RefundEnum.ORDER_TYPE_RECHARGE.getCode()));
            refundRecord.setOrderAmount(rechargeOrder.getOrderAmount());
            refundRecord.setRefundAmount(rechargeOrder.getOrderAmount());
            refundRecord.setRefundType(RefundEnum.TYPE_ADMIN.getCode());
            refundRecord.setTransactionId(refundRecord.getTransactionId());
            refundRecord.setRefundWay(rechargeOrder.getPayWay());
            refundRecordMapper.insert(refundRecord);

            // 生成退款日志
            log = new RefundLog();
            log.setSn(refundLogMapper.randMakeOrderSn("sn"));
            log.setRecordId(refundRecord.getId());
            log.setUserId(rechargeOrder.getUserId());
            log.setHandleId(adminId);
            log.setOrderAmount(rechargeOrder.getOrderAmount());
            log.setRefundAmount(refundRecord.getRefundAmount());
            log.setRefundStatus(RefundEnum.REFUND_ING.getCode());
            log.setCreateTime(System.currentTimeMillis() / 1000);
            log.setUpdateTime(System.currentTimeMillis() / 1000);
            refundLogMapper.insert(log);

            // 发起退款请求
            String refundResponse = null;
            if (rechargeOrder.getPayWay().equals(PaymentEnum.ALI_PAY.getCode())) { //支付宝退款
                refundResponse = this.aliPayRefund(rechargeOrder, refundSn);
            } else { //微信
                refundResponse = this.wxPayRefund(rechargeOrder, refundSn);
            }

            // 退款记录更新
            refundRecord.setRefundStatus(RefundEnum.REFUND_SUCCESS.getCode());
            refundRecord.setCreateTime(System.currentTimeMillis() / 1000);
            refundRecord.setUpdateTime(System.currentTimeMillis() / 1000);
            refundRecord.setTransactionId(rechargeOrder.getTransactionId());
            refundRecordMapper.updateById(refundRecord);

            // 退款日志更新
            log.setRefundStatus(RefundEnum.REFUND_SUCCESS.getCode());
            log.setUpdateTime(System.currentTimeMillis() / 1000);
            if (StringUtils.isNotNull(refundResponse)) {
                log.setRefundMsg(refundResponse);
            }
            refundLogMapper.updateById(log);

            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            // 事务回滚
            transactionManager.rollback(transactionStatus);

            if (StringUtils.isNotNull(refundRecord)) {
                refundRecord.setRefundStatus(RefundEnum.REFUND_ERROR.getCode());
                refundRecordMapper.updateById(refundRecord);
            }

            if (StringUtils.isNotNull(log)) {
                log.setRefundStatus(RefundEnum.REFUND_ERROR.getCode());
                refundLogMapper.updateById(log);
            }
            throw new OperateException(e.getMessage());
        }
    }

    /**
     * 支付宝退款
     * @param rechargeOrder
     * @param refundSn
     */
    private String aliPayRefund(RechargeOrder rechargeOrder, String refundSn) throws Exception {
        String gateWay = StringUtils.isNotNull(YmlUtils.get("like.alidebug")) && YmlUtils.get("like.alidebug").equals("true") ? AlipayConfig.GATEWAY_URL_DEBUG : AlipayConfig.GATEWAY_URL;
        AlipayClient alipayClient = new DefaultAlipayClient(gateWay, ConfigUtils.getAliDevPay("app_id"), ConfigUtils.getAliDevPay("private_key"), "json", AlipayConfig.CHARSET, ConfigUtils.getAliDevPay("ali_public_key"), AlipayConfig.SIGN_TYPE);
        AlipayTradeRefundRequest aliRequest = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel alipayTradeRefundModel = new AlipayTradeRefundModel();
        alipayTradeRefundModel.setTradeNo(rechargeOrder.getTransactionId());
        alipayTradeRefundModel.setRefundAmount(rechargeOrder.getOrderAmount().toString());
        alipayTradeRefundModel.setOutRequestNo(rechargeOrder.getSn());
        aliRequest.setBizModel(alipayTradeRefundModel);
        AlipayTradeRefundResponse response = alipayClient.execute(aliRequest);
        if(response.isSuccess()) {
            String ret = JSONObject.toJSONString(response);
            if (StringUtils.isNotNull(ret)) {
                JSONObject refundResponseJSON = JSONObject.parseObject(ret);
                rechargeOrder.setRefundTransactionId(refundResponseJSON.getBigInteger("tradeNo"));
                rechargeOrderMapper.updateById(rechargeOrder);
            }
            return ret;
        } else {
            throw new Exception(response.getBody());
        }
    }

    /**
     * w微信退款
     * @param rechargeOrder
     * @param refundSn
     * @throws WxPayException
     */
    private String wxPayRefund(RechargeOrder rechargeOrder, String refundSn) throws WxPayException {
        String refundTransactionId = null;
        RefundRequestV3 requestV3 = new RefundRequestV3();
        requestV3.setTransactionId(rechargeOrder.getTransactionId());
        requestV3.setOutTradeNo(rechargeOrder.getSn());
        requestV3.setOutRefundNo(refundSn);
        requestV3.setTotalAmount(AmountUtil.yuan2Fen(rechargeOrder.getOrderAmount().toString()));
        requestV3.setRefundAmount(AmountUtil.yuan2Fen(rechargeOrder.getOrderAmount().toString()));
        WxPayRefundV3Result result = WxPayDriver.refund(requestV3);

        refundTransactionId = result.getTransactionId();
        rechargeOrder.setRefundTransactionId(new BigInteger(refundTransactionId));
        rechargeOrderMapper.updateById(rechargeOrder);

        return JSONObject.toJSONString(result);
    }

    /**
     * 重新退款
     *
     * @author fzr
     * @param recordId 记录ID
     * @param adminId 管理员ID
     */
    @Override
    public void refundAgain(Integer recordId, Integer adminId) {
        // 开启事务
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        RefundLog log = null;
        try {
            RefundRecord refundRecord = refundRecordMapper.selectById(recordId);
            RechargeOrder rechargeOrder = rechargeOrderMapper.selectById(refundRecord.getOrderId());

            Assert.notNull(rechargeOrder, "充值订单丢失!");

            log = refundLogMapper.selectOne(new QueryWrapper<RefundLog>()
                    .eq("record_id", recordId)
                    .last("limit 1"));

            log.setRefundStatus(RefundEnum.REFUND_ING.getCode());
            refundLogMapper.updateById(log);

            // 发起退款请求
            RefundRequestV3 requestV3 = new RefundRequestV3();
            requestV3.setTransactionId(refundRecord.getTransactionId());
            requestV3.setOutTradeNo(refundRecord.getOrderSn());
            requestV3.setOutRefundNo(refundRecord.getSn());
            requestV3.setTotalAmount(AmountUtil.yuan2Fen(rechargeOrder.getOrderAmount().toString()));
            requestV3.setRefundAmount(AmountUtil.yuan2Fen(refundRecord.getOrderAmount().toString()));
            WxPayDriver.refund(requestV3);

            log.setRefundStatus(RefundEnum.REFUND_SUCCESS.getCode());
            refundLogMapper.updateById(log);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            if (StringUtils.isNotNull(log)) {
                log.setRefundStatus(RefundEnum.REFUND_ERROR.getCode());
                refundLogMapper.updateById(log);
            }
            throw new OperateException(e.getMessage());
        }
    }

    @Override
    public JSONObject getExportData(PageValidate pageValidate, FinanceRechargeSearchValidate searchValidate) {
        Integer page  = pageValidate.getPage_no();
        Integer limit = pageValidate.getPage_size();
        PageResult<FinanceRechargeListVo> userVoPageResult = this.list(pageValidate, searchValidate);
        JSONObject ret  = ToolUtils.getExportData(userVoPageResult.getCount(), limit, searchValidate.getPage_start(), searchValidate.getPage_end(),"充值列表");
        return ret;
    }

    @Override
    public String export(FinanceRechargeSearchValidate searchValidate) {
        PageValidate pageValidate = new PageValidate();
        if (StringUtils.isNotNull(searchValidate.getPage_start())) {
            pageValidate.setPage_no(searchValidate.getPage_start());
        } else {
            pageValidate.setPage_no(1);
        }

        if (StringUtils.isNotNull(searchValidate.getPage_end()) && StringUtils.isNotNull(searchValidate.getPage_size())) {
            pageValidate.setPage_size(searchValidate.getPage_end() * searchValidate.getPage_size());
        } else {
            pageValidate.setPage_size(20);
        }
        Boolean isAll = StringUtils.isNull(searchValidate.getPage_type()) || searchValidate.getPage_type().equals(0) ? true : false;
        List<FinanceRechargeListExportVo> excellist = this.getExcellist(isAll, pageValidate, searchValidate);
        String fileName = StringUtils.isNull(searchValidate.getFile_name()) ? ToolUtils.makeUUID() : searchValidate.getFile_name();
        String folderPath = "/excel/export/"+ TimeUtils.timestampToDay(System.currentTimeMillis() / 1000) +"/" ;
        String path =  folderPath +  fileName +".xlsx";
        String filePath =  YmlUtils.get("like.upload-directory") + path;
        File folder = new File(YmlUtils.get("like.upload-directory") + folderPath);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new OperateException("创建文件夹失败");
            }
        }
        EasyExcel.write(filePath)
                .head(FinanceRechargeListExportVo.class)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("充值记录")
                .doWrite(excellist);
        return UrlUtils.toAdminAbsoluteUrl(path);
    }

    private List<FinanceRechargeListExportVo> getExcellist(boolean isAll, PageValidate pageValidate, FinanceRechargeSearchValidate searchValidate) {
        Integer pageNo = pageValidate.getPage_no();
        Integer pageSize = pageValidate.getPage_size();

        MPJQueryWrapper<RechargeOrder> mpjQueryWrapper = new MPJQueryWrapper<>();
        mpjQueryWrapper.selectAll(RechargeOrder.class)
                .select("U.id as user_id,U.account,U.nickname,U.avatar,t.sn AS sn")
                .leftJoin("?_user U ON U.id=t.user_id".replace("?_", GlobalConfig.tablePrefix))
                .orderByDesc("id");

        rechargeOrderMapper.setSearch(mpjQueryWrapper, searchValidate, new String[]{
                "like:sn@t.sn:str",
                "=:pay_way@t.pay_way:int",
                "=:pay_status@t.pay_status:int",
                "datetime:start_time-end_time@create_time:long",
        });

        if (StringUtils.isNotEmpty(searchValidate.getUser_info())) {
            String keyword = searchValidate.getUser_info();
            mpjQueryWrapper.nested(wq->wq
                    .like("U.nickname", keyword).or()
                    .like("U.sn", keyword).or()
                    .like("U.mobile", keyword));
        }
        List<FinanceRechargeListExportVo> retList = new ArrayList<>();
        if (isAll) {
            retList = rechargeOrderMapper.selectJoinList(FinanceRechargeListExportVo.class, mpjQueryWrapper);
        } else {
            IPage<FinanceRechargeListExportVo> iPage = rechargeOrderMapper.selectJoinPage(
                    new Page<>(pageNo, pageSize),
                    FinanceRechargeListExportVo.class,
                    mpjQueryWrapper);
            retList = iPage.getRecords();
        }
        for (FinanceRechargeListExportVo vo : retList) {
            vo.setCreateTime(TimeUtils.timestampToDate(vo.getCreateTime()));
            vo.setPayTime(TimeUtils.timestampToDate(vo.getPayTime()));
            vo.setAvatar(UrlUtils.toAdminAbsoluteUrl(vo.getAvatar()));
            vo.setPayWay(vo.getPayWay());
            vo.setPayWayText(PaymentEnum.getPayWayMsg(Integer.parseInt(vo.getPayWay())));
            vo.setPayStatusText(PaymentEnum.getPayStatusMsg(vo.getPayStatus()));
        }
        return retList;
    }
}
