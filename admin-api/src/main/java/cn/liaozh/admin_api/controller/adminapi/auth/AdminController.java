package cn.liaozh.admin_api.controller.adminapi.auth;

import cn.liaozh.admin_api.LikeAdminThreadLocal;
import cn.liaozh.admin_api.service.admin.IAdminRoleService;
import cn.liaozh.admin_api.service.admin.IAdminService;
import cn.liaozh.admin_api.vo.auth.AdminMySelfVo;
import cn.liaozh.common.aop.NotPower;
import cn.liaozh.common.core.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"adminapi/auth/admin", "adminapi/auth.admin"})
@Tag(name = "管理员详情管理")
public class AdminController {

    @Resource
    IAdminService iAdminService;

    @Resource
    IAdminRoleService iAdminRoleService;

    @NotPower
    @GetMapping("/mySelf")
    @Operation(summary = "获取当前管理员信息")
    public AjaxResult<AdminMySelfVo> mySelf() {
        Integer adminId = LikeAdminThreadLocal.getAdminId();

        List<Integer> roleIds = iAdminRoleService.getRoleIdAttr(adminId);

        AdminMySelfVo mySelf = iAdminService.mySelf(LikeAdminThreadLocal.getAdminId(), roleIds);
        return AjaxResult.success(mySelf);
    }

}
