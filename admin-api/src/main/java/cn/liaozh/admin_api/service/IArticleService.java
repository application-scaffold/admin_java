package cn.liaozh.admin_api.service;

import cn.liaozh.admin_api.validate.article.ArticleCreateValidate;
import cn.liaozh.admin_api.validate.article.ArticleSearchValidate;
import cn.liaozh.admin_api.validate.article.ArticleUpdateValidate;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.vo.article.ArticleDetailVo;
import cn.liaozh.admin_api.vo.article.ArticleListedVo;
import cn.liaozh.common.core.PageResult;

/**
 * 文章服务接口类
 */
public interface IArticleService {

    /**
     * 文章列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<ArticleListVo>
     */
    PageResult<ArticleListedVo> list(PageValidate pageValidate, ArticleSearchValidate searchValidate);

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 主键ID
     */
    ArticleDetailVo detail(Integer id);

    /**
     * 文章新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    void add(ArticleCreateValidate createValidate);

    /**
     * 文章编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    void edit(ArticleUpdateValidate updateValidate);

    /**
     * 文章删除
     *
     * @author fzr
     * @param id 文章主键
     */
    void del(Integer id);

    /**
     * 文章状态
     *
     * @author fzr
     * @param id 文章主键
     */
    void change(Integer id);

}
