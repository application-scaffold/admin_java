package cn.liaozh.admin_api.controller.system;

import cn.liaozh.admin_api.service.system.ISystemLogsServer;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.system.SystemSearchLoginsValidate;
import cn.liaozh.admin_api.validate.system.SystemSearchOperateValidate;
import cn.liaozh.admin_api.vo.system.SystemLogLoginVo;
import cn.liaozh.admin_api.vo.system.SystemLogOperateVo;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adminapi/setting.system.log")
@Tag(name = "系统日志管理")
public class SystemLogsController {

    @Resource
    ISystemLogsServer iSystemLogsServer;

    @GetMapping("/lists")
    @Operation(summary = "系统操作日志")
    public AjaxResult<PageResult<SystemLogOperateVo>> lists(@Validated PageValidate pageValidate,
                                                              @Validated SystemSearchOperateValidate searchValidate) {
        PageResult<SystemLogOperateVo> list = iSystemLogsServer.operate(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    @GetMapping("/login")
    @Operation(summary = "系统登录日志")
    public AjaxResult<PageResult<SystemLogLoginVo>> login(@Validated PageValidate pageValidate,
                                                          @Validated SystemSearchLoginsValidate searchValidate) {
        PageResult<SystemLogLoginVo> list = iSystemLogsServer.login(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

}
