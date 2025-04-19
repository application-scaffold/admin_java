package cn.liaozh.admin_api.controller.channel;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.IChannelOaConfigService;
import cn.liaozh.admin_api.validate.channel.ChannelOaValidate;
import cn.liaozh.admin_api.vo.channel.ChannelOaVo;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/adminapi/channel.official_account_setting")
@Tag(name = "公众号渠道设置")
public class ChannelOaController {

    @Resource
    IChannelOaConfigService iChannelOaConfigService;

    @GetMapping("/getConfig")
    @Operation(summary = "公众号渠道设置详情")
    public AjaxResult<ChannelOaVo> getConfig() {
        ChannelOaVo vo = iChannelOaConfigService.getConfig();
        return AjaxResult.success(vo);
    }

    @Log(title = "公众号渠道设置保存")
    @PostMapping("/setConfig")
    @Operation(summary = "公众号渠道设置保存")
    public AjaxResult<Object> setConfig(@Validated @RequestBody ChannelOaValidate channelOaValidate) {
        iChannelOaConfigService.setConfig(channelOaValidate);
        return AjaxResult.success();
    }

}
