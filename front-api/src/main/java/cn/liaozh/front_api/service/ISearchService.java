package cn.liaozh.front_api.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 搜索接口类
 */
public interface ISearchService {

    /**
     * 热搜
     *
     * @author fzr
     */
    JSONObject hotLists();

}
