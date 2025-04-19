package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.decorate.DecorateTabsValidate;
import cn.liaozh.admin_api.vo.decorate.DecorateTabbarVo;

/**
 * 底部导航服务接口类
 */
public interface IDecorateTabbarService {

    /**
     * 底部导航详情
     *
     * @author fzr
     * @return DecorateTabbarVo
     */
    DecorateTabbarVo detail();

    /**
     * 底部导航保存
     *
     * @author fzr
     * @param tabsValidate 参数
     */
    void save(DecorateTabsValidate tabsValidate);

}
