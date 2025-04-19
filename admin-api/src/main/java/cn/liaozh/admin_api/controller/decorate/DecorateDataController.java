package cn.liaozh.admin_api.controller.decorate;

import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.service.IDecorateDataService;
import cn.liaozh.admin_api.vo.decorate.DecorateDataArticleVo;
import cn.liaozh.common.aop.NotPower;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adminapi/decorate.data")
@Tag(name = "装修数据管理")
public class DecorateDataController {

    @Resource
    IDecorateDataService iDecorateDataService;

    @NotPower
    @GetMapping("/article")
    @Operation(summary = "获取文章数据")
    public AjaxResult<List<DecorateDataArticleVo>> article(@RequestParam(defaultValue = "10") Integer limit) {
        List<DecorateDataArticleVo> list = iDecorateDataService.article(limit);
        return AjaxResult.success(list);
    }


    @GetMapping("/pc")
    @Operation(summary = "获取pc装修数据")
    public AjaxResult<JSONObject> pc() {
        JSONObject pc = iDecorateDataService.pc();
        return AjaxResult.success(pc);
    }

}
