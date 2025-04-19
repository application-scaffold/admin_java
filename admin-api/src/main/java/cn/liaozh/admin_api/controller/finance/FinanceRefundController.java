package cn.liaozh.admin_api.controller.finance;

import cn.liaozh.admin_api.service.IFinanceRefundService;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.finance.FinanceRefundSearchValidate;
import cn.liaozh.admin_api.vo.finance.FinanceRefundListVo;
import cn.liaozh.admin_api.vo.finance.FinanceRefundLogVo;
import cn.liaozh.common.aop.NotPower;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adminapi/finance.refund")
@Tag(name = "退款记录管理")
public class FinanceRefundController {

    @Resource
    IFinanceRefundService iFinanceRefundService;

    @NotPower
    @GetMapping("/stat")
    @Operation(summary = "退还统计")
    public AjaxResult<Object> stat() {
        return AjaxResult.success(iFinanceRefundService.stat());
    }

    @GetMapping("/record")
    @Operation(summary = "退款记录")
    public AjaxResult<Object> list(@Validated PageValidate pageValidate,
                                   @Validated FinanceRefundSearchValidate searchValidate) {
        PageResult<FinanceRefundListVo> list = iFinanceRefundService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    @GetMapping("/log")
    @Operation(summary = "退款日志")
    public AjaxResult<Object> log(@Validated @IDMust() @RequestParam("record_id") Integer id) {
        List<FinanceRefundLogVo> list = iFinanceRefundService.log(id);
        return AjaxResult.success(list);
    }

}
