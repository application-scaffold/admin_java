package cn.liaozh.admin_api.controller.system;

import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.service.system.IJobsService;
import cn.liaozh.admin_api.validate.commons.IdValidate;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.system.JobsCreateValidate;
import cn.liaozh.admin_api.validate.system.JobsSearchValidate;
import cn.liaozh.admin_api.validate.system.JobsUpdateValidate;
import cn.liaozh.admin_api.vo.system.JobsVo;
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

import java.util.List;

@RestController
@RequestMapping("/adminapi/dept.jobs")
@Tag(name = "系统岗位管理")
public class JobsController {

    @Resource
    IJobsService iJobsService;

    @NotPower
    @GetMapping("/all")
    @Operation(summary = "所有岗位")
    public AjaxResult<List<JobsVo>> all() {
        List<JobsVo> list = iJobsService.all();
        return AjaxResult.success(list);
    }

    @GetMapping("/lists")
    @Operation(summary = "岗位列表")
    public AjaxResult<Object> list(@Validated PageValidate pageValidate,
                                               @Validated JobsSearchValidate searchValidate) {
        if (StringUtils.isNotNull(searchValidate.getExport()) && searchValidate.getExport().equals(1)) {
            JSONObject ret = iJobsService.getExportData(pageValidate, searchValidate);
            return AjaxResult.success(ret);
        } else if (StringUtils.isNotNull(searchValidate.getExport()) && searchValidate.getExport().equals(2)) {
            String path = iJobsService.export(searchValidate);
            return AjaxResult.success(2, new JSONObject() {{
                put("url", path);
            }}, ErrorEnum.SHOW_MSG.getCode());
        } else {
            PageResult<JobsVo> list = iJobsService.list(pageValidate, searchValidate);
            return AjaxResult.success(list);
        }
    }

    @GetMapping("/detail")
    @Operation(summary = "岗位详情")
    public AjaxResult<JobsVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        JobsVo vo = iJobsService.detail(id);
        return AjaxResult.success(vo);
    }

    @PostMapping("/add")
    @Operation(summary = "岗位新增")
    public AjaxResult<Object> add(@Validated @RequestBody JobsCreateValidate createValidate) {
        iJobsService.add(createValidate);
        return AjaxResult.success();
    }

    @PostMapping("/edit")
    @Operation(summary = "岗位编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody JobsUpdateValidate updateValidate) {
        iJobsService.edit(updateValidate);
        return AjaxResult.success();
    }

    @PostMapping("/delete")
    @Operation(summary = "岗位删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iJobsService.del(idValidate.getId());
        return AjaxResult.success();
    }

}
