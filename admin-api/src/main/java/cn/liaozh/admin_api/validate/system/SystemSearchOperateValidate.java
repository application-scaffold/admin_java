package cn.liaozh.admin_api.validate.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "系统操作日志搜索参数")
public class SystemSearchOperateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "标题")
    private String action;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "IP")
    private String ip;

    @Schema(description = "类型")
    private String type;


    @Schema(description = "路由")
    private String url;

    @Schema(description = "开始时间")
    private String start_time;

    @Schema(description = "结束时间")
    private String end_time;

}
