package cn.liaozh.front_api.controller;

import cn.liaozh.common.aop.NotLogin;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.front_api.service.IWechatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("api/wechat")
@Schema(name = "微信管理")
public class WechatController {

    @Resource
    IWechatService iWechatService;

    @NotLogin
    @GetMapping("/jsConfig")
    @Operation(summary = "微信jsConfig")
    public AjaxResult<Object> jsConfig(@Validated @NotEmpty() @RequestParam("url") String url) throws Exception {
        Map<String, Object> map = iWechatService.jsConfig(url);
        return AjaxResult.success(map);
    }

}
