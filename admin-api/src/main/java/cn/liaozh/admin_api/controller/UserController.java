package cn.liaozh.admin_api.controller;

import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.IUserService;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.user.UserSearchValidate;
import cn.liaozh.admin_api.validate.user.UserUpdateValidate;
import cn.liaozh.admin_api.validate.user.UserWalletValidate;
import cn.liaozh.admin_api.vo.user.UserVo;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.enums.ErrorEnum;
import cn.liaozh.common.util.StringUtils;
import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"adminapi/user/user", "adminapi/user.user"})
@Tag(name = "用户数据管理")
public class UserController {

    @Resource
    IUserService iUserService;

    @GetMapping("/lists")
    @Operation(summary = "用户列表")
    public AjaxResult<Object> list(@Validated PageValidate pageValidate,
                                               @Validated UserSearchValidate searchValidate) {
        if (StringUtils.isNotNull(searchValidate.getExport()) && searchValidate.getExport().equals(1)) {
            JSONObject ret = iUserService.getExportData(pageValidate, searchValidate);
            return AjaxResult.success(ret);
        } else if (StringUtils.isNotNull(searchValidate.getExport()) && searchValidate.getExport().equals(2)) {
            String path = iUserService.export(searchValidate);
            return AjaxResult.success(2, new JSONObject() {{
                put("url", path);
            }}, ErrorEnum.SHOW_MSG.getCode());
        } else {
            PageResult<UserVo> list = iUserService.list(pageValidate, searchValidate);
            return AjaxResult.success(list);
        }
    }

    @GetMapping("/detail")
    @Operation(summary = "用户详情")
    public AjaxResult<UserVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        UserVo vo = iUserService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "用户编辑")
    @PostMapping("/edit")
    @Operation(summary = "用户编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody UserUpdateValidate updateValidate) {
        iUserService.edit(updateValidate);
        return AjaxResult.success();
    }

    @Log(title = "余额调整")
    @PostMapping("/adjustMoney")
    @Operation(summary = "余额调整")
    public AjaxResult<Object> adjustWallet(@Validated @RequestBody UserWalletValidate walletValidate) {
        iUserService.adjustWallet(walletValidate);
        return AjaxResult.success();
    }

}
