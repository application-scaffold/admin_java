package cn.liaozh.admin_api.controller.system;

import com.alibaba.fastjson2.JSONArray;
import cn.liaozh.admin_api.service.system.IDeptService;
import cn.liaozh.admin_api.validate.commons.IdValidate;
import cn.liaozh.admin_api.validate.system.DeptCreateValidate;
import cn.liaozh.admin_api.validate.system.DeptSearchValidate;
import cn.liaozh.admin_api.validate.system.DeptUpdateValidate;
import cn.liaozh.admin_api.vo.system.DeptVo;
import cn.liaozh.common.aop.NotPower;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminapi/dept.dept")
@Tag(name = "系统部门管理")
public class DeptController {

    @Resource
    IDeptService deptService;

    @NotPower
    @GetMapping("/all")
    @Operation(summary = "部门所有")
    public AjaxResult<JSONArray> all() {
        JSONArray list = deptService.all();
        return AjaxResult.success(list);
    }

    @GetMapping("/lists")
    @Operation(summary = "部门列表")
    public AjaxResult<JSONArray> list(@Validated DeptSearchValidate searchValidate) {
        JSONArray list = deptService.list(searchValidate);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @Operation(summary = "部门详情")
    public AjaxResult<DeptVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        DeptVo vo = deptService.detail(id);
        return AjaxResult.success(vo);
    }

    @PostMapping("/add")
    @Operation(summary = "部门新增")
    public AjaxResult<Object> add(@Validated @RequestBody DeptCreateValidate createValidate) {
        deptService.add(createValidate);
        return AjaxResult.success();
    }

    @PostMapping("/edit")
    @Operation(summary = "部门编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody DeptUpdateValidate updateValidate) {
        deptService.edit(updateValidate);
        return AjaxResult.success();
    }

    @PostMapping("/delete")
    @Operation(summary = "部门删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        deptService.del(idValidate.getId());
        return AjaxResult.success();
    }

}
