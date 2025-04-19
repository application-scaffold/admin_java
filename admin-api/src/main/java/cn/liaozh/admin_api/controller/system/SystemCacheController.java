package cn.liaozh.admin_api.controller.system;


import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.system.ISystemCacheService;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adminapi/setting.system.cache")
@Tag(name = "系统缓存管理")
public class SystemCacheController {

    @Resource
    ISystemCacheService iSystemCacheService;

    @Log(title = "清除系统缓存")
    @PostMapping("/clear")
    @Operation(summary = "清除系统缓存")
    public AjaxResult<Object> clear() {
        iSystemCacheService.clear();
        return AjaxResult.success();
    }
}
