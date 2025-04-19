package cn.liaozh.admin_api.validate.crontab;

import cn.liaozh.common.validator.annotation.IDMust;
import cn.liaozh.common.validator.annotation.IntegerContains;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Schema(name = "计划任务更新参数")
public class CrontabUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @NotNull(message = "name参数缺失")
    @Schema(description = "任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull(message = "types参数缺失")
    @Schema(description = "类型 1-定时任务")
    private Integer type;

    @NotNull(message = "command参数缺失")
    @Schema(description = "执行指令", requiredMode = Schema.RequiredMode.REQUIRED)
    private String command;

    @NotNull(message = "rules参数缺失")
    @Schema(description = "执行规则", requiredMode = Schema.RequiredMode.REQUIRED)
    private String expression;

    @Length(max = 200, message = "remark参数不能超出200个字符")
    @Schema(description = "备注")
    private String remark;

    @NotNull(message = "status参数缺失")
    @IntegerContains(values = {1, 2, 3}, message = "status参数取值异常")
    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "参数")
    private String params;


}
