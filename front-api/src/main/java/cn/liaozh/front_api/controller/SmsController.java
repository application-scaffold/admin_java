package cn.liaozh.front_api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.liaozh.common.aop.NotLogin;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.entity.smsLog.SmsLog;
import cn.liaozh.common.enums.NoticeEnum;
import cn.liaozh.common.enums.SmsEnum;
import cn.liaozh.common.enums.YesNoEnum;
import cn.liaozh.common.exception.OperateException;
import cn.liaozh.common.mapper.smsLog.SmsLogMapper;
import cn.liaozh.common.plugin.notice.NoticeDriver;
import cn.liaozh.common.plugin.notice.vo.NoticeSmsVo;
import cn.liaozh.common.util.StringUtils;
import cn.liaozh.common.util.ToolUtils;
import cn.liaozh.front_api.validate.common.SmsValidate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms")
@Schema(name = "主页管理")
public class SmsController {

    @Resource
    SmsLogMapper smsLogMapper;

    @NotLogin
    @PostMapping("/sendCode")
    @Operation(summary = "发送短信")
    public AjaxResult<Object> sendSms(@Validated @RequestBody SmsValidate smsValidate) {
        QueryWrapper smsLogQueryWrapper = new QueryWrapper<SmsLog>();
        smsLogQueryWrapper.eq("mobile", smsValidate.getMobile());
        smsLogQueryWrapper.eq("send_status", SmsEnum.SEND_SUCCESS.getCode());
        smsLogQueryWrapper.in("scene_id", NoticeEnum.getSmsScene());
        smsLogQueryWrapper.eq("is_verify", YesNoEnum.NO.getCode());
        if (StringUtils.isNotNull(smsValidate.getScene())) {
            smsLogQueryWrapper.eq("scene_id", NoticeEnum.getSceneByTag(smsValidate.getScene()));
        }
        smsLogQueryWrapper.orderByDesc("send_time");
        smsLogQueryWrapper.last("limit 1");
        SmsLog smsLog = smsLogMapper.selectOne(smsLogQueryWrapper);
        if (StringUtils.isNotNull(smsLog)) {
            if (smsLog.getSendTime() + 5 * 60 > System.currentTimeMillis() / 1000 ) {
                throw new OperateException("已有短信记录，请勿重复发送");
            }
        }

        String code = ToolUtils.randomInt(4);
        NoticeSmsVo params = new NoticeSmsVo()
                .setScene(NoticeEnum.getSceneByTag(smsValidate.getScene()))
                .setMobile(smsValidate.getMobile())
                .setExpire(900)
                .setParams(new String[] {
                        "code:" + code
                })
                .setCode(code);

        NoticeDriver.handle(params);
        return AjaxResult.success();
    }

}
