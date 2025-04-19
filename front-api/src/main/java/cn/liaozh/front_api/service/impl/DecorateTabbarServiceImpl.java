package cn.liaozh.front_api.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.liaozh.common.entity.decorate.DecorateTabbar;
import cn.liaozh.common.enums.YesNoEnum;
import cn.liaozh.common.mapper.decorate.DecorateTabbarMapper;
import cn.liaozh.common.util.*;
import cn.liaozh.front_api.service.IDecorateTabbarService;
import cn.liaozh.front_api.vo.decorateTabbar.DecorateTabbarVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 底部导航服务实现类
 */
@Service
public class DecorateTabbarServiceImpl implements IDecorateTabbarService {

    @Resource
    DecorateTabbarMapper decorateTabbarMapper;


    @Override
    public List<DecorateTabbarVo> getTabbarLists() {
        List<DecorateTabbar> list = decorateTabbarMapper.selectList(new QueryWrapper<DecorateTabbar>().eq("is_show", YesNoEnum.YES.getCode()));
        List<DecorateTabbarVo> ret = new ArrayList<DecorateTabbarVo>();
        if (list.size() == 0) {
            return ret;
        }

        for (DecorateTabbar item : list) {
            DecorateTabbarVo vo = new DecorateTabbarVo();
            BeanUtils.copyProperties(item, vo);
            if (StringUtils.isNotEmpty(item.getSelected())) {
                vo.setSelected(UrlUtils.toAbsoluteUrl(item.getSelected()));
            }

            if (StringUtils.isNotEmpty(item.getUnselected())) {
                vo.setUnselected(UrlUtils.toAbsoluteUrl(item.getUnselected()));
            }

            vo.setCreateTime(TimeUtils.timestampToDate(item.getCreateTime()));
            vo.setUpdateTime(TimeUtils.timestampToDate(item.getUpdateTime()));
            vo.setLink(StringUtils.isEmpty(item.getLink()) ? new JSONObject() : JSONObject.parse(item.getLink()));
            ret.add(vo);
        }
        return ret;
    }
}
