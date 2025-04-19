package cn.liaozh.admin_api.controller.adminapi;

import cn.liaozh.admin_api.service.system.ISystemLoginService;
import cn.liaozh.admin_api.validate.system.SystemAdminLoginsValidate;
import cn.liaozh.admin_api.vo.system.SystemCaptchaVo;
import cn.liaozh.admin_api.vo.system.SystemLoginVo;
import cn.liaozh.common.aop.NotLogin;
import cn.liaozh.common.aop.NotPower;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("adminapi/login")
@Tag(name = "系统登录管理")
public class LoginController {

    @Resource
    ISystemLoginService iSystemLoginService;

    @NotLogin
    @GetMapping("/captcha")
    @Operation(summary = "取验证码")
    public AjaxResult<SystemCaptchaVo> captcha() {
        SystemCaptchaVo vo = iSystemLoginService.captcha();
        return AjaxResult.success(vo);
    }

    @NotLogin
    @PostMapping("/account")
    @Operation(summary = "登录系统")
    public AjaxResult<SystemLoginVo> account(@Validated() @RequestBody SystemAdminLoginsValidate loginsValidate) {
        SystemLoginVo vo = iSystemLoginService.login(loginsValidate);
        return AjaxResult.success(vo);
    }

    @NotPower
    @PostMapping("/logout")
    @Operation(summary = "退出登录")
    public AjaxResult<Object> logout(HttpServletRequest request) {
        iSystemLoginService.logout(request.getHeader("token"));
        return AjaxResult.success();
    }

}
