package cn.liaozh.front_api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(name = "充值配置Vo")
public class RechargeConfigVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "是否开启充值: 0=否,1=是")
    private Integer status;

    @Schema(description = "最低充值金额")
    private BigDecimal minAmount;

    @Schema(description = "用户钱包")
    private BigDecimal userMoney;

}
