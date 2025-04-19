package cn.liaozh.admin_api.controller.finance;

import cn.liaozh.admin_api.service.IFinanceWalletService;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.finance.FinanceWalletSearchValidate;
import cn.liaozh.admin_api.vo.finance.FinanceWalletListVo;
import cn.liaozh.common.aop.NotPower;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/adminapi/finance.account_log")
@Tag(name = "余额明细管理")
public class FinanceWalletController {

    @Resource
    IFinanceWalletService iFinanceWalletService;

    @GetMapping("/lists")
    @Operation(summary = "记录列表")
    public AjaxResult<Object> list(@Validated PageValidate pageValidate,
                                   @Validated FinanceWalletSearchValidate searchValidate) {
        PageResult<FinanceWalletListVo> list = iFinanceWalletService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }


    @NotPower
    @GetMapping("/getUmChangeType")
    @Operation(summary = "记录列表")
    public AjaxResult<Map<Integer, String>> getUmChangeType() {
        return AjaxResult.success(iFinanceWalletService.getUmChangeType());
    }

}
