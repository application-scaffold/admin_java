package cn.liaozh.admin_api.controller;

import cn.liaozh.admin_api.service.IIndexService;
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
@RequestMapping("/adminapi/workbench")
@Tag(name = "主页数据管理")
public class IndexController {

    @Resource
    IIndexService iIndexService;

    @GetMapping("/index")
    @Operation(summary = "控制台")
    public AjaxResult<Map<String, Object>> index() {
        Map<String, Object> map = iIndexService.index();
        return AjaxResult.success(map);
    }

}
