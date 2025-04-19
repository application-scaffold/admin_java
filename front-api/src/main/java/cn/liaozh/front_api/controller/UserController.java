package cn.liaozh.front_api.controller;

import cn.liaozh.common.aop.NotLogin;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.exception.OperateException;
import cn.liaozh.front_api.LikeFrontThreadLocal;
import cn.liaozh.front_api.service.IUserService;
import cn.liaozh.front_api.validate.users.*;
import cn.liaozh.front_api.vo.user.UserCenterVo;
import cn.liaozh.front_api.vo.user.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@Schema(name = "用户管理")
public class UserController {

    @Resource
    IUserService iUserService;

    @GetMapping("/center")
    @Operation(summary = "个人中心")
    public AjaxResult<UserCenterVo> center() {
        Integer userId = LikeFrontThreadLocal.getUserId();
        Integer terminal = LikeFrontThreadLocal.getTerminal();

        UserCenterVo vo = iUserService.center(userId, terminal);
        return AjaxResult.success(vo);
    }

    @GetMapping("/info")
    @Operation(summary = "个人信息")
    public AjaxResult<UserInfoVo> info() {
        Integer userId = LikeFrontThreadLocal.getUserId();

        UserInfoVo vo = iUserService.info(userId);
        return AjaxResult.success(vo);
    }

    @PostMapping("/setInfo")
    @Operation(summary = "编辑信息")
    public AjaxResult<Object> setInfo(@Validated @RequestBody UserUpdateValidate updateValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        iUserService.setInfo(updateValidate, userId);
        return AjaxResult.success();
    }

    @PostMapping("/changePassword")
    @Operation(summary = "修改密码")
    public AjaxResult<Object> changePassword(@Validated @RequestBody UserChangePwdValidate passwordValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        if (passwordValidate.getPassword().equals(passwordValidate.getPasswordConfirm()) == false) {
            throw new OperateException("两次输入的密码不一致");
        }
        iUserService.changePwd(passwordValidate.getPassword(), passwordValidate.getOldPassword(), userId);
        return AjaxResult.success();
    }

    @NotLogin
    @PostMapping("/resetPassword")
    @Operation(summary = "重置密码")
    public AjaxResult<Object> resetPassword(@Validated @RequestBody ResetPasswordValidate passwordValidate) {
        iUserService.resetPassword(passwordValidate);
        return AjaxResult.success();
    }



    @NotLogin
    @PostMapping("/forgotPwd")
    @Operation(summary = "忘记密码")
    public AjaxResult<Object> forgotPwd(@Validated @RequestBody UserForgetPwdValidate userForgetPwdValidate) {
        String password = userForgetPwdValidate.getPassword();
        String mobile = userForgetPwdValidate.getMobile();
        String code = userForgetPwdValidate.getCode();

        iUserService.forgotPwd(password, mobile, code);
        return AjaxResult.success();
    }

    @PostMapping("/bindMobile")
    @Operation(summary = "绑定手机")
    public AjaxResult<Object> bindMobile(@Validated @RequestBody UserPhoneBindValidate mobileValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        iUserService.bindMobile(mobileValidate, userId);
        return AjaxResult.success();
    }

    @PostMapping("/getMobileByMnp")
    @Operation(summary = "微信手机号")
    public AjaxResult<Object> mnpMobile(@Validated @RequestBody UserPhoneMnpValidate mobileValidate) {
        iUserService.mnpMobile(mobileValidate.getCode().trim());
        return AjaxResult.success();
    }

    @PostMapping("/updateUser")
    @Operation(summary = "更新新用户信息")
    public AjaxResult<Object> updateData(@Validated @RequestBody NewUserUpdateValidate newUserUpdateValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();
        iUserService.updateNewUserInfo(newUserUpdateValidate, userId);
        return AjaxResult.success();
    }

    @PostMapping("/bindMnp")
    @Operation(summary = "绑定小程序")
    public AjaxResult<Object> bindMnp(@Validated @RequestBody UserBindWechatValidate BindMnpValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        iUserService.bindMnp(BindMnpValidate, userId);
        return AjaxResult.success();
    }

    @PostMapping("/bindOa")
    @Operation(summary = "绑定微信公众号")
    public AjaxResult<Object> bindOa(@Validated @RequestBody UserBindWechatValidate BindOaValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        iUserService.bindOa(BindOaValidate, userId);
        return AjaxResult.success();
    }

}
