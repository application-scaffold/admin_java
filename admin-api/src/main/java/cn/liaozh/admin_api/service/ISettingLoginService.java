package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.setting.SettingLoginValidate;
import cn.liaozh.admin_api.vo.setting.SettingLoginVo;

/**
 * 登录设置服务接口类
 */
public interface ISettingLoginService {

    /**
     * 登录设置详情
     *
     * @author fzr
     * @return SettingLoginVo
     */
    SettingLoginVo getRegisterConfig();

    /**
     * 登录设置保存
     *
     * @author fzr
     * @param loginValidate 参数
     */
    void setRegisterConfig(SettingLoginValidate loginValidate);

}
