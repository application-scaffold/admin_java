package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.channel.ChannelMpValidate;
import cn.liaozh.admin_api.vo.channel.ChannelMpVo;

/**
 * 微信小程序渠道服务接口类
 */
public interface IChannelMpConfigService {

    /**
     * 微信小程序设置详情
     *
     * @author fzr
     * @return ChannelMpVo
     */
    ChannelMpVo getConfig();

    /**
     * 微信小程序设置保存
     *
     * @author fzr
     * @param channelMpValidate 参数
     */
    void setConfig(ChannelMpValidate channelMpValidate);

}
