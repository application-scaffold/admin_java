package cn.liaozh.common.plugin.notice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.liaozh.common.entity.smsLog.SmsLog;
import cn.liaozh.common.enums.NoticeEnum;
import cn.liaozh.common.enums.SmsEnum;
import cn.liaozh.common.enums.YesNoEnum;
import cn.liaozh.common.mapper.smsLog.SmsLogMapper;
import cn.liaozh.common.util.SpringUtils;
import cn.liaozh.common.util.StringUtils;

/**
 * 通知验证码验证器
 */
public class NoticeCheck {

    public static Boolean verify(Integer sceneId, String code, String mobile) {

        SmsLogMapper smsLogMapper = SpringUtils.getBean(SmsLogMapper.class);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile", mobile);
        queryWrapper.eq("send_status", SmsEnum.SEND_SUCCESS.getCode());
        queryWrapper.in("scene_id", NoticeEnum.getSmsScene());
        queryWrapper.eq("is_verify", YesNoEnum.NO.getCode());
        if (sceneId.equals(0) == false) {
            queryWrapper.eq("scene_id", sceneId);
        }
        queryWrapper.orderByDesc("send_time");
        queryWrapper.last("limit 1");
        SmsLog smsLog = smsLogMapper.selectOne(queryWrapper);

        if (StringUtils.isNull(smsLog)) {
            return false;
        }

        // 没有验证码 或 最新验证码已校验 或 已过期(有效期：5分钟)
        if (smsLog.getIsVerify().equals(1) || smsLog.getSendTime() < System.currentTimeMillis() / 1000 - (5*60)) {
            return false;
        }

        // 更新校验状态
        smsLog.setCheckNum(smsLog.getCheckNum() + 1);
        if (smsLog.getCode().equals(code)) {
            smsLog.setIsVerify(YesNoEnum.YES.getCode());
            smsLogMapper.updateById(smsLog);
            return true;
        } else {
            smsLogMapper.updateById(smsLog);
            return false;
        }
    }

}
