package cn.liaozh.admin_api.controller.marketing;

import cn.liaozh.admin_api.service.IMarketingRechargeService;
import cn.liaozh.admin_api.validate.marketing.MarketingRechargeValidate;
import cn.liaozh.admin_api.vo.marketing.MarketingRechargeVo;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"adminapi/recharge/recharge", "adminapi/recharge.recharge"})
@Tag(name = "营销充值管理")
public class MarketingRechargeController {

    @Resource
    IMarketingRechargeService iMarketingRechargeService;

    @GetMapping("/getConfig")
    @Operation(summary = "充值配置详情")
    public AjaxResult<MarketingRechargeVo> detail() {
        MarketingRechargeVo vo = iMarketingRechargeService.detail();
        return AjaxResult.success(vo);
    }

    @PostMapping("/setConfig")
    @Operation(summary = "充值配置保存")
    public AjaxResult<Object> save(@Validated @RequestBody MarketingRechargeValidate rechargeValidate) {
        iMarketingRechargeService.save(rechargeValidate);
        return AjaxResult.success();
    }

}
