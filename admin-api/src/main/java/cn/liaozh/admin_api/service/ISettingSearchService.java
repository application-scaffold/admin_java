package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.setting.SettingSearchValidate;
import cn.liaozh.admin_api.vo.setting.SettingSearchDetailVo;

/**
 * 热门搜索服务接口类
 */
public interface ISettingSearchService {

    /**
     * 热门搜索详情
     *
     * @author fzr
     * @return SettingSearchDetailVo
     */
    SettingSearchDetailVo getConfig();

    /**
     * 热门搜索新增
     *
     * @author fzr
     * @param searchValidate 参数
     */
     void setConfig(SettingSearchValidate searchValidate);


}
