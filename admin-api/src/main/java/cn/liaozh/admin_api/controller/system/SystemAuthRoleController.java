package cn.liaozh.admin_api.controller.system;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.system.ISystemRoleService;
import cn.liaozh.admin_api.validate.commons.IdValidate;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.system.SystemRoleCreateValidate;
import cn.liaozh.admin_api.validate.system.SystemRoleUpdateValidate;
import cn.liaozh.admin_api.vo.system.SystemAuthRoleVo;
import cn.liaozh.common.aop.NotPower;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminapi/auth.role")
@Tag(name = "系统角色管理")
public class SystemAuthRoleController {

    @Resource
    ISystemRoleService iSystemAuthRoleService;

    @NotPower
    @GetMapping("/all")
    @Operation(summary = "所有角色")
    public AjaxResult<List<SystemAuthRoleVo>> all() {
        List<SystemAuthRoleVo> list = iSystemAuthRoleService.all();
        return AjaxResult.success(list);
    }

    @Log(title = "角色列表")
    @GetMapping("/lists")
    @Operation(summary = "角色列表")
    public AjaxResult<PageResult<SystemAuthRoleVo>> list(@Validated PageValidate pageValidate) {
        PageResult<SystemAuthRoleVo> list = iSystemAuthRoleService.list(pageValidate);
        return AjaxResult.success(list);
    }

    @Log(title = "角色详情")
    @GetMapping("/detail")
    @Operation(summary = "角色详情")
    public AjaxResult<SystemAuthRoleVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthRoleVo vo = iSystemAuthRoleService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "角色新增")
    @PostMapping("/add")
    @Operation(summary = "角色新增")
    public AjaxResult<Object> add(@Validated @RequestBody SystemRoleCreateValidate createValidate) {
        iSystemAuthRoleService.add(createValidate);
        return AjaxResult.success();
    }

    @Log(title = "角色编辑")
    @PostMapping("/edit")
    @Operation(summary = "角色编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody SystemRoleUpdateValidate updateValidate) {
        iSystemAuthRoleService.edit(updateValidate);
        return AjaxResult.success();
    }

    @Log(title = "角色删除")
    @PostMapping("/delete")
    @Operation(summary = "角色删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iSystemAuthRoleService.del(idValidate.getId());
        return AjaxResult.success();
    }

}
