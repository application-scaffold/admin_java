package cn.liaozh.admin_api.controller.channel;

import cn.liaozh.admin_api.service.IChannelOaReplyService;
import cn.liaozh.admin_api.validate.channel.ChannelRpValidate;
import cn.liaozh.admin_api.validate.commons.IdValidate;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.vo.channel.ChannelRpVo;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminapi/channel.official_account_reply")
@Tag(name = "公众号回复")
public class ChannelOaReplyController {

    @Resource
    IChannelOaReplyService iChannelOaReplyService;

    @GetMapping("/lists")
    @Operation(summary = "默认回复列表")
    public AjaxResult<PageResult<ChannelRpVo>> list(@Validated PageValidate pageValidate, @RequestParam("reply_type") Integer replyType) {
        PageResult<ChannelRpVo> list = iChannelOaReplyService.list(pageValidate, replyType);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @Operation(summary = "默认回复详情")
    public AjaxResult<ChannelRpVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ChannelRpVo vo = iChannelOaReplyService.detail(id);
        return AjaxResult.success(vo);
    }

    @PostMapping("/add")
    @Operation(summary = "默认回复新增")
    public AjaxResult<Object> add(@Validated @RequestBody ChannelRpValidate defaultValidate) {
        iChannelOaReplyService.add(defaultValidate);
        return AjaxResult.success();
    }

    @PostMapping("/edit")
    @Operation(summary = "默认回复编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody ChannelRpValidate defaultValidate) {
        iChannelOaReplyService.edit(defaultValidate);
        return AjaxResult.success();
    }

    @PostMapping("/delete")
    @Operation(summary = "默认回复删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iChannelOaReplyService.del(idValidate.getId());
        return AjaxResult.success();
    }

    @PostMapping("/status")
    @Operation(summary = "默认回复状态")
    public AjaxResult<Object> status(@Validated @RequestBody IdValidate idValidate) {
        iChannelOaReplyService.status(idValidate.getId());
        return AjaxResult.success();
    }

}
