package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.channel.ChannelRpValidate;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.vo.channel.ChannelRpVo;
import cn.liaozh.common.core.PageResult;

/**
 * 公众号默认回复服务接口类
 */
public interface IChannelOaReplyService {

    /**
     * 默认回复列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return PageResult<ChannelRpDefaultVo>
     */
    PageResult<ChannelRpVo> list(PageValidate pageValidate, Integer replyType);

    /**
     * 默认回复详情
     *
     * @author fzr
     * @param id 主键
     * @return ChannelRpDefaultVo
     */
    ChannelRpVo detail(Integer id);

    /**
     * 默认回复新增
     *
     * @author fzr
     * @param defaultValidate 参数
     */
    void add(ChannelRpValidate defaultValidate);

    /**
     * 默认回复编辑
     *
     * @author fzr
     * @param defaultValidate 参数
     */
    void edit(ChannelRpValidate defaultValidate);

    /**
     * 默认回复删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

    /**
     * 默认回复状态
     *
     * @author fzr
     * @param id 主键
     */
    void status(Integer id);

}
