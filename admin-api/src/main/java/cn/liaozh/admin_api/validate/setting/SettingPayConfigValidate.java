package cn.liaozh.admin_api.validate.setting;

import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "支付渠道设置参数")
public class SettingPayConfigValidate {

    @IDMust(message = "id参数必传且需大于0")
    @Schema(description = "ID", required = true)
    private Integer id;

    @NotNull(message = "name参数缺失")
    @Schema(description = "模版名称", required = true)
    private String name;

    @Schema(description = "模版名称")
    private String icon;

    @Schema(description = "排序编号")
    private Integer sort;

    @Schema(description = "备注信息")
    private String remark;

    @Schema(description = "domain")
    private String domian;

    @Schema(description = "配置参数")
    private JSONObject config;

}
