package cn.liaozh.admin_api.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "操作日志Vo")
public class SystemLogOperateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "操作人ID")
    private Integer adminId;

    private String adminName;

    private String account;

    @Schema(description = "操作标题")
    private String action;

    @Schema(description = "请求类型: GET/POST/PUT")
    private String type;

    @Schema(description = "请求接口")
    private String url;

    @Schema(description = "请求参数")
    private String params;

    @Schema(description = "请求结果")
    private String result;

    @Schema(description = "请求IP")
    private String ip;

    @Schema(description = "创建时间")
    private String createTime;

}
