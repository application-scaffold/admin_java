package cn.liaozh.common.plugin.notice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.liaozh.common.entity.notice.NoticeSetting;
import cn.liaozh.common.exception.OperateException;
import cn.liaozh.common.mapper.notice.NoticeSettingMapper;
import cn.liaozh.common.plugin.notice.engine.SmsNoticeHandle;
import cn.liaozh.common.plugin.notice.template.SmsTemplate;
import cn.liaozh.common.plugin.notice.vo.NoticeSmsVo;
import cn.liaozh.common.util.SpringUtils;
import cn.liaozh.common.util.StringUtils;

/**
 * 通知驱动
 */
public class NoticeDriver {

    public static void handle(NoticeSmsVo noticeSmsVo) {
        // 场景模板
        NoticeSettingMapper noticeSettingMapper = SpringUtils.getBean(NoticeSettingMapper.class);
        NoticeSetting noticeSetting = noticeSettingMapper.selectOne(
                new QueryWrapper<NoticeSetting>()
                        .eq("scene_id", noticeSmsVo.getScene())
                        .last("limit 1"));

        if (StringUtils.isNull(noticeSetting)) {
            throw new OperateException("消息场景不存在!");
        }

        // 短信通知
        SmsTemplate smsTemplate = new SmsTemplate();
        smsTemplate.setName(noticeSetting.getSceneName());
        smsTemplate.setType(noticeSetting.getType());
        smsTemplate.setParams(noticeSetting.getSmsNotice());
        if (StringUtils.isNotNull(smsTemplate.getStatus()) && smsTemplate.getStatus().equals(1)) {
            (new SmsNoticeHandle()).send(noticeSmsVo, smsTemplate);
        }
    }

}
