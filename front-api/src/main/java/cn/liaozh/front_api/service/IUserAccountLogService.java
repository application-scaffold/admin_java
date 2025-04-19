package cn.liaozh.front_api.service;

import cn.liaozh.common.core.PageResult;
import cn.liaozh.front_api.validate.common.PageValidate;
import cn.liaozh.front_api.validate.users.*;
import cn.liaozh.front_api.vo.user.UserAccountListVo;

/**
 * 用户服务接口类
 */
public interface IUserAccountLogService {

    /**
     * @notes 获取列表
     * @return array
     * @author damonyuan
     */
    PageResult<UserAccountListVo> lists(PageValidate pageValidate, UserAccountLogSearchValidate searchValidate);
}
