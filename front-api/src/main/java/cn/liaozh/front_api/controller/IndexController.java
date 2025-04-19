package cn.liaozh.front_api.controller;

import cn.liaozh.common.aop.NotLogin;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.mapper.notice.NoticeRecordMapper;
import cn.liaozh.common.validator.annotation.IDMust;
import cn.liaozh.front_api.service.IIndexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/index")
@Schema(name = "主页管理")
public class IndexController {

    @Resource
    NoticeRecordMapper noticeRecordMapper;

    @Resource
    IIndexService iIndexService;

    @NotLogin
    @GetMapping("/index")
    @Operation(summary = "首页数据")
    public AjaxResult<Map<String, Object>> index() {
        Map<String, Object> detail = iIndexService.index();
        return AjaxResult.success(detail);
    }

    @NotLogin
    @GetMapping("/decorate")
    @Operation(summary = "装修数据")
    public AjaxResult<Map<String, Object>> decorate(@Validated @IDMust() @RequestParam("id") Integer id) {
        Map<String, Object> detail = iIndexService.decorate(id);
        return AjaxResult.success(detail);
    }

    @NotLogin
    @GetMapping("/config")
    @Operation(summary = "公共配置")
    public AjaxResult<Map<String, Object>> config() {
        Map<String, Object> map = iIndexService.config();
        return AjaxResult.success(map);
    }

    @NotLogin
    @GetMapping("/policy")
    @Operation(summary = "政策协议")
    public AjaxResult<Map<String, String>> policy(@RequestParam String type) {
        Map<String, String> map = iIndexService.policy(type);
        return AjaxResult.success(map);
    }

    @NotLogin
    @GetMapping("/hotSearch")
    @Operation(summary = "热门搜索")
    public AjaxResult<List<String>> hotSearch() {
        List<String> list = iIndexService.hotSearch();
        return AjaxResult.success(list);
    }
}
