package cn.liaozh.admin_api.controller.setting;

import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.ISettingCopyrightService;
import cn.liaozh.admin_api.service.ISettingProtocolService;
import cn.liaozh.admin_api.service.ISettingWebsiteService;
import cn.liaozh.admin_api.validate.setting.SettingAgreementValidate;
import cn.liaozh.admin_api.validate.setting.SettingCopyrightValidate;
import cn.liaozh.admin_api.validate.setting.SettingSiteStatisticsValidate;
import cn.liaozh.admin_api.validate.setting.SettingWebsiteValidate;
import cn.liaozh.admin_api.vo.setting.SettingAgreementVo;
import cn.liaozh.admin_api.vo.setting.SettingCopyrightVo;
import cn.liaozh.admin_api.vo.setting.SettingSiteStatisticsVo;
import cn.liaozh.admin_api.vo.setting.SettingWebsiteVo;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.util.ConfigUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("adminapi/setting.web.web_setting")
@Tag(name = "配置网站信息")
public class SettingWebsiteController {

    @Resource
    ISettingWebsiteService iSettingWebsiteService;

    @Resource
    ISettingCopyrightService iSettingCopyrightService;

    @Resource
    ISettingProtocolService iSettingProtocolService;

    @GetMapping("/getWebsite")
    @Operation(summary = "网站配置信息")
    public AjaxResult<SettingWebsiteVo> getWebsite() {
        SettingWebsiteVo detail = iSettingWebsiteService.getWebsite();
        return AjaxResult.success(detail);
    }

    @Log(title = "网站配置编辑")
    @PostMapping("/setWebsite")
    @Operation(summary = "网站配置编辑")
    public AjaxResult<Object> setWebsite(@Validated @RequestBody SettingWebsiteValidate websiteValidate) {
        iSettingWebsiteService.setWebsite(websiteValidate);
        return AjaxResult.success();
    }



    @GetMapping("/getCopyright")
    @Operation(summary = "网站版权信息")
    public AjaxResult<List<SettingCopyrightVo>> getCopyright() {
        List<SettingCopyrightVo> list = iSettingCopyrightService.getCopyright();
        return AjaxResult.success(list);
    }

    @Log(title = "网站版权编辑")
    @PostMapping("/setCopyright")
    @Operation(summary = "网站版权编辑")
    public AjaxResult<Object> setCopyright(@Validated @RequestBody SettingCopyrightValidate copyrightValidate) {
        iSettingCopyrightService.setCopyright(copyrightValidate);
        return AjaxResult.success();
    }

    @GetMapping("/getAgreement")
    @Operation(summary = "政策协议信息")
    public AjaxResult<SettingAgreementVo> getAgreement() {
        SettingAgreementVo detail = iSettingProtocolService.getAgreement();
        return AjaxResult.success(detail);
    }

    @Log(title = "政策协议编辑")
    @PostMapping("/setAgreement")
    @Operation(summary = "政策协议编辑")
    public AjaxResult<Object> setAgreement(@Validated @RequestBody SettingAgreementValidate protocolValidate) {
        iSettingProtocolService.setAgreement(protocolValidate);
        return AjaxResult.success();
    }


    @GetMapping("/getSiteStatistics")
    @Operation(summary = "获取站点统计配置")
    public AjaxResult<SettingSiteStatisticsVo> getSiteStatistics() {
        SettingSiteStatisticsVo vo = new SettingSiteStatisticsVo();
        vo.setClarityCode(ConfigUtils.get("siteStatistics", "clarity_code", ""));
        return AjaxResult.success(vo);
    }

    @Log(title = "站点统计配置")
    @PostMapping("/setSiteStatistics")
    @Operation(summary = "站点统计配置")
    public AjaxResult<Object> setSiteStatistics(@Validated @RequestBody SettingSiteStatisticsValidate settingSiteStatisticsValidate) {
        ConfigUtils.set("siteStatistics", "clarity_code", settingSiteStatisticsValidate.getClarityCode());
        return AjaxResult.success();
    }

}
