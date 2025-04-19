package cn.liaozh.admin_api.controller.decorate;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.IDecorateTabbarService;
import cn.liaozh.admin_api.validate.decorate.DecorateTabsValidate;
import cn.liaozh.admin_api.vo.decorate.DecorateTabbarVo;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminapi/decorate.tabbar")
@Tag(name = "装修导航管理")
public class DecorateTabbarController {

    @Resource
    IDecorateTabbarService iDecorateTabbarService;

    @GetMapping("/detail")
    @Operation(summary = "底部导航详情")
    public AjaxResult<DecorateTabbarVo> detail() {
        DecorateTabbarVo vo = iDecorateTabbarService.detail();
        return AjaxResult.success(vo);
    }

    @Log(title = "底部导航编辑")
    @PostMapping("/save")
    @Operation(summary = "底部导航编辑")
    public AjaxResult<Object> save(@Validated @RequestBody DecorateTabsValidate tabsValidate) {
        iDecorateTabbarService.save(tabsValidate);
        return AjaxResult.success();
    }

}
