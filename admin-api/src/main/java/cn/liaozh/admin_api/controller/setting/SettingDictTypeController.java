package cn.liaozh.admin_api.controller.setting;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.ISettingDictTypeService;
import cn.liaozh.admin_api.validate.commons.IdValidate;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.setting.DictTypeCreateValidate;
import cn.liaozh.admin_api.validate.setting.DictTypeUpdateValidate;
import cn.liaozh.admin_api.vo.setting.SettingDictTypeVo;
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
import java.util.Map;

@RestController
@RequestMapping("/adminapi/setting.dict.dict_type")
@Tag(name = "配置字典类型")
public class SettingDictTypeController {

    @Resource
    ISettingDictTypeService iSettingDictTypeService;

    @NotPower
    @GetMapping("/all")
    @Operation(summary = "字典类型所有")
    public AjaxResult<List<SettingDictTypeVo>> all() {
        List<SettingDictTypeVo> list = iSettingDictTypeService.all();
        return AjaxResult.success(list);
    }

    @GetMapping("/lists")
    @Operation(summary = "字典类型列表")
    public AjaxResult<PageResult<SettingDictTypeVo>> list(@Validated PageValidate pageValidate,
                       @RequestParam Map<String, String> params) {
        PageResult<SettingDictTypeVo> list = iSettingDictTypeService.list(pageValidate, params);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @Operation(summary = "字典类型详情")
    public AjaxResult<SettingDictTypeVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SettingDictTypeVo vo = iSettingDictTypeService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "字典类型新增")
    @PostMapping("/add")
    @Operation(summary = "字典类型新增")
    public AjaxResult<Object> add(@Validated @RequestBody DictTypeCreateValidate createValidate) {
        iSettingDictTypeService.add(createValidate);
        return AjaxResult.success();
    }

    @Log(title = "字典类型编辑")
    @PostMapping("/edit")
    @Operation(summary = "字典类型编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody DictTypeUpdateValidate updateValidate) {
        iSettingDictTypeService.edit(updateValidate);
        return AjaxResult.success();
    }

    @Log(title = "字典类型删除")
    @PostMapping("/delete")
    @Operation(summary = "字典类型删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iSettingDictTypeService.del(idValidate.getId());
        return AjaxResult.success();
    }

}
