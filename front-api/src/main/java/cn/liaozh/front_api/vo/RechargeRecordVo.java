package cn.liaozh.front_api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(name = "充值记录Vo")
public class RechargeRecordVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "金额")
    private BigDecimal orderAmount;

    @Schema(description = "描述")
    private String tips;

    @Schema(description = "操作")
    private Integer action;

    @Schema(description = "时间")
    private String createTime;

}
