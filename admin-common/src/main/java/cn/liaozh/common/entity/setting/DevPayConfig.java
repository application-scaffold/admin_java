package cn.liaozh.common.entity.setting;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "支付配置实体")
public class DevPayConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "模版名称")
    private String name;

    @TableField(exist = false)
    @Schema(description = "显示名称")
    private String payWayName;

    @Schema(description = "渠道图标")
    private String icon;

    @Schema(description = "支付方式: [1=余额支付, 2=微信支付, 3=支付宝支付]")
    private Integer payWay;

    @Schema(description = "排序编号")
    private Integer sort;

    @Schema(description = "备注信息")
    private String remark;

    @Schema(description = "配置参数")
    private Object config;

}
