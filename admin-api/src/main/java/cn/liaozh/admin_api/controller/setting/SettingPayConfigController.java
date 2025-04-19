package cn.liaozh.admin_api.controller.setting;


import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.service.ISettingPaymentService;
import cn.liaozh.admin_api.validate.setting.SettingPayConfigValidate;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.entity.setting.DevPayConfig;
import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminapi/setting.pay.pay_config")
@Tag(name = "配置支付参数")
public class SettingPayConfigController {

    @Resource
    ISettingPaymentService iSettingPaymentService;

    @GetMapping("/lists")
    @Operation(summary = "支付配置列表")
    public AjaxResult<JSONObject> list() {
        JSONObject result = iSettingPaymentService.list();
        return AjaxResult.success(result);
    }

    @GetMapping("/getConfig")
    @Operation(summary = "支付配置详情")
    public AjaxResult<Object> getConfig(@Validated @IDMust() @RequestParam("id") Integer id) {
        DevPayConfig vo = iSettingPaymentService.getConfig(id);
        return AjaxResult.success(vo);
    }

    @PostMapping("/setConfig")
    @Operation(summary = "支付配置编辑")
    public AjaxResult<Object> setConfig(@Validated @RequestBody SettingPayConfigValidate configValidate) {
        iSettingPaymentService.setConfig(configValidate);
        return AjaxResult.success();
    }


}
