package cn.liaozh.admin_api.controller.system;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.LikeAdminThreadLocal;
import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.system.ISystemMenuService;
import cn.liaozh.admin_api.validate.commons.IdValidate;
import cn.liaozh.admin_api.validate.system.SystemMenuCreateValidate;
import cn.liaozh.admin_api.validate.system.SystemMenuUpdateValidate;
import cn.liaozh.admin_api.vo.system.SystemAuthMenuVo;
import cn.liaozh.common.aop.NotPower;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminapi/auth.menu")
@Tag(name = "系统菜单管理")
public class SystemAuthMenuController {

    @Resource
    ISystemMenuService iSystemAuthMenuService;

    @NotPower
    @GetMapping("/route")
    @Operation(summary = "获取菜单路由")
    public AjaxResult<JSONArray> route() {
        List<Integer> roleIds = LikeAdminThreadLocal.getRoleIds();
        JSONArray lists = iSystemAuthMenuService.selectMenuByRoleId(roleIds);
        return AjaxResult.success(lists);
    }

    @NotPower
    @GetMapping("/lists")
    @Operation(summary = "获取菜单列表")
    public AjaxResult<JSONObject> list() {
        JSONObject result = iSystemAuthMenuService.list();
        return AjaxResult.success(result);
    }

    @NotPower
    @GetMapping("/systemMenuLists")
    @Operation(summary = "获取菜单列表")
    public AjaxResult<Object> systemMenuLists() {
        JSONArray result = iSystemAuthMenuService.systemMenuLists();
        return AjaxResult.success(result);
    }



    @NotPower
    @GetMapping("/all")
    @Operation(summary = "获取菜单列表")
    public AjaxResult<JSONArray> all() {
        JSONArray result = iSystemAuthMenuService.all();
        return AjaxResult.success(result);
    }

    @GetMapping("/detail")
    @Operation(summary = "获取菜单详情")
    public AjaxResult<SystemAuthMenuVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthMenuVo vo = iSystemAuthMenuService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "菜单新增")
    @PostMapping("/add")
    @Operation(summary = "新增菜单")
    public AjaxResult<Object> add(@Validated @RequestBody SystemMenuCreateValidate createValidate) {
        iSystemAuthMenuService.add(createValidate);
        return AjaxResult.success();
    }

    @Log(title = "菜单编辑")
    @PostMapping("/edit")
    @Operation(summary = "菜单编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody SystemMenuUpdateValidate updateValidate) {
        iSystemAuthMenuService.edit(updateValidate);
        return AjaxResult.success();
    }

    @Log(title = "菜单删除")
    @PostMapping("/delete")
    @Operation(summary = "菜单删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iSystemAuthMenuService.del(idValidate.getId());
        return AjaxResult.success();
    }

}
