package cn.liaozh.admin_api.controller.channel;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.IChannelH5ConfigService;
import cn.liaozh.admin_api.validate.channel.ChannelH5Validate;
import cn.liaozh.admin_api.vo.channel.ChannelH5Vo;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminapi/channel.web_page_setting")
@Tag(name = "移动渠道设置")
public class ChannelH5Controller {

    @Resource
    IChannelH5ConfigService iChannelH5ConfigService;

    @GetMapping("/getConfig")
    @Operation(summary = "H5渠道设置详情")
    public AjaxResult<ChannelH5Vo> getConfig() {
        ChannelH5Vo vo = iChannelH5ConfigService.getConfig();
        return AjaxResult.success(vo);
    }

    @Log(title = "H5渠道设置保存")
    @PostMapping("/setConfig")
    @Operation(summary = "H5渠道设置保存")
    public AjaxResult<Object> setConfig(@Validated @RequestBody ChannelH5Validate channelH5Validate) {
        iChannelH5ConfigService.setConfig(channelH5Validate);
        return AjaxResult.success();
    }

}
