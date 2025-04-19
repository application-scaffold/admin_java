package cn.liaozh.admin_api.controller.finance;

import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.LikeAdminThreadLocal;
import cn.liaozh.admin_api.service.IFinanceRechargerService;
import cn.liaozh.admin_api.validate.commons.IdValidate;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.finance.FinanceRechargeSearchValidate;
import cn.liaozh.admin_api.validate.finance.FinanceRefundValidate;
import cn.liaozh.admin_api.vo.finance.FinanceRechargeListVo;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.enums.ErrorEnum;
import cn.liaozh.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping({"/adminapi/recharge/recharge", "/adminapi/recharge.recharge"})
@Tag(name = "充值记录管理")
public class FinanceRechargerController {

    @Resource
    IFinanceRechargerService iFinanceRechargerService;

    @GetMapping("/lists")
    @Operation(summary = "充值记录")
    public AjaxResult<Object> list(@Validated PageValidate pageValidate,
                                   @Validated FinanceRechargeSearchValidate searchValidate) {
        if (StringUtils.isNotNull(searchValidate.getExport()) && searchValidate.getExport().equals(1)) {
            JSONObject ret = iFinanceRechargerService.getExportData(pageValidate, searchValidate);
            return AjaxResult.success(ret);
        } else if (StringUtils.isNotNull(searchValidate.getExport()) && searchValidate.getExport().equals(2)) {
            String path = iFinanceRechargerService.export(searchValidate);
            return AjaxResult.success(2, new JSONObject() {{
                put("url", path);
            }}, ErrorEnum.SHOW_MSG.getCode());
        } else {
            PageResult<FinanceRechargeListVo> list = iFinanceRechargerService.list(pageValidate, searchValidate);
            return AjaxResult.success(list);
        }
    }

    @PostMapping("/refund")
    @Operation(summary = "发起退款")
    public AjaxResult<Object> refund(@Validated @RequestBody FinanceRefundValidate financeRefundValidate) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();

        iFinanceRechargerService.refund(financeRefundValidate.getRecharge_id(), adminId);
        return AjaxResult.success();
    }

    @PostMapping("/refundAgain")
    @Schema(name = "重新退款")
    public AjaxResult<Object> refundAgain(@Validated @RequestBody IdValidate idValidate) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();

        iFinanceRechargerService.refundAgain(idValidate.getId(), adminId);
        return AjaxResult.success();
    }

}
