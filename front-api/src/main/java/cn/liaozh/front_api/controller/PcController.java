package cn.liaozh.front_api.controller;

import cn.liaozh.common.aop.NotLogin;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.validator.annotation.IDMust;
import cn.liaozh.front_api.LikeFrontThreadLocal;
import cn.liaozh.front_api.service.IPcService;
import cn.liaozh.front_api.vo.article.PcArticleCenterVo;
import cn.liaozh.front_api.vo.article.PcArticleDetailVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pc")
@Schema(name = "电脑管理")
public class PcController {

    @Resource
    IPcService iPcService;

    @NotLogin
    @GetMapping("/index")
    @Operation(summary = "主页数据")
    public AjaxResult<Map<String,Object>> index() {
        Map<String, Object> index = iPcService.index();
        return AjaxResult.success(index);
    }

    @NotLogin
    @GetMapping("/config")
    @Operation(summary = "公共配置")
    public AjaxResult<Map<String, Object>> getConfig() {
        Map<String, Object> config = iPcService.getConfig();
        return AjaxResult.success(config);
    }

    @NotLogin
    @GetMapping("/infoCenter")
    @Operation(summary = "资讯中心")
    public AjaxResult<List<PcArticleCenterVo>> infoCenter() {
        List<PcArticleCenterVo> list = iPcService.infoCenter();
        return AjaxResult.success(list);
    }

    @NotLogin
    @GetMapping("/articleDetail")
    @Operation(summary = "文章详情")
    public AjaxResult<PcArticleDetailVo> articleDetail(@Validated @IDMust() @RequestParam("id") Integer id) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        PcArticleDetailVo vo = iPcService.articleDetail(id, userId);
        return AjaxResult.success(vo);
    }

}
