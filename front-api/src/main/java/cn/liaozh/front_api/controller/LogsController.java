package cn.liaozh.front_api.controller;

import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.front_api.LikeFrontThreadLocal;
import cn.liaozh.front_api.service.ILogsService;
import cn.liaozh.front_api.validate.common.PageValidate;
import cn.liaozh.front_api.vo.RechargeRecordVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/api/logs")
@Schema(name = "日志管理")
public class LogsController {

    @Resource
    ILogsService iLogsService;

    @GetMapping("/userMoney")
    @Operation(summary = "用户金额变动日志")
    public AjaxResult<Object> userMoney(@Validated PageValidate pageValidate,
                                        @RequestParam(defaultValue = "0") Integer type) {

        Integer userId = LikeFrontThreadLocal.getUserId();

        PageResult<RechargeRecordVo> list = iLogsService.userMoney(pageValidate, userId, type);
        return AjaxResult.success(list);
    }

}
