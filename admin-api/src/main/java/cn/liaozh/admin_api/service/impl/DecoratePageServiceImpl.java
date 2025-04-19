package cn.liaozh.admin_api.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import cn.liaozh.admin_api.service.IDecoratePageService;
import cn.liaozh.admin_api.validate.decorate.DecoratePageValidate;
import cn.liaozh.admin_api.vo.decorate.DecoratePageVo;
import cn.liaozh.common.entity.decorate.DecoratePage;
import cn.liaozh.common.mapper.decorate.DecoratePageMapper;
import cn.liaozh.common.util.TimeUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class DecoratePageServiceImpl implements IDecoratePageService {

    @Resource
    DecoratePageMapper decoratePageMapper;

    /**
     * 页面装修详情
     *
     * @author fzr
     * @param id 主键
     * @return DecoratePageVo
     */
    @Override
    public DecoratePageVo detail(Integer id) {
        DecoratePage decoratePage = decoratePageMapper.selectById(id);
        Assert.notNull(decoratePage, "数据不存在!");
        DecoratePageVo vo = new DecoratePageVo();
        vo.setId(decoratePage.getId());
        vo.setName(decoratePage.getName());
        vo.setType(decoratePage.getType());
        vo.setData(decoratePage.getData());
        vo.setMeta(decoratePage.getMeta());

        vo.setCreateTime(TimeUtils.timestampToDate(decoratePage.getCreateTime()));
        vo.setUpdateTime(TimeUtils.timestampToDate(decoratePage.getUpdateTime()));
        return vo;
    }

    /**
     * 页面装修保存
     *
     * @author fzr
     * @param decoratePageValidate 参数
     */
    @Override
    public void save(DecoratePageValidate decoratePageValidate) {
        DecoratePage decoratePage = decoratePageMapper.selectById(decoratePageValidate.getId());
        Assert.notNull(decoratePage, "数据不存在!");

        decoratePage.setName(decoratePageValidate.getName());
        decoratePage.setType(decoratePageValidate.getType());
        decoratePage.setMeta(decoratePageValidate.getMeta());
        decoratePage.setData(decoratePageValidate.getData());
        decoratePage.setUpdateTime(System.currentTimeMillis() / 1000);
        decoratePageMapper.updateById(decoratePage);
    }

}
