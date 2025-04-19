package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.setting.SettingWebsiteValidate;
import cn.liaozh.admin_api.vo.setting.SettingWebsiteVo;

/**
 * 网站信息服务接口类
 */
public interface ISettingWebsiteService {

    /**
     * 获取网站信息
     *
     * @author fzr
     * @return SettingWebsiteVo
     */
    SettingWebsiteVo getWebsite();

    /**
     * 保存网站信息
     *
     * @author fzr
     * @param websiteValidate 参数
     */
    void setWebsite(SettingWebsiteValidate websiteValidate);

}
