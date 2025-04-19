package cn.liaozh.front_api.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(name = "用户资金变更Vo")
public class UserAccountListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    private Integer id;
    @Schema(description = "变动类型")
    private Integer changeType;

    @Schema(description = "变动类型字符串")
    private String typeDesc;

    @Schema(description = "变动数量")
    private BigDecimal changeAmount;

    @Schema(description = "变动数量字符串")
    private String changeAmountDesc;

    @Schema(description = "变动类型: [1=增加, 2=减少]")
    private Integer action;
    @Schema(description = "备注信息")
    private String remark;

    @Schema(description = "创建时间")
    private String createTime;


}
