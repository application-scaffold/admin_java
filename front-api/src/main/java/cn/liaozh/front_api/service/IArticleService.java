package cn.liaozh.front_api.service;

import cn.liaozh.common.core.PageResult;
import cn.liaozh.front_api.validate.article.ArticleSearchValidate;
import cn.liaozh.front_api.validate.common.PageValidate;
import cn.liaozh.front_api.vo.article.ArticleCateVo;
import cn.liaozh.front_api.vo.article.ArticleCollectVo;
import cn.liaozh.front_api.vo.article.ArticleDetailVo;
import cn.liaozh.front_api.vo.article.ArticleListedVo;

import java.util.List;

/**
 * 文章服务接口类
 */
public interface IArticleService {

    /**
     * 文章分类
     *
     * @author fzr
     * @return List<ArticleCateVo>
     */
    List<ArticleCateVo> category();

    /**
     * 文章分类
     *
     * @author fzr
     * @param userId 用户ID
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<ArticleListVo>
     */
    PageResult<ArticleListedVo> list(Integer userId, PageValidate pageValidate, ArticleSearchValidate searchValidate);

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 文章主键
     * @param userId 用户ID
     * @return ArticleDetailVo
     */
    ArticleDetailVo detail(Integer id, Integer userId);

    /**
     * 文章收藏
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param userId 用户ID
     * @return PageResult<ArticleListVo>
     */
    PageResult<ArticleCollectVo> collect(PageValidate pageValidate, Integer userId);

    /**
     * 加入收藏
     *
     * @author fzr
     * @param articleId 文章ID
     * @param userId 用户ID
     */
    void addCollect(Integer articleId, Integer userId);

    /**
     * 取消收藏
     *
     * @author fzr
     * @param articleId 主键
     * @param userId 用户ID
     */
    void cancelCollect(Integer articleId, Integer userId);

}
