package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.decorate.DecoratePageValidate;
import cn.liaozh.admin_api.vo.decorate.DecoratePageVo;

public interface IDecoratePageService {

    /**
     * 页面装修详情
     *
     * @author fzr
     * @param id 主键
     * @return DecoratePageVo
     */
    DecoratePageVo detail(Integer id);

    /**
     * 页面装修保存
     *
     * @author fzr
     * @param decoratePageValidate 参数
     */
    void save(DecoratePageValidate decoratePageValidate);
}
