package cn.liaozh.admin_api.controller.setting;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.ISettingSearchService;
import cn.liaozh.admin_api.validate.setting.SettingSearchValidate;
import cn.liaozh.admin_api.vo.setting.SettingSearchDetailVo;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminapi/setting.hot_search")
@Tag(name = "配置热门搜索")
public class SettingSearchController {

    @Resource
    ISettingSearchService iSettingSearchService;

    @GetMapping("/getConfig")
    @Operation(summary = "热门搜索详情")
    public AjaxResult<SettingSearchDetailVo> getConfig() {
        SettingSearchDetailVo vo = iSettingSearchService.getConfig();
        return AjaxResult.success(vo);
    }

    @Log(title = "热门搜索编辑")
    @PostMapping("/setConfig")
    @Operation(summary = "热门搜索编辑")
    public AjaxResult<Object> setConfig(@Validated @RequestBody SettingSearchValidate searchValidate) {
        iSettingSearchService.setConfig(searchValidate);
        return AjaxResult.success("操作成功");
    }

}
