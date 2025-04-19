package cn.liaozh.admin_api.controller.setting;

import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.ISettingNoticeService;
import cn.liaozh.admin_api.vo.setting.SettingNoticeDetailVo;
import cn.liaozh.admin_api.vo.setting.SettingNoticeListedVo;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminapi/notice.notice")
@Tag(name = "配置消息通知")
public class SettingNoticeController {

    @Resource
    ISettingNoticeService iSettingNoticeService;

    @GetMapping("/settingLists")
    @Operation(summary = "通知设置列表")
    public AjaxResult<JSONObject> list(@RequestParam Integer recipient) {
        List<SettingNoticeListedVo> list = iSettingNoticeService.list(recipient);
        JSONObject result = new JSONObject();
        result.put("lists", list);
        return AjaxResult.success(result);
    }

    @GetMapping("/detail")
    @Operation(summary = "通知设置详情")
    public AjaxResult<SettingNoticeDetailVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SettingNoticeDetailVo vo = iSettingNoticeService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "通知设置编辑")
    @PostMapping("/set")
    @Operation(summary = "通知设置编辑")
    public AjaxResult<Object> save(@RequestBody JSONObject params) {
        iSettingNoticeService.save(params);
        return AjaxResult.success();
    }

}
