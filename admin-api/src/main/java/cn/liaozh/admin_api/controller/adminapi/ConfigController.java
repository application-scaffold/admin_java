package cn.liaozh.admin_api.controller.adminapi;

import cn.liaozh.admin_api.service.IIndexService;
import cn.liaozh.common.aop.NotLogin;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("adminapi/config")
@Tag(name = "主页数据管理")
public class ConfigController {

    @Resource
    IIndexService iIndexService;
    @NotLogin
    @GetMapping("/getConfig")
    @Operation(summary = "公共配置")
    public AjaxResult<Map<String, Object>> getConfig() {
        Map<String, Object> map = iIndexService.config();
        return AjaxResult.success(map);
    }

}
