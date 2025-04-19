package cn.liaozh.admin_api.validate.finance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "退款记录搜索参数")
public class FinanceRefundValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "充值订单id")
    private Integer recharge_id;


}
