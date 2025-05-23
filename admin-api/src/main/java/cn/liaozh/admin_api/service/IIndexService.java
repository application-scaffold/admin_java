package cn.liaozh.admin_api.service;

import java.util.Map;

/**
 * 主页服务接口类
 */
public interface IIndexService {

    /**
     * 控制台数据
     *
     * @author fzr
     * @return Map<String, Object>
     */
    Map<String, Object> index();

    /**
     * 公共配置
     */
    Map<String, Object> config();



}
