package cn.liaozh.admin_api.controller.channel;

import com.alibaba.fastjson2.JSONArray;
import cn.liaozh.admin_api.service.IChannelOaMenusService;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminapi/channel.official_account_menu")
@Tag(name = "公众号菜单管理")
public class ChannelOaMenuController {

    @Resource
    IChannelOaMenusService iChannelOaMenusService;

    @GetMapping("/detail")
    @Operation(summary = "菜单详情")
    public AjaxResult<JSONArray> detail() {
        JSONArray detail = iChannelOaMenusService.detail();
        return AjaxResult.success(detail);
    }

    @PostMapping("/save")
    @Operation(summary = "仅是保存菜单")
    public AjaxResult<Object> save(@RequestBody List<Object> params) {
        iChannelOaMenusService.save(params, false);
        return AjaxResult.success();
    }


    @PostMapping("/saveAndPublish")
    @Operation(summary = "saveAndPublish")
    public AjaxResult<Object> publish(@RequestBody List<Object> params) {
        iChannelOaMenusService.save(params, true);
        return AjaxResult.success();
    }

}
