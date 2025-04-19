package cn.liaozh.admin_api.controller.setting;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.ISettingLoginService;
import cn.liaozh.admin_api.validate.setting.SettingLoginValidate;
import cn.liaozh.admin_api.vo.setting.SettingLoginVo;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminapi/setting.user.user")
@Tag(name = "配置用户登录")
public class SettingLoginController {

    @Resource
    ISettingLoginService iSettingLoginService;

    @GetMapping("/getRegisterConfig")
    @Operation(summary = "登录设置详情")
    public AjaxResult<SettingLoginVo> getRegisterConfig() {
        SettingLoginVo vo = iSettingLoginService.getRegisterConfig();
        return AjaxResult.success(vo);
    }

    @Log(title = "登录设置编辑")
    @PostMapping("/setRegisterConfig")
    @Operation(summary = "登录设置编辑")
    public AjaxResult<Object> setRegisterConfig(@Validated @RequestBody SettingLoginValidate loginValidate) {
        iSettingLoginService.setRegisterConfig(loginValidate);
        return AjaxResult.success();
    }

}
