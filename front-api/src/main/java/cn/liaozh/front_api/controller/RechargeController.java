package cn.liaozh.front_api.controller;

import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.front_api.LikeFrontThreadLocal;
import cn.liaozh.front_api.service.IRechargeService;
import cn.liaozh.front_api.validate.RechargeValidate;
import cn.liaozh.front_api.validate.common.PageValidate;
import cn.liaozh.front_api.vo.RechargeConfigVo;
import cn.liaozh.front_api.vo.RechargeRecordVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/recharge")
@Schema(name = "充值管理")
public class RechargeController {

    @Resource
    IRechargeService iRechargeService;

    @GetMapping("/config")
    @Operation(summary = "充值配置")
    public AjaxResult<Object> config() {
        Integer userId = LikeFrontThreadLocal.getUserId();

        RechargeConfigVo vo = iRechargeService.config(userId);
        return AjaxResult.success(vo);
    }

    @GetMapping("/lists")
    @Operation(summary = "充值记录")
    public AjaxResult<Object> lists(@Validated PageValidate pageValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        PageResult<RechargeRecordVo> list = iRechargeService.record(userId, pageValidate);
        return AjaxResult.success(list);
    }

    @PostMapping("/recharge")
    @Operation(summary = "充值下单")
    public AjaxResult<Object> recharge(@Validated @RequestBody RechargeValidate rechargeValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();
        Integer terminal = LikeFrontThreadLocal.getTerminal();

        Map<String, Object> result = iRechargeService.placeOrder(userId, terminal, rechargeValidate);
        return AjaxResult.success(result);
    }

}
