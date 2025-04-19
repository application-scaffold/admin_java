package cn.liaozh.admin_api.controller;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.IArticleService;
import cn.liaozh.admin_api.validate.article.ArticleCreateValidate;
import cn.liaozh.admin_api.validate.article.ArticleSearchValidate;
import cn.liaozh.admin_api.validate.article.ArticleUpdateValidate;
import cn.liaozh.admin_api.validate.commons.IdValidate;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.vo.article.ArticleDetailVo;
import cn.liaozh.admin_api.vo.article.ArticleListedVo;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"adminapi/article/article", "adminapi/article.article"})
@Tag(name = "文章数据管理")
public class ArticleController {

    @Resource
    IArticleService iArticleService;

    @GetMapping("/lists")
    @Operation(summary = "文章列表")
    public AjaxResult<PageResult<ArticleListedVo>> list(@Validated PageValidate pageValidate,
                                                        @Validated ArticleSearchValidate searchValidate) {
        PageResult<ArticleListedVo> vos = iArticleService.list(pageValidate, searchValidate);
        return AjaxResult.success(vos);
    }

    @GetMapping("/detail")
    @Operation(summary = "文章详情")
    public AjaxResult<ArticleDetailVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ArticleDetailVo vo = iArticleService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "文章新增")
    @PostMapping("/add")
    @Operation(summary = "文章新增")
    public AjaxResult<Object> add(@Validated @RequestBody ArticleCreateValidate createValidate) {
        iArticleService.add(createValidate);
        return AjaxResult.success();
    }

    @Log(title = "文章编辑")
    @PostMapping("/edit")
    @Operation(summary = "文章编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody ArticleUpdateValidate updateValidate) {
        iArticleService.edit(updateValidate);
        return AjaxResult.success();
    }

    @Log(title = "文章删除")
    @PostMapping("/delete")
    @Operation(summary = "文章删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iArticleService.del(idValidate.getId());
        return AjaxResult.success();
    }

    @Log(title = "文章状态")
    @PostMapping("/updateStatus")
    @Operation(summary = "文章状态")
    public AjaxResult<Object> change(@Validated @RequestBody IdValidate idValidate) {
        iArticleService.change(idValidate.getId());
        return AjaxResult.success();
    }

}
