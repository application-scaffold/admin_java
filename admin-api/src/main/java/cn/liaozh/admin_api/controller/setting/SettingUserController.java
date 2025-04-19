package cn.liaozh.admin_api.controller.setting;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.ISettingUserService;
import cn.liaozh.admin_api.validate.setting.SettingUserValidate;
import cn.liaozh.admin_api.vo.setting.SettingUserVo;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminapi/setting.user.user")
@Tag(name = "配置用户参数")
public class SettingUserController {

    @Resource
    ISettingUserService iSettingUserService;

    @GetMapping("/getConfig")
    @Operation(summary = "用户设置详情")
    public AjaxResult<SettingUserVo> detail() {
        SettingUserVo vo = iSettingUserService.getConfig();
        return AjaxResult.success(vo);
    }

    @Log(title = "用户设置编辑")
    @PostMapping("/setConfig")
    @Operation(summary = "用户设置编辑")
    public AjaxResult<Object> setConfig(@Validated @RequestBody SettingUserValidate userValidate) {
        iSettingUserService.setConfig(userValidate);
        return AjaxResult.success();
    }

}
