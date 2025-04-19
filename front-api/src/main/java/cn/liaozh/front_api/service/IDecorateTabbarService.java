package cn.liaozh.front_api.service;

import cn.liaozh.front_api.vo.decorateTabbar.DecorateTabbarVo;

import java.util.List;

/**
 * 底部导航服务接口类
 */
public interface IDecorateTabbarService {

    /**
     * @notes 获取底部导航列表
     * @return array
     * @author damonyuan
     */
    List<DecorateTabbarVo> getTabbarLists();

}
