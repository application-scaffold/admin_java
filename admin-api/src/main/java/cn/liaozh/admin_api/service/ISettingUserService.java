package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.setting.SettingUserValidate;
import cn.liaozh.admin_api.vo.setting.SettingUserVo;

/**
 * 用户设置服务接口类
 */
public interface ISettingUserService {

    /**
     * 用户设置详情
     *
     * @author fzr
     * @return SettingUserVo
     */
    SettingUserVo getConfig();

    /**
     * 用户设置保存
     *
     * @author fzr
     * @param userValidate 参数
     */
    void setConfig(SettingUserValidate userValidate);

}
