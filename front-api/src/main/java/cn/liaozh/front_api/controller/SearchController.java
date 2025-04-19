package cn.liaozh.front_api.controller;

import com.alibaba.fastjson.JSONObject;
import cn.liaozh.common.aop.NotLogin;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.front_api.service.ISearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/api/search")
@Schema(name = "搜索")
public class SearchController {


    @Resource
    ISearchService iSearchService;

    @NotLogin
    @GetMapping("/hotLists")
    @Operation(summary = "搜索列表")
    public AjaxResult<JSONObject> hotLists() {
        JSONObject result = iSearchService.hotLists();
        return AjaxResult.success(result);
    }

}
