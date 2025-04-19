package cn.liaozh.admin_api.validate.setting;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "站点统计设置参数")
public class SettingSiteStatisticsValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "clarityCode")
    private String clarityCode;

}
