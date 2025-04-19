package cn.liaozh.admin_api.validate.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "用户搜索参数")
public class UserSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "渠道")
    private Integer channel;

    @Schema(description = "关键词")
    private String keyword;

    @Schema(description = "开始时间")
    private String create_time_start;

    @Schema(description = "结束时间")
    private String create_time_end;

    @Schema(description = "导出信息")
    private Integer export;
    @Schema(description = "file_name")
    private String file_name;

    @Schema(description = "page_start")
    private Integer page_start;

    @Schema(description = "page_end")
    private Integer page_end;

    @Schema(description = "page_size")
    private Integer page_size;

    @Schema(description = "page_type")
    private Integer page_type;

}
