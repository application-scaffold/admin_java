package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.channel.ChannelH5Validate;
import cn.liaozh.admin_api.vo.channel.ChannelH5Vo;

/**
 * H5渠道设置服务接口类
 */
public interface IChannelH5ConfigService {

    /**
     * H5设置详情
     *
     * @author fzr
     * @return ChannelH5Vo
     */
    ChannelH5Vo getConfig();

    /**
     * H5设置保存
     *
     * @author fzr
     * @param channelH5Validate 参数
     */
    void setConfig(ChannelH5Validate channelH5Validate);

}
