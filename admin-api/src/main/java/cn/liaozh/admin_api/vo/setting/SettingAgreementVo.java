package cn.liaozh.admin_api.vo.setting;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "政策协议详情Vo")
public class SettingAgreementVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "服务协议标题")
    private String serviceTitle;

    @Schema(description = "服务协议内容")
    private String serviceContent;

    @Schema(description = "服务协议标题")
    private String privacyTitle;

    @Schema(description = "服务协议内容")
    private String privacyContent;

}
