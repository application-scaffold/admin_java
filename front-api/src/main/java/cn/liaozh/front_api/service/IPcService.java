package cn.liaozh.front_api.service;

import cn.liaozh.front_api.vo.article.PcArticleCenterVo;
import cn.liaozh.front_api.vo.article.PcArticleDetailVo;

import java.util.List;
import java.util.Map;

public interface IPcService {

    /**
     * 配置
     * @author cjh
     * @return Map<String, Object>
     */
    Map<String, Object> index();

    /**
     * 配置
     * @author cjh
     * @return Map<String, Object>
     */
    Map<String, Object> getConfig();

    /**
     * 资讯中心
     *
     * @author fzr
     * @return PcArticleCenterVo
     */
    List<PcArticleCenterVo> infoCenter();

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 文章ID
     * @param userId 用户ID
     * @return PcArticleDetailVo
     */
    PcArticleDetailVo articleDetail(Integer id, Integer userId);

}
