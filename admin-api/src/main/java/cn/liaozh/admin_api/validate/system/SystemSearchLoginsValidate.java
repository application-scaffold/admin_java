package cn.liaozh.admin_api.validate.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "系统登录日志搜索参数")
public class SystemSearchLoginsValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "开始时间")
    private String startTime;

    @Schema(description = "结束时间")
    private String endTime;

}
