package cn.liaozh.admin_api.controller.decorate;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.IDecoratePageService;
import cn.liaozh.admin_api.validate.decorate.DecoratePageValidate;
import cn.liaozh.admin_api.vo.decorate.DecoratePageVo;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminapi/decorate.page")
@Tag(name = "装修页面管理")
public class DecoratePagesController {

    @Resource
    IDecoratePageService iDecoratePageService;

    @GetMapping("/detail")
    @Operation(summary = "页面装修详情")
    public AjaxResult<DecoratePageVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        DecoratePageVo vo = iDecoratePageService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "页面装修保存")
    @PostMapping("/save")
    @Operation(summary = "页面装修保存")
    public AjaxResult<Object> save(@RequestBody DecoratePageValidate decoratePageValidate) {
        iDecoratePageService.save(decoratePageValidate);
        return AjaxResult.success();
    }

}
