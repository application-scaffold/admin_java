package cn.liaozh.admin_api.controller.channel;

import cn.liaozh.admin_api.service.IChannelOpService;
import cn.liaozh.admin_api.validate.channel.ChannelOpValidate;
import cn.liaozh.admin_api.vo.channel.ChannelOpVo;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminapi/channel.open_setting/")
@Tag(name = "微信开放渠道")
public class ChannelOpController {

    @Resource
    IChannelOpService iChannelOpService;

    @GetMapping("/getConfig")
    @Operation(summary = "开放平台设置详情")
    public AjaxResult<Object> getConfig() {
        ChannelOpVo vo = iChannelOpService.getConfig();
        return AjaxResult.success(vo);
    }

    @PostMapping("/setConfig")
    @Operation(summary = "开放平台设置保存")
    public AjaxResult<Object> save(@Validated @RequestBody ChannelOpValidate opValidate) {
        iChannelOpService.setConfig(opValidate);
        return AjaxResult.success();
    }

}
