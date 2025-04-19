package cn.liaozh.front_api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.liaozh.common.entity.setting.HotSearch;
import cn.liaozh.common.mapper.setting.HotSearchMapper;
import cn.liaozh.common.util.*;
import cn.liaozh.front_api.service.ISearchService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 首页服务实现类
 */
@Service
public class SearchServiceImpl implements ISearchService {


    @Resource
    HotSearchMapper hotSearchMapper;

    @Override
    public JSONObject hotLists() {

        List<HotSearch> hotSearches = hotSearchMapper.selectList(new QueryWrapper<HotSearch>().orderByDesc("sort"));
        JSONObject result = new JSONObject(){{
            put("status", ConfigUtils.get("hot_search", "status", "0"));
            put("data", hotSearches);
        }};

        return result;
    }
}
