package cn.liaozh.front_api.validate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(name = "预支付订单参数")
public class PaymentValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    //@NotNull(message = "scene参数缺失")
    @Schema(description = "支付场景: [recharge=充值,order=普通订单]")
    private String scene;

    @NotNull(message = "payWay参数缺失")
    @Schema(description = "支付方式: [1=余额支付,2=微信支付,3=支付宝支付]")
    private Integer payWay;

    @NotNull(message = "orderId参数缺失")
    @Schema(description = "订单ID")
    private Integer orderId;

    @Schema(description = "重定向链接: H5端需要")
    private String redirect;

    @Schema(description = "用户ID", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer userId;
    
    @Schema(description = "订单类型", accessMode = Schema.AccessMode.READ_ONLY)
    private String attach;
    
    @Schema(description = "订单编号", accessMode = Schema.AccessMode.READ_ONLY)
    private String outTradeNo;
    
    @Schema(description = "订单金额", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal orderAmount;
    
    @Schema(description = "用户描述", accessMode = Schema.AccessMode.READ_ONLY)
    private String description;

    @Schema(description = "Terminal")
    private Integer Terminal;

    @Schema(description = "微信授权code")
    private String code;

}
