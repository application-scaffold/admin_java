package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.channel.ChannelOpValidate;
import cn.liaozh.admin_api.vo.channel.ChannelOpVo;

/**
 * 微信开放渠道设置接口服务类
 */
public interface IChannelOpService {

    /**
     * 开放平台设置详情
     *
     * @author fzr
     * @return ChannelOpVo
     */
    ChannelOpVo getConfig();

    /**
     * 开放平台设置保存
     *
     * @author fzr
     * @param opValidate 参数
     */
    void setConfig(ChannelOpValidate opValidate);

}
