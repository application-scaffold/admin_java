package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.setting.SettingCopyrightValidate;
import cn.liaozh.admin_api.vo.setting.SettingCopyrightVo;

import java.util.List;

/**
 * 网站备案服务接口类
 */
public interface ISettingCopyrightService {

    /**
     * 获取网站备案信息
     *
     * @author fzr
     * @return List<SettingCopyrightVo>
     */
    List<SettingCopyrightVo> getCopyright();

    /**
     * 保存网站备案信息
     *
     * @author fzr
     * @param copyrightValidate 参数
     */
    void setCopyright(SettingCopyrightValidate copyrightValidate);

}
