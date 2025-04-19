package cn.liaozh.admin_api.controller.setting;


import cn.liaozh.admin_api.service.ISettingPaymentService;
import cn.liaozh.admin_api.vo.setting.SettingPaymentMethodVo;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/adminapi/setting.pay.pay_way")
@Tag(name = "配置支付参数")
public class SettingPayWayController {

    @Resource
    ISettingPaymentService iSettingPaymentService;

    @GetMapping("/getPayWay")
    @Operation(summary = "支付方式列表")
    public AjaxResult<HashMap> method() {
        HashMap list = iSettingPaymentService.getPayWay();
        return AjaxResult.success(list);
    }

    @PostMapping("/setPayWay")
    @Operation(summary = "支付方式编辑")
    public AjaxResult<Object> setPayWay(@Validated @RequestBody HashMap<Integer, List<SettingPaymentMethodVo>> data) {
        iSettingPaymentService.setPayWay(data);
        return AjaxResult.success();
    }

}
