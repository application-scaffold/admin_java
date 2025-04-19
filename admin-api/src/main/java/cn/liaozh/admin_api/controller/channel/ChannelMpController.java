package cn.liaozh.admin_api.controller.channel;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.IChannelMpConfigService;
import cn.liaozh.admin_api.validate.channel.ChannelMpValidate;
import cn.liaozh.admin_api.vo.channel.ChannelMpVo;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminapi/channel.mnp_settings")
@Tag(name = "微信程序渠道")
public class ChannelMpController {

    @Resource
    IChannelMpConfigService iChannelMpConfigService;

    @GetMapping("/getConfig")
    @Operation(summary = "微信程序渠道设置详情")
    public AjaxResult<ChannelMpVo> getConfig() {
        ChannelMpVo vo = iChannelMpConfigService.getConfig();
        return AjaxResult.success(vo);
    }

    @Log(title = "微信小程序渠道设置保存")
    @PostMapping("/setConfig")
    @Operation(summary = "微信程序渠道设置保存")
    public AjaxResult<Object> setConfig(@Validated @RequestBody ChannelMpValidate channelMpValidate) {
        iChannelMpConfigService.setConfig(channelMpValidate);
        return AjaxResult.success();
    }

}
