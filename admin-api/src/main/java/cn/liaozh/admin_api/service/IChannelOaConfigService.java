package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.channel.ChannelOaValidate;
import cn.liaozh.admin_api.vo.channel.ChannelOaVo;


/**
 * 公众号渠道设置服务接口类
 */
public interface IChannelOaConfigService {

    /**
     * 公众号设置详情
     *
     * @author fzr
     * @return ChannelOaVo
     */
    ChannelOaVo getConfig();

    /**
     * 公众号设置保存
     *
     * @author fzr
     * @param channelOaValidate 参数
     */
    void setConfig(ChannelOaValidate channelOaValidate);

}
