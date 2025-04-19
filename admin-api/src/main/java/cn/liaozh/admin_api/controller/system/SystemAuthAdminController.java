package cn.liaozh.admin_api.controller.system;

import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.LikeAdminThreadLocal;
import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.admin.IAdminService;
import cn.liaozh.admin_api.validate.commons.IdValidate;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.system.SystemAdminCreateValidate;
import cn.liaozh.admin_api.validate.system.SystemAdminSearchValidate;
import cn.liaozh.admin_api.validate.system.SystemAdminUpInfoValidate;
import cn.liaozh.admin_api.validate.system.SystemAdminUpdateValidate;
import cn.liaozh.admin_api.vo.system.SystemAuthAdminDetailVo;
import cn.liaozh.admin_api.vo.system.SystemAuthAdminListedVo;
import cn.liaozh.admin_api.vo.system.SystemAuthAdminSelvesVo;
import cn.liaozh.common.aop.NotPower;
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
@RequestMapping("/adminapi/auth.admin")
@Tag(name = "系统用户管理")
public class SystemAuthAdminController {

    @Resource
    IAdminService iSystemAuthAdminService;

    @GetMapping("/lists")
    @Operation(summary = "管理员列表")
    public AjaxResult<Object> list(@Validated PageValidate pageValidate,
                                                                @Validated SystemAdminSearchValidate searchValidate) {
        if (StringUtils.isNotNull(searchValidate.getExport()) && searchValidate.getExport().equals(1)) {
            JSONObject ret = iSystemAuthAdminService.getExportData(pageValidate, searchValidate);
            return AjaxResult.success(ret);
        } else if (StringUtils.isNotNull(searchValidate.getExport()) && searchValidate.getExport().equals(2)) {
            String path = iSystemAuthAdminService.export(searchValidate);
            return AjaxResult.success(2, new JSONObject() {{
                put("url", path);
            }}, ErrorEnum.SHOW_MSG.getCode());
        } else {
            PageResult<SystemAuthAdminListedVo> list = iSystemAuthAdminService.list(pageValidate, searchValidate);
            return AjaxResult.success(list);
        }
    }

    @NotPower
    @GetMapping("/self")
    @Operation(summary = "管理员信息")
    public AjaxResult<SystemAuthAdminSelvesVo> self() {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        SystemAuthAdminSelvesVo vo = iSystemAuthAdminService.self(adminId);
        return AjaxResult.success(vo);
    }

    @GetMapping("/detail")
    @Operation(summary = "管理员详情")
    public AjaxResult<SystemAuthAdminDetailVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthAdminDetailVo vo = iSystemAuthAdminService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "管理员新增")
    @PostMapping("/add")
    @Operation(summary = "管理员新增")
    public AjaxResult<Object> add(@Validated @RequestBody SystemAdminCreateValidate createValidate) {
        iSystemAuthAdminService.add(createValidate);
        return AjaxResult.success();
    }

    @Log(title = "管理员编辑")
    @PostMapping("/edit")
    @Operation(summary = "管理员编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody SystemAdminUpdateValidate updateValidate) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        iSystemAuthAdminService.edit(updateValidate, adminId);
        return AjaxResult.success();
    }

    @NotPower
    @Log(title = "管理员更新")
    @PostMapping("/upInfo")
    @Operation(summary = "当前管理员更新")
    public AjaxResult<Object> upInfo(@Validated @RequestBody SystemAdminUpInfoValidate upInfoValidate) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        iSystemAuthAdminService.upInfo(upInfoValidate, adminId);
        return AjaxResult.success();
    }

    @Log(title = "管理员删除")
    @PostMapping("/delete")
    @Operation(summary = "管理员删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        iSystemAuthAdminService.del(idValidate.getId(), adminId);
        return AjaxResult.success();
    }

    @Log(title = "管理员状态")
    @PostMapping("/disable")
    @Operation(summary = "管理员状态切换")
    public AjaxResult<Object> disable(@Validated @RequestBody IdValidate idValidate) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        iSystemAuthAdminService.disable(idValidate.getId(), adminId);
        return AjaxResult.success();
    }

}
