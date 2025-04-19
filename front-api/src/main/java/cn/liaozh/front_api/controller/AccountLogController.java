package cn.liaozh.front_api.controller;

import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.front_api.LikeFrontThreadLocal;
import cn.liaozh.front_api.service.IUserAccountLogService;
import cn.liaozh.front_api.validate.common.PageValidate;
import cn.liaozh.front_api.validate.users.UserAccountLogSearchValidate;
import cn.liaozh.front_api.vo.user.UserAccountListVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account_log")
@Schema(name = "用户资金变更管理")
public class AccountLogController {

    @Resource
    IUserAccountLogService iUserAccountLogService;

    @GetMapping("/lists")
    @Operation(summary = "用户资金变更列表")
    public AjaxResult<PageResult<UserAccountListVo>> lists(@Validated PageValidate pageValidate,
                                                           @Validated UserAccountLogSearchValidate searchValidate) {
        searchValidate.setUserId(LikeFrontThreadLocal.getUserId());
        PageResult<UserAccountListVo> list = iUserAccountLogService.lists(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }
}
