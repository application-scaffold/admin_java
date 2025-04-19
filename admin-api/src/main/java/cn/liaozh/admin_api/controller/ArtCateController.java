package cn.liaozh.admin_api.controller;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.IArtCateService;
import cn.liaozh.admin_api.validate.article.ArtCateCreateValidate;
import cn.liaozh.admin_api.validate.article.ArtCateSearchValidate;
import cn.liaozh.admin_api.validate.article.ArtCateUpdateValidate;
import cn.liaozh.admin_api.validate.commons.IdValidate;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.vo.article.ArticleCateVo;
import cn.liaozh.common.aop.NotPower;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;

import java.util.List;

@RestController
@RequestMapping({"/adminapi/article/articleCate", "/adminapi/article.articleCate"})
@Tag(name = "文章分类管理")
public class ArtCateController {

    @Resource
    IArtCateService iArtCateService;

    @NotPower
    @GetMapping("/all")
    @Operation(summary = "所有分类")
    public AjaxResult<List<ArticleCateVo>> all() {
        List<ArticleCateVo> list = iArtCateService.all();
        return AjaxResult.success(list);
    }

    @GetMapping("/lists")
    @Operation(summary = "分类列表")
    public AjaxResult<PageResult<ArticleCateVo>> list(@Validated PageValidate pageValidate,
                                                      @Validated ArtCateSearchValidate searchValidate) {
        PageResult<ArticleCateVo> list = iArtCateService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @Operation(summary = "分类详情")
    public AjaxResult<ArticleCateVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ArticleCateVo vo = iArtCateService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "文章分类新增")
    @PostMapping("/add")
    @Operation(summary = "分类新增")
    public AjaxResult<Object> add(@Validated @RequestBody ArtCateCreateValidate createValidate) {
        iArtCateService.add(createValidate);
        return AjaxResult.success();
    }

    @Log(title = "文章分类编辑")
    @PostMapping("/edit")
    @Operation(summary = "分类编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody ArtCateUpdateValidate updateValidate) {
        iArtCateService.edit(updateValidate);
        return AjaxResult.success();
    }

    @Log(title = "文章分类删除")
    @PostMapping("/delete")
    @Operation(summary = "分类删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iArtCateService.del(idValidate.getId());
        return AjaxResult.success();
    }

    @Log(title = "文章分类状态")
    @PostMapping("/updateStatus")
    @Operation(summary = "分类状态")
    public AjaxResult<Object> change(@Validated @RequestBody IdValidate idValidate) {
        iArtCateService.change(idValidate.getId());
        return AjaxResult.success();
    }

}
