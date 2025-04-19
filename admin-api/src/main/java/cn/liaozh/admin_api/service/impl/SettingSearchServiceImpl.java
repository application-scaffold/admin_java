package cn.liaozh.admin_api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.liaozh.admin_api.service.ISettingSearchService;
import cn.liaozh.admin_api.validate.setting.SettingSearchValidate;
import cn.liaozh.admin_api.vo.setting.SettingSearchDetailVo;
import cn.liaozh.admin_api.vo.setting.SettingSearchObjectVo;
import cn.liaozh.common.entity.setting.HotSearch;
import cn.liaozh.common.mapper.setting.HotSearchMapper;
import cn.liaozh.common.util.ConfigUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 热门搜索服务实现类
 */
@Service
public class SettingSearchServiceImpl implements ISettingSearchService {

    @Resource
    HotSearchMapper hotSearchMapper;

    /**
     * 热门搜索详情
     *
     * @author fzr
     * @return SettingSearchDetailVo
     */
    @Override
    public SettingSearchDetailVo getConfig() {
        Integer status = Integer.parseInt(ConfigUtils.get("hot_search", "status", "0"));
        List<HotSearch> list = hotSearchMapper.selectList(new QueryWrapper<HotSearch>().orderByDesc("sort"));

        SettingSearchDetailVo vo = new SettingSearchDetailVo();
        vo.setStatus(status);
        vo.setData(list);
        return vo;
    }

    /**
     * 热门搜索新增
     *
     * @author fzr
     * @param searchValidate 参数
     */
    @Override
    public void setConfig(SettingSearchValidate searchValidate) {
        String isHotSearch = String.valueOf(searchValidate.getStatus());
        ConfigUtils.set("hot_search", "status", isHotSearch);

        hotSearchMapper.delete(new QueryWrapper<HotSearch>().ge("id", 0));
        for (SettingSearchObjectVo vo : searchValidate.getData()) {
            HotSearch hotSearch = new HotSearch();
            hotSearch.setName(vo.getName());
            hotSearch.setSort(vo.getSort());
            hotSearchMapper.insert(hotSearch);
        }
    }

}
