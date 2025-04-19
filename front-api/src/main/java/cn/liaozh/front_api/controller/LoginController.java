package cn.liaozh.front_api.controller;

import cn.liaozh.common.aop.NotLogin;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.enums.ClientEnum;
import cn.liaozh.common.util.StringUtils;
import cn.liaozh.front_api.LikeFrontThreadLocal;
import cn.liaozh.front_api.service.ILoginService;
import cn.liaozh.front_api.service.IUserService;
import cn.liaozh.front_api.validate.login.*;
import cn.liaozh.front_api.validate.users.UserBindWechatValidate;
import cn.liaozh.front_api.vo.login.LoginTokenVo;
import cn.liaozh.front_api.vo.login.LoginUrlsVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/login")
@Schema(name = "登录管理")
public class LoginController {

    @Resource
    ILoginService iLoginService;
    @Resource
    IUserService iUserService;

    @NotLogin
    @PostMapping("/register")
    @Operation(summary = "注册账号")
    public AjaxResult<Object> register(@Validated @RequestBody RegisterValidate registerValidate) {
        Integer terminal = LikeFrontThreadLocal.getTerminal();
        if (StringUtils.isNull(registerValidate.getTerminal())) {
            terminal = LikeFrontThreadLocal.getTerminal();
        }
        String account = registerValidate.getAccount();
        String password = registerValidate.getPassword();

        iLoginService.register(account, password, terminal);
        return AjaxResult.success();
    }

    @NotLogin
    @PostMapping("/account")
    @Operation(summary = "账号登录")
    public AjaxResult<LoginTokenVo> account(@Validated @RequestBody LoginPwdValidate loginPwdValidate) {
        if (StringUtils.isNull(loginPwdValidate.getTerminal())) {
            loginPwdValidate.setTerminal(LikeFrontThreadLocal.getTerminal());
        }
        LoginTokenVo vo = iLoginService.accountLogin(loginPwdValidate);
        return AjaxResult.success(vo);
    }

//    @NotLogin
//    @PostMapping("/mobileLogin")
//    @ApiOperation(value="手机登录")
//    public AjaxResult<LoginTokenVo> mobileLogin(@Validated @RequestBody LoginPhoneValidate loginPhoneValidate) {
//        Integer terminal = LikeFrontThreadLocal.getTerminal();
//        String mobile = loginPhoneValidate.getMobile();
//        String code = loginPhoneValidate.getCode();
//
//        LoginTokenVo vo = iLoginService.mobileLogin(mobile, code, terminal);
//        return AjaxResult.success(vo);
//    }

    @NotLogin
    @PostMapping("/mnpLogin")
    @Operation(summary = "微信登录")
    public AjaxResult<LoginTokenVo> mnpLogin(@Validated @RequestBody LoginCodeValidate loginCodeValidate) {
        Integer terminal = ClientEnum.MNP.getCode();

        String code = loginCodeValidate.getCode();

        LoginTokenVo vo = iLoginService.mnpLogin(code, terminal);
        return AjaxResult.success(vo);
    }

    @NotLogin
    @PostMapping("/oaLogin")
    @Operation(summary = "公众号登录")
    public AjaxResult<LoginTokenVo> oaLogin(@Validated @RequestBody LoginCodeValidate loginCodeValidate) {
        Integer terminal = ClientEnum.OA.getCode();
        String code = loginCodeValidate.getCode();
//        if (StringUtils.isNull(loginCodeValidate.getTerminal())) {
//            terminal = LikeFrontThreadLocal.getTerminal();
//        }
        LoginTokenVo vo = iLoginService.officeLogin(code, terminal);
        return AjaxResult.success(vo);
    }
    @NotLogin
    @GetMapping("/codeUrl")
    @Operation(summary = "公众号链接")
    public AjaxResult<LoginUrlsVo> codeUrl(@Validated @NotNull() @RequestParam("url") String url) {
        LoginUrlsVo vo = new LoginUrlsVo();
        vo.setUrl(iLoginService.oaCodeUrl(url));
        return AjaxResult.success(vo);
    }


    @NotLogin
    @GetMapping("/getScanCode")
    @Operation(summary = "PC扫码链接")
    public AjaxResult<LoginUrlsVo> scanCodeUrl(@Validated @NotNull() @RequestParam("url") String url, HttpSession session) {
        String qrcodeUrl = iLoginService.scanCodeUrl(url, session);
        LoginUrlsVo vo = new LoginUrlsVo();
        vo.setUrl(qrcodeUrl);
        return AjaxResult.success(vo);
    }

    @NotLogin
    @PostMapping("/scanLogin")
    @Operation(summary = "PC扫码登录")
    public AjaxResult<Object> scanLogin(@Validated @RequestBody LoginScanValidate loginScanValidate, HttpSession session) {
        Integer terminal = LikeFrontThreadLocal.getTerminal();
        String code = loginScanValidate.getCode();
        String state = loginScanValidate.getState();

        LoginTokenVo vo = iLoginService.scanLogin(code, state, terminal, session);
        return AjaxResult.success(vo);
    }

    @NotLogin
    @PostMapping("/logout")
    @Operation(summary = "退出登录")
    public AjaxResult<Object> logout() {
        iLoginService.logout();
        return AjaxResult.success();
    }

    @NotLogin
    @PostMapping("/oaAuthBind")
    @Operation(summary = "绑定微信公众号")
    public AjaxResult<Object> oaAuthBind(@Validated @RequestBody UserBindWechatValidate BindOaValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();
        iUserService.bindOa(BindOaValidate, userId);
        return AjaxResult.success();
    }


    @PostMapping("/mnpAuthBind")
    @Operation(summary = "绑定小程序")
    public AjaxResult<Object> mnpAuthBind(@Validated @RequestBody UserBindWechatValidate BindOaValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();
        iUserService.bindMnp(BindOaValidate, userId);
        return AjaxResult.success();
    }

}
