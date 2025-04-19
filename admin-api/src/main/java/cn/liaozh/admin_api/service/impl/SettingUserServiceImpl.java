package cn.liaozh.admin_api.service.impl;

import cn.liaozh.admin_api.service.ISettingUserService;
import cn.liaozh.admin_api.validate.setting.SettingUserValidate;
import cn.liaozh.admin_api.vo.setting.SettingUserVo;
import cn.liaozh.common.util.ConfigUtils;
import cn.liaozh.common.util.UrlUtils;
import org.springframework.stereotype.Service;

/**
 * 用户设置服务实现类
 */
@Service
public class SettingUserServiceImpl implements ISettingUserService {

    /**
     * 用户设置详情
     *
     * @author fzr
     * @return SettingUserVo
     */
    @Override
    public SettingUserVo getConfig() {
        String defaultAvatar = ConfigUtils.get("default_image", "user_avatar", "");
        SettingUserVo vo = new SettingUserVo();
        vo.setDefaultAvatar(UrlUtils.toAdminAbsoluteUrl(defaultAvatar));
        return vo;
    }

    /**
     * 用户设置保存
     *
     * @author fzr
     * @param userValidate 参数
     */
    @Override
    public void setConfig(SettingUserValidate userValidate) {
        ConfigUtils.set("default_image", "user_avatar", UrlUtils.toRelativeUrl(userValidate.getDefaultAvatar()));
    }

}
