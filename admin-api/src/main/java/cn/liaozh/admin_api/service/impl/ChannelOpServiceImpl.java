package cn.liaozh.admin_api.service.impl;

import cn.liaozh.admin_api.service.IChannelOpService;
import cn.liaozh.admin_api.validate.channel.ChannelOpValidate;
import cn.liaozh.admin_api.vo.channel.ChannelOpVo;
import cn.liaozh.common.util.ConfigUtils;
import cn.liaozh.common.util.StringUtils;
import cn.liaozh.common.util.YmlUtils;
import org.springframework.stereotype.Service;

/**
 * 开放平台设置服务类
 */
@Service
public class ChannelOpServiceImpl implements IChannelOpService {

    /**
     * 开放平台设置详情
     *
     * @author fzr
     * @return ChannelOpVo
     */
    @Override
    public ChannelOpVo getConfig() {
        String appId = ConfigUtils.get("open_platform", "app_id", "");
        String appSecret = ConfigUtils.get("open_platform", "app_secret", "");

        String env = YmlUtils.get("like.production");
        boolean envStatus = StringUtils.isNotNull(env) && env.equals("true");

        ChannelOpVo vo = new ChannelOpVo();
        vo.setAppId(envStatus ? "******" : appId);
        vo.setAppSecret(envStatus ? "******" : appSecret);
        return vo;
    }

    /**
     * 开放平台设置保存
     *
     * @author fzr
     * @param opValidate 参数
     */
    @Override
    public void setConfig(ChannelOpValidate opValidate) {
        ConfigUtils.set("open_platform", "app_id", opValidate.getAppId());
        ConfigUtils.set("open_platform", "app_secret", opValidate.getAppSecret());
    }

}
