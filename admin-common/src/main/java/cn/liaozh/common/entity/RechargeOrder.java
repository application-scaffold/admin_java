package cn.liaozh.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Schema(description = "充值订单实体类")
public class RechargeOrder {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "用户ID")
    private Integer userId;

    @Schema(description = "订单编号")
    private String sn;

    @Schema(description = "支付编号")
    private String paySn;

    @Schema(description = "支付方式: [2=微信支付, 3=支付宝支付]")
    private Integer payWay;

    @Schema(description = "支付状态: [0=待支付, 1=已支付]")
    private Integer payStatus;

    @Schema(description = "支付时间")
    private Long payTime;

    @Schema(description = "充值金额")
    private BigDecimal orderAmount;

    @Schema(description = "下单终端")
    private Integer orderTerminal;

    @Schema(description = "交易流水")
    private String transactionId;

    @Schema(description = "退款状态: [0=未退款 , 1=已退款]")
    private Integer refundStatus;
    @Schema(description = "退款交易流水号")
    private BigInteger refundTransactionId;
    @Schema(description = "创建时间")
    private Long createTime;

    @Schema(description = "更新时间")
    private Long updateTime;

    @Schema(description = "删除时间")
    private Long deleteTime;

}
