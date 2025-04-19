package cn.liaozh.admin_api.controller;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.ICrontabService;
import cn.liaozh.admin_api.validate.commons.IdValidate;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.crontab.CrontabCreateValidate;
import cn.liaozh.admin_api.validate.crontab.CrontabUpdateValidate;
import cn.liaozh.admin_api.vo.CrontabDetailVo;
import cn.liaozh.admin_api.vo.CrontabListedVo;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.exception.OperateException;
import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/adminapi/crontab.crontab")
@Tag(name = "计划任务管理")
public class CrontabController {

    @Resource
    ICrontabService iCrontabService;

    @GetMapping("/lists")
    @Operation(summary = "计划任务列表")
    public AjaxResult< PageResult<CrontabListedVo>> list(@Validated PageValidate pageValidate) {
        PageResult<CrontabListedVo> list = iCrontabService.list(pageValidate);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @Operation(summary = "计划任务详情")
    public AjaxResult<Object> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        CrontabDetailVo vo = iCrontabService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "计划任务新增")
    @PostMapping("/add")
    @Operation(summary = "计划任务新增")
    public AjaxResult<Object> add(@Validated @RequestBody CrontabCreateValidate createValidate) throws SchedulerException {
        iCrontabService.add(createValidate);
        return AjaxResult.success();
    }

    @Log(title = "计划任务编辑")
    @PostMapping("/edit")
    @Operation(summary = "计划任务编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody CrontabUpdateValidate updateValidate) throws SchedulerException {
        iCrontabService.edit(updateValidate);
        return AjaxResult.success();
    }

    @Log(title = "计划任务删除")
    @PostMapping("/delete")
    @Operation(summary = "计划任务删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) throws SchedulerException {
        iCrontabService.del(idValidate.getId());
        return AjaxResult.success();
    }


    @GetMapping("/expression")
    @Operation(summary = "计划任务列表")
    public AjaxResult<List<Map<String, String>>> expression(@Validated @RequestParam("expression") String expression) {
        return AjaxResult.success(getExpression(expression));
    }


    public static List<Map<String, String>> getExpression(String cronExpression) {
        try {
            CronExpression cron = new CronExpression(cronExpression);
            List<Date> dates = new ArrayList<>();

            Date date = new Date();
            for (int i = 0; i < 5; i++) {
                Date nextValidTimeAfter = cron.getNextValidTimeAfter(date); // 假设从当前时间开始
                if (nextValidTimeAfter != null) {
                    dates.add(nextValidTimeAfter);
                    date = nextValidTimeAfter;
                } else {
                    break; // 如果没有更多的有效时间，则退出循环
                }
            }

            List<Map<String, String>> lists = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i < dates.size(); i++) {
                Map<String, String> entry = new HashMap<>();
                entry.put("time", String.valueOf(i + 1));
                entry.put("date", sdf.format(dates.get(i)));
                lists.add(entry);
            }

            // 添加额外的条目
            Map<String, String> extraEntry = new HashMap<>();
            extraEntry.put("time", "x");
            extraEntry.put("date", "……");
            lists.add(extraEntry);

            return lists;
        } catch (Exception e) {
            // 注意：在Java中，通常不会直接返回异常消息作为业务逻辑的一部分
            // 这里为了与PHP函数保持一致，我们返回异常消息，但在实际应用中应避免这样做
            throw new OperateException(e.getMessage());
        }
    }

}
