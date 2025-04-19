package cn.liaozh.front_api.controller;

import cn.liaozh.common.aop.NotLogin;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.validator.annotation.IDMust;
import cn.liaozh.front_api.LikeFrontThreadLocal;
import cn.liaozh.front_api.service.IArticleService;
import cn.liaozh.front_api.validate.article.ArticleCollectValidate;
import cn.liaozh.front_api.validate.article.ArticleSearchValidate;
import cn.liaozh.front_api.validate.common.PageValidate;
import cn.liaozh.front_api.vo.article.ArticleCateVo;
import cn.liaozh.front_api.vo.article.ArticleCollectVo;
import cn.liaozh.front_api.vo.article.ArticleDetailVo;
import cn.liaozh.front_api.vo.article.ArticleListedVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
@Schema(name = "文章管理")
public class ArticleController {

    @Resource
    IArticleService iArticleService;

    @NotLogin
    @GetMapping("/cate")
    @Operation(summary = "文章分类")
    public AjaxResult<List<ArticleCateVo>> category() {
        List<ArticleCateVo> list = iArticleService.category();
        return AjaxResult.success(list);
    }

    @NotLogin
    @GetMapping("/lists")
    @Operation(summary = "文章列表")
    public AjaxResult<PageResult<ArticleListedVo>> lists(@Validated PageValidate pageValidate,
                                                        @Validated ArticleSearchValidate searchValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        PageResult<ArticleListedVo> list = iArticleService.list(userId, pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    @NotLogin
    @GetMapping("/detail")
    @Operation(summary = "文章详情")
    public AjaxResult<ArticleDetailVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        ArticleDetailVo vo = iArticleService.detail(id, userId);
        return AjaxResult.success(vo);
    }

    @GetMapping("/collect")
    @Operation(summary = "收藏列表")
    public AjaxResult<PageResult<ArticleCollectVo>> collect(@Validated PageValidate pageValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        PageResult<ArticleCollectVo> list = iArticleService.collect(pageValidate, userId);
        return AjaxResult.success(list);
    }

    @PostMapping("/addCollect")
    @Operation(summary = "收藏加入")
    public AjaxResult<Object> addCollect(@Validated @RequestBody ArticleCollectValidate collectValidate) {
        Integer articleId = collectValidate.getId();
        Integer userId = LikeFrontThreadLocal.getUserId();

        iArticleService.addCollect(articleId, userId);
        return AjaxResult.success();
    }

    @PostMapping("/cancelCollect")
    @Operation(summary = "收藏取消")
    public AjaxResult<Object> cancelCollect(@Validated @RequestBody ArticleCollectValidate collectValidate) {
        Integer articleId = collectValidate.getId();
        Integer userId = LikeFrontThreadLocal.getUserId();

        iArticleService.cancelCollect(articleId, userId);
        return AjaxResult.success();
    }

}
